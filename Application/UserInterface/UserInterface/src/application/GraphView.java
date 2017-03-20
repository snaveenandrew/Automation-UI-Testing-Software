package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GraphView extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Measure measure = new Measure();
		primaryStage.setTitle("STB Automation Testing Software");
	    primaryStage.getIcons().add(new Image("file:plugins//icons//logo2.png"));
	    Group root = new Group();
	    Scene scene = new Scene(root, measure.width, measure.height);
	    scene.getStylesheets().add (Main.class.getResource("application.css").toExternalForm());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

}
