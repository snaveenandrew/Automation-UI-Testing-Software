package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sec extends BorderPane{
	double x1,y1,x2,y2;
	int ulx,uly,w,h;
	String filename;	
	public Sec() {
		// TODO Auto-generated constructor stub
		
		setStyle("-fx-padding: 10 10 10 10;  -fx-background-color:    linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),    linear-gradient(#020b02, #3a3a3a),    linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),    linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%),    linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);    -fx-background-insets: 0,1,4,5,6;    -fx-background-radius: 9,8,5,4,3;");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() - 100;
        //double height = screenSize.getHeight()- 100;
        double panelWidth = (width)/2 - 50;
        double panelHeight = (9*panelWidth)/16;
        double panelX = width-panelWidth-50-20;
        double panelY = 0.0;
        x1 =panelX;
        y2 =panelY;
        w = (int)panelWidth;
        h = (int)panelHeight;
        System.out.println(w+" "+h);
 		 Rectangle rectangle = new Rectangle();
		 //BorderPane root = new BorderPane();
		 //Scene scene = new Scene(grid,width,height);
		 //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 
		 File imagePath1 = new File("car.jpg");
	     Image image1 = new Image(imagePath1.toURI().toString());
	     ImageView imageView = new ImageView();
	     imageView.setId("imageView");        
	     imageView.setImage(image1);
	     imageView.setFitWidth(panelWidth);
	     imageView.setFitHeight(panelHeight);
	     imageView.setPreserveRatio(false);
	     imageView.setSmooth(true);
	     imageView.setCache(true);
	     
	     imageView.setOnMousePressed(new EventHandler <MouseEvent>()
	        {
	            public void handle(MouseEvent event)
	            {
	        	    imageView.setMouseTransparent(true);
	        	    x1 = event.getSceneX() ;
	        	    y1 = event.getSceneY();
	  		    	System.out.println(x1+" "+y1);
	        	    event.setDragDetect(true);
	   		    }
	  		});
	        
	     imageView.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					double tx1=x1,ty1=y1,tx2=0,ty2=0;
					double tempX=0,tempY=0;
					
					if(event.getSceneX()<panelX || event.getSceneX()>(panelX+panelWidth) || event.getSceneY()<0 || event.getSceneY()>(panelY+panelHeight)){
						if(event.getSceneX()<panelX){
							tempX = 0;
							tempY = event.getSceneY();
						}
						else if(event.getSceneX()>(panelX+panelWidth)){
							tempX = panelX+panelWidth;
							tempY = event.getSceneY();
						}
						if(event.getSceneY()<panelY){
							tempX = event.getSceneX();
							tempY = 0;
						}
						else if(event.getSceneY()>(panelY+panelHeight)){
							tempX = event.getSceneX();
							tempY = panelY+panelHeight;
						}
					}
					else{
						tempX = event.getSceneX();
						tempY = event.getSceneY();
					}
					
					if(tempX<tx1 && tempY<ty1){
						tx2 = tx1;
						tx1 = tempX;
						ty2 = ty1;
						ty1 = tempY;							
					}
					else if(tempX<tx1 && tempY>ty1){
						tx2 = tx1;
						tx1 = tempX;
						ty2 = tempY;
					}
					else if(tempX>tx1 && tempY<ty1){
						tx2 = tempX;
						ty2 = ty1;
						ty1 = tempY;
					}
					else if(tempX>tx1 && tempY>ty1){
						tx2 = tempX;
						ty2 = tempY;	
					}
					
	  		    	ulx = (int)tx1;
	  		    	uly = (int)ty1;
	  		    	w =(int) (tx2-tx1);
	  		    	h = (int)(ty2-ty1);
					rectangle.setX(ulx);
		  	        rectangle.setY(uly);
		  	        rectangle.setWidth(w);
		  	        rectangle.setHeight(h);
		  	        rectangle.setFill(Color.TRANSPARENT);
		  	        rectangle.setStroke(Color.WHITE);
		  	        rectangle.setStrokeWidth(2);
		  	        rectangle.getStrokeDashArray().addAll(7d, 5d);	
				}
			});
	     
	     imageView.setOnMouseReleased(new EventHandler <MouseEvent>()
	  		{
	  		    public void handle(MouseEvent event)
	  		    {
	  		    	imageView.setMouseTransparent(false);
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
	  		    	
	  		    	ulx = (int)x1;
	  		    	uly = (int)y1;
	  		    	w =(int) (x2-x1);
	  		    	h = (int)(y2-y1);
	  		    	System.out.println(x1+" "+y1+" "+x2+" "+y2+" "+w+" "+h);
	  		    	filename = String.valueOf(x1)+"_"+String.valueOf(y1)+"_"+String.valueOf(w)+"_"+String.valueOf(h);
	  		    	rectangle.setX(ulx);
	  	            rectangle.setY(uly);
	  	            rectangle.setWidth(w);
	  	            rectangle.setHeight(h);
	  	            rectangle.setFill(Color.TRANSPARENT);
	  	            rectangle.setStroke(Color.WHITE);
	  	            rectangle.setStrokeWidth(2);
	  	            rectangle.getStrokeDashArray().addAll(7d, 5d);
	        	}
	   		});
	     
	     Group group = new Group(imageView, rectangle);
	     
	     HBox option = new HBox();
	     Button save = new Button("SAVE");
	     save.setOnAction(new EventHandler<ActionEvent>() {
	        	public void handle(ActionEvent e){
	        		double x = x1-panelX;
	        		double y = y1-panelY;
	    	    	System.out.println(x+" "+y);
	        		WritableImage image = imageView.snapshot(new SnapshotParameters(), null);
	        		PixelReader reader = image.getPixelReader();
	        		WritableImage newImage = new WritableImage(reader, (int)(x), (int)(y), w, h);
	        		    // TODO: probably use a file chooser here
	        		filename = String.valueOf(x)+"_"+String.valueOf(y)+"_"+String.valueOf(w)+"_"+String.valueOf(h);
	        		    File file = new File(filename+".png");
	        		    try {
	        		        ImageIO.write(SwingFXUtils.fromFXImage(newImage, null), "png", file);
	        		    } catch (IOException ec) {
	        		        // TODO: handle exception here
	        		    }
	        	}
			});
	     
	     option.getChildren().add(save);
	     			     
	     
	     setCenter(imageView);
	     setBottom(option);
	     //setCenter(rectangle);
	}

}
