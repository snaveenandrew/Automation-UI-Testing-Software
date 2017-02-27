package application;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ImagePreview extends BorderPane {
	
	String filename;
	ImageView imageView;
	Image image;
	int x=0,y=0,w,h;
	Group group = new Group();
	Measure measure = new Measure();
	
	public ImagePreview(String file){
		imageView = new ImageView();
		image = new Image("file:"+file,measure.panelWidth,measure.panelHeight,false,false);
		ImagePanel();
		
	}
	public ImagePreview(){
		imageView = new ImageView();
		image = new Image("file:plugins\\capture\\capture.png");
		
		///#############
		System.out.println(image);
		ImagePanel();
	}
	 public void ImagePanel() {
		w = (int) measure.panelWidth;
		h = (int) measure.panelHeight;
		setStyle("-fx-padding: 10 10 10 10;  -fx-background-color:    linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),    linear-gradient(#020b02, #3a3a3a),    linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),    linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%),    linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);    -fx-background-insets: 0,1,4,5,6;    -fx-background-radius: 9,8,5,4,3;");
		Pane imageViewParent = new Pane();
		Rectangle rectBound = new Rectangle();
		rectBound.setFill(Color.TRANSPARENT);
	    rectBound.setStroke(Color.GOLD);     
	    ///#############
	    System.out.println(image);
	     
	     HBox hbox =new HBox();
	     
	     group.getChildren().add(imageView);
	     
	     imageView.setImage(image);
	     imageView.setFitWidth(measure.panelWidth);
	     imageView.setFitHeight(measure.panelHeight);
	     imageView.setPreserveRatio(true);
	     imageView.setSmooth(true);
	     imageView.setCache(true);
	     imageView.setLayoutX(0.0);
	     imageView.setLayoutY(0.0);
	     
	     hbox.getChildren().add(imageView);
	     hbox.getChildren().add(group);
	     
	     imageViewParent.getChildren().add(hbox);
	     
	     imageView.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
	         @Override
	            public void handle(MouseEvent event) {
	
	                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
	                    if (rectBound.getParent() == null) {
	                        rectBound.setWidth(0.0); 
	                        rectBound.setHeight(0.0);
	                        rectBound.setLayoutX(event.getX()); 
	                        rectBound.setLayoutY(event.getY());
	                        x = (int) rectBound.getLayoutX();
		                	y = (int) rectBound.getLayoutY();
		                	// setX or setY
	                        group.getChildren().add(rectBound);
	                    }
	                } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
	
	                } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
	                    rectBound.setWidth(event.getX() - rectBound.getLayoutX());
	                    rectBound.setHeight(event.getY() - rectBound.getLayoutY());
	                    w = (int) rectBound.getWidth();
	                	h = (int) rectBound.getHeight();
	                } else if (event.getEventType() == MouseEvent.MOUSE_CLICKED
	                        && event.getButton() == MouseButton.SECONDARY) {
	
	                    if (rectBound.getParent() != null) {
	                        group.getChildren().remove(rectBound);
	                    }
	                } else if (event.getEventType() == MouseEvent.MOUSE_CLICKED
	                        && event.getButton() == MouseButton.PRIMARY && event.getClickCount() > 1) {
	                    //////////////// i crop here //////////////
	                    
	                	x = (int) rectBound.getLayoutX();
	                	y = (int) rectBound.getLayoutY();
	                	w = (int) rectBound.getWidth();
	                	h = (int) rectBound.getHeight();
	                	PixelReader reader = imageView.getImage().getPixelReader();
	                    WritableImage newImage = new WritableImage(reader, (int) rectBound.getLayoutX(),
	                            (int) rectBound.getLayoutY(),
	                            (int) rectBound.getWidth(),
	                            (int) rectBound.getHeight());
	
	                    File file = new File("image.png"); 
	                    try {
							ImageIO.write(SwingFXUtils.fromFXImage(newImage, null), "png", file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    //root.getChildren().add(new ImageView(newImage));
	                }
	
	            }
	    });
	     
	     setTop(imageViewParent);
	
	     group = new Group(imageView, rectBound);
	
	     HBox option = new HBox();
	     option.setAlignment(Pos.CENTER);
	     
	     TextField Name = new TextField ();
	     
	     Button save = new Button();
	     save.setGraphic(setIcon("plugins\\icons\\saveBlack.png"));
	     
	     save.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent e){
	                WritableImage image = imageView.snapshot(new SnapshotParameters(), null);
	                PixelReader reader = image.getPixelReader();
	                WritableImage newImage = new WritableImage(reader, (int)(x), (int)(y), w, h);
	                    // TODO: probably use a file chooser here
	                filename = Name.getText().toString()+"_"+String.valueOf(x)+"_"+String.valueOf(y)+"_"+String.valueOf(w)+"_"+String.valueOf(h);
	                    File file = new File(filename+".png");
	                    try {
	                        ImageIO.write(SwingFXUtils.fromFXImage(newImage, null), "png", file);
	                    } catch (IOException ec) {
	                        // TODO: handle exception here
	                    }
	            }
	        }); 
	     option.getChildren().add(Name);
	     option.getChildren().add(save);        
        setCenter(group);
        setBottom(option);
    }
  
	
	public ImageView setIcon(String path){
    	Image image = new Image(new File(path).toURI().toString());
    	ImageView view = new ImageView(image);
        view.setFitWidth(40);
        view.setFitHeight(40);
    	return view;
    }
    
	
}
