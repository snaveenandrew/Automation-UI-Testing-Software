package application;
	
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Pane imageViewParent = new Pane();
			Rectangle rectBound = new Rectangle();
			rectBound.setFill(Color.TRANSPARENT);
		    rectBound.setStroke(Color.GOLD);
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Group group = new Group();
			
		     Image image = new Image("file:sample.png",400,400,false,false);
		     System.out.print(image);
		     ImageView imageView1 = new ImageView(image);
		     ImageView imageView2 = new ImageView(image);
		     
		     HBox hbox =new HBox();
		     
		     group.getChildren().add(imageView1);
		     
		     imageView2.setImage(image);
		     imageView2.setFitWidth(400);
		     imageView2.setFitHeight(400);
		     imageView2.setPreserveRatio(true);
		     imageView2.setSmooth(true);
		     imageView2.setCache(true);
		     imageView2.setLayoutX(0.0);
		     imageView2.setLayoutY(0.0);
		     
		     imageView1.setImage(image);
		     imageView1.setFitWidth(400);
		     imageView1.setFitHeight(400);
		     imageView1.setPreserveRatio(true);
		     imageView1.setSmooth(true);
		     imageView1.setCache(true);
		     imageView1.setLayoutX(0.0);
		     imageView1.setLayoutY(0.0);
		     
		     hbox.getChildren().add(imageView2);
		     hbox.getChildren().add(group);
		     
		     imageViewParent.getChildren().add(hbox);
		     
		     imageView1.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
		         @Override
		            public void handle(MouseEvent event) {

		                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
		       		     //System.out.print("MP");
		                    if (rectBound.getParent() == null) {
		                        rectBound.setWidth(0.0); 
		                        rectBound.setHeight(0.0);
		                        rectBound.setLayoutX(event.getX()); 
		                        rectBound.setLayoutY(event.getY()); // setX or setY
		                        group.getChildren().add(rectBound);
		                    }
		                } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {

		                	System.out.println(rectBound.getLayoutX()+" "+rectBound.getLayoutY()+" "+rectBound.getWidth()+" "+rectBound.getHeight());
		                } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {

		                    rectBound.setWidth(event.getX() - rectBound.getLayoutX());
		                    rectBound.setHeight(event.getY() - rectBound.getLayoutY());
		                } else if (event.getEventType() == MouseEvent.MOUSE_CLICKED
		                        && event.getButton() == MouseButton.SECONDARY) {

		                    if (rectBound.getParent() != null) {
		                        group.getChildren().remove(rectBound);
		                    }
		                } else if (event.getEventType() == MouseEvent.MOUSE_CLICKED
		                        && event.getButton() == MouseButton.PRIMARY && event.getClickCount() > 1) {
		                	System.out.println("MC P");
		                    //////////////// i crop here //////////////
		                    PixelReader reader = imageView1.getImage().getPixelReader();
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
		     
		     root.setTop(imageViewParent);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
