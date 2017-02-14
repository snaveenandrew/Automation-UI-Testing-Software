package application;
	
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
    @Override
    public void start(Stage primaryStage) {
    	
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() - 100;
        double height = screenSize.getHeight() - 100;
        //double panelWidth = (width)/2 - 50;
        //double panelHeight = (9*panelWidth)/16;
        
    	
        primaryStage.setTitle("STB Automation Testing Software");
        primaryStage.getIcons().add(new Image("file:plugins//icons//icon.png"));
        Group root = new Group();
        Scene scene = new Scene(root, width, height);
        
        scene.getStylesheets().add (Main.class.getResource("application.css").toExternalForm());
        
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color:  #373737;");
        grid.setAlignment(Pos.TOP_LEFT); 
        grid.setHgap(20); 
        grid.setVgap(10); 
        grid.setPadding(new Insets(0, 10, 10, 10));
// Label Video Player
        Text playerLabel = new Text("Video Player"); 
        playerLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        playerLabel.setId("heading");
        grid.add(playerLabel, 0 , 0);
// Label Preview Panel
        Text previewLabel = new Text("Preview Panel"); 
        previewLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        previewLabel.setId("heading");
        grid.add(previewLabel, 1 ,0 );
// Media Player   
        File file = new File("plugins\\video\\chinese.mp4");
        Media media = new Media (file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaControl mediaControl = new MediaControl(mediaPlayer);
        grid.add(mediaControl, 0, 1);
// Image View
        ImagePreview preview = new ImagePreview();
        File imagePath = new File("plugins//image//sample.png");
        Image image = new Image(imagePath.toURI().toString());        
        //imageView.setId("imageView");
        preview.ImagePreviewPanel();
        preview.setImageView(image);
        grid.add(preview, 1, 1);
//File Browser
        FileBrowser fileBrowser = new FileBrowser();
        grid.add(fileBrowser, 0, 2);
        
        scene.setRoot(grid);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}