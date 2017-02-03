package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ImagePreview extends BorderPane {
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth() - 100;
    double height = screenSize.getHeight() - 100;
    double panelWidth = (width)/2 - 50;
    double panelHeight = (9*panelWidth)/16;
    public ImagePreview() {
		// TODO Auto-generated constructor stub
    	setStyle("-fx-padding: 10 10 10 10;  -fx-background-color:    linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),    linear-gradient(#020b02, #3a3a3a),    linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),    linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%),    linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);    -fx-background-insets: 0,1,4,5,6;    -fx-background-radius: 9,8,5,4,3;");
    	File imagePath = new File("plugins//image//car.jpg");
        Image image = new Image(imagePath.toURI().toString());
        ImageView imageView = new ImageView();
        imageView.setId("imageView");        
        imageView.setImage(image);
        imageView.setFitWidth(panelWidth);
        imageView.setFitHeight(panelHeight);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        
        setCenter(imageView);
    }
	
}
