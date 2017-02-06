package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ImageCrop extends Application {
    
    int width = 420, height = 524;
    int ulx = 100, uly = 100, w = 80, h = 80;
    int offset;
    Rectangle rectInside = new Rectangle();
    PixelWriter pixelWriter;
    byte[] buffer;
    double x1,x2,y1,y2;
    
    public void crop(Stage stage) {
        start(stage);
    }
    
    @Override
    public void start(Stage stage) {
        String imageFile = "car.jpg";
        Image image = new Image(imageFile, width, height, false, false);
        ImageView imageView = new ImageView(image);
    
        imageView.setOnMousePressed(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
        	    imageView.setMouseTransparent(true);
        	    x1 = event.getSceneX();
        	    y1 = event.getSceneY();
        	    //System.out.println(event.getSceneX()+" "+event.getSceneY());
        	    //System.out.println(event.getScreenX()+" "+event.getScreenY());
        	    event.setDragDetect(true);
   		    }
  		});
        
        imageView.setOnMouseReleased(new EventHandler <MouseEvent>()
  		{
  		    public void handle(MouseEvent event)
  		    {
  		    	imageView.setMouseTransparent(false);
  		    	try{
  		    	if(event.getSceneX()<x1){
  		    		x2 = x1;
  		    		x1 =event.getSceneX();
  		    	}
  		    	else{
  		    		x2 = event.getSceneX();
  		    	}
  		    	if(event.getSceneY()<y1){
  		    		y2 = y1;
  		    		y1 =event.getSceneY();
  		    	}
  		    	else{
  		    		y2 = event.getSceneY();
  		    	}
  		    	}
  		    	catch(Exception e){
  		    		System.out.println("Region out of bound");
  		    	}
  		    	
  		    	System.out.println(x1+" "+y1+" "+x2+" "+y2);
  		    	ulx = (int)x1;
  		    	uly = (int)y1;
  		    	w =(int) (x2-x1);
  		    	h = (int)(y2-y1);
  		    	rectInside.setX(ulx);
  	            rectInside.setY(uly);
  	            offset = 4*(ulx+width*uly);
  	            
  	            //rectInside = new Rectangle(uly, uly, w,h);
  	            rectInside.setX(ulx);
  	            rectInside.setY(uly);
  	            rectInside.setWidth(w);
  	            rectInside.setHeight(h);
  	            rectInside.setFill(Color.TRANSPARENT);
  	            rectInside.setStroke(Color.LIME);
  	            rectInside.setStrokeWidth(2);
  	            
  	            drawImage();
        	}
   		});
  		
        PixelReader pixelReader = image.getPixelReader();
        buffer = new byte[width * height * 4];
        pixelReader.getPixels(0, 0,width, height,PixelFormat.getByteBgraInstance(),buffer, 0,4*width);
        
        WritableImage writableImage = new WritableImage(width, height);
        pixelWriter = writableImage.getPixelWriter();
       //offset = 4*(ulx+width*uly);
       // drawImage();
        
        VBox vbox = new VBox(new ImageView(writableImage));
 
        vbox.setLayoutY(height);
        
        Group root = new Group(imageView, rectInside, vbox);
        Scene scene = new Scene(root,width,height+200);
        stage.setScene(scene);
        stage.setTitle("Example 94. Cropping");
        stage.show();
    }
    
      
    private void drawImage() {
    	//pixelWriter = null;
        pixelWriter.setPixels( 160, 50, w, h,
                PixelFormat.getByteBgraInstance(),
                buffer, offset, 4*width);
    }
}
