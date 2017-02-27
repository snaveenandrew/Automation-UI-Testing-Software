package application;
	
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	MediaView mediaView;
	Duration duration;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			File file = new File("db_design.mp4");
	        Media media = new Media (file.toURI().toString());
	        MediaPlayer mediaPlayer = new MediaPlayer(media);
	        //mediaPlayer.setAutoPlay(true);
	        mediaView = new MediaView(mediaPlayer);
	        //Thread.sleep(10000);
	        // this.capture();
	        //mediaPlayer.seek(duration.millis(80000));
	        
	        while(mediaPlayer.getStatus() == Status.UNKNOWN){
	        System.out.print(mediaPlayer.getStatus());
	        }
	        mediaPlayer.setOnReady(new Runnable() {

	            @Override
	            public void run() {
	            	System.out.print(mediaPlayer.getStatus());
	                
	            }
	        });
	        Button capture = new Button("Click");
	        capture.setOnAction(new EventHandler<ActionEvent>() {
	        	public void handle(ActionEvent e){
	        		 WritableImage image = mediaView.snapshot(new SnapshotParameters(), null);
	        		    // TODO: probably use a file chooser here
	        		    File file = new File("capture.png");
	        		    try {
							ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        	}
			});
	        root.setTop(mediaView);
	        root.setBottom(capture);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void capture(){
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
