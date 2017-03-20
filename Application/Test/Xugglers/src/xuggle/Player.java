package xuggle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;
import com.xuggle.xuggler.IContainer;
	
public class Player extends MediaListenerAdapter{
    public double SECONDS_BETWEEN_FRAMES;
    private final String inputFilename = "db_design.mp4";
    private final String outputFilePrefix = "";    
    private int mVideoStreamIndex = -1;
    public long MICRO_SECONDS_BETWEEN_FRAMES;
    public String filePath;
    public static boolean takeImage = true;
    public IMediaReader mediaReader;
    
   /*
    * TEST-STUB 
    */
    public static void main(String args[]){
    	Player p = new Player();
    	System.out.println(p.checkVideo());
    }
    public boolean checkAudio(){
    	IContainer container = IContainer.make();
    	int result = container.open(inputFilename, IContainer.Type.READ, null);
    	return (result<0)?false:true;
    }
    public boolean checkVideo(){
    	IContainer container = IContainer.make();
    	int result = container.open(inputFilename, IContainer.Type.READ, null);
    	return (result<0)?false:true;
    }
    public String capture(double time) {
    	this.SECONDS_BETWEEN_FRAMES = time; 
    	MICRO_SECONDS_BETWEEN_FRAMES = (long)(Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);
    	mediaReader = ToolFactory.makeReader(inputFilename);
        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
        mediaReader.addListener(this);
        while (mediaReader.readPacket() == null) {
        	if (!takeImage) {
        		takeImage= true;
                mediaReader.removeListener(this);
                break;
            }
        }
        return filePath;  
    }    
    
    public void onVideoPicture(IVideoPictureEvent event) {
        try {
             if (event.getStreamIndex() != mVideoStreamIndex) {
                if (-1 == mVideoStreamIndex) {
                    mVideoStreamIndex = event.getStreamIndex();
                } else {
                    return;
                }
             }
             if ((event.getTimeStamp() > MICRO_SECONDS_BETWEEN_FRAMES) && takeImage) {
                filePath = dumpImageToFile(event.getImage());
                takeImage = false;
             }
        } 
        catch (Exception e) { 
        	e.printStackTrace(); 
        }
    }

    public String dumpImageToFile(BufferedImage image) {
        try {
            String outputFilename = outputFilePrefix + System.currentTimeMillis() + ".png";
            ImageIO.write(image, "png", new File(outputFilename));
            return outputFilename;
        } 
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
