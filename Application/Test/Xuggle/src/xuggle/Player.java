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

public class Player {
    
    public double SECONDS_BETWEEN_FRAMES;
    private final String inputFilename = "db_design.mp4";
    private final String outputFilePrefix = "";    
    private int mVideoStreamIndex = -1;
    public long MICRO_SECONDS_BETWEEN_FRAMES;
    public String filePath;
    
   /*
    * TEST-STUB
    * 
    * public static void main(String args[]){
    	test t = new test();
    	String s = t.capture(100);
    	System.out.println(s);
	}*/
    public String capture(double time) {
    	this.SECONDS_BETWEEN_FRAMES = time; 
    	MICRO_SECONDS_BETWEEN_FRAMES = (long)(Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);
        IMediaReader mediaReader = ToolFactory.makeReader(inputFilename);
        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
        mediaReader.addListener(new ImageSnapListener());
        while (mediaReader.readPacket() == null) ;
        return filePath;
    }

    public class ImageSnapListener extends MediaListenerAdapter {
        public void onVideoPicture(IVideoPictureEvent event) {
            if (event.getStreamIndex() != mVideoStreamIndex) {
                if (mVideoStreamIndex == -1)
                    mVideoStreamIndex = event.getStreamIndex();
                else
                    return;
            }
            if(event.getTimeStamp() - MICRO_SECONDS_BETWEEN_FRAMES == 0){
            	filePath = dumpImageToFile(event.getImage());
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
}
