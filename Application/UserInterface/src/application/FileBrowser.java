package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileBrowser extends BorderPane {
	//Stage stage = new Stage();
	File file = null;
	private Desktop desktop = Desktop.getDesktop();
	public FileBrowser() {
		//setAlignment(getBottom(), Pos.BASELINE_RIGHT);
		// TODO Auto-generated constructor stub
		 VBox vbox = new VBox();
		 vbox.setPadding(new Insets(10, 10, 10, 10));
		 vbox.setSpacing(10.0);
		 vbox.setAlignment(Pos.TOP_RIGHT);
		 
		 HBox hbox = new HBox();
		 hbox.setSpacing(10.0);
		 hbox.setAlignment(Pos.TOP_RIGHT);
		 
		 TextField filename = new TextField ();
		 filename.setStyle("-fx-text-fill:#000; -fx-border-color: #000; -fx-background-color:  #fff; -fx-border-radius: 30;-fx-font-family: \"Helvetica\";-fx-font-size: 14px;-fx-font-weight: bold;");
		 
		 Button browse = new Button("Browse");
		 browse.setStyle("-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); -fx-background-radius: 30; -fx-background-insets: 0,1,1; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");        
		 Button run = new Button("Run Test");
		 run.setStyle("-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); -fx-background-radius: 30; -fx-background-insets: 0,1,1; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");

		 
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Python Script File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Python Script[*.py,*.pyw]", "*.py","*.pyw"));
		 
		 
		 browse.setOnMouseClicked(new EventHandler <MouseEvent>()
	        {
	            public void handle(MouseEvent event)
	            {
					File file = fileChooser.showOpenDialog(new Stage());
                    if (file != null) {
                        String filePath = file.getAbsolutePath();
                        filename.setText(filePath);
                    }
	            }
	        });
		 run.setOnMouseClicked(new EventHandler <MouseEvent>()
	        {
	            public void handle(MouseEvent event)
	            {	
	            	String Path = filename.getText().toString();
	            	File sfile = new File(Path);
	            	if (sfile.exists()) {
	            		openFile(sfile);
	            	}
	            	else{
	            		Alert alert = new Alert(AlertType.ERROR);
	            		alert.setTitle("Error Dialog");
	            		alert.setHeaderText(null);
	            		alert.setContentText("Please select a script a script file to run");
	            		alert.showAndWait();
	            	}
	            }
	        });
		 
		 hbox.getChildren().addAll(browse,run);
		 vbox.getChildren().addAll(filename,hbox);
		 //vbox.getChildren().add(browse);
		 setTop(vbox);
		  
	}
	private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
