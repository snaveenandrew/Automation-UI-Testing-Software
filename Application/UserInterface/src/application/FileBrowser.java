package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
		Measure measure = new Measure();
		
		 GridPane grid = new GridPane();
	        grid.setStyle("-fx-background-color:  #fff;");
	        grid.setAlignment(Pos.TOP_LEFT); 
	        grid.setHgap(30); 
	        grid.setVgap(10); 
	        grid.setPadding(new Insets(0, 10, 10, 10));
	
		 VBox vbox = new VBox();
		 vbox.setPadding(new Insets(10, 0, 0, 0));
		 vbox.setSpacing(10.0);
		 vbox.setMinWidth(measure.panelWidth);
		 vbox.setAlignment(Pos.TOP_RIGHT);
		 
		 HBox hbox = new HBox();
		 hbox.setSpacing(10.0);
		 hbox.setAlignment(Pos.TOP_RIGHT);
		 
		 TextField filename = new TextField ();
		 filename.setMaxWidth(measure.panelWidth);
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
		 
		 grid.add(vbox, 0, 0);
		 
		 HBox rhbox = new HBox();
		 VBox rvbox = new VBox();
		 rvbox.setSpacing(15.0);
		 Button graph = new Button();
		 graph.setGraphic(setIcon("plugins\\icons\\graph.jpg"));
		 graph.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 10, 0 , 0 , 3 );");
		 Button log = new Button();
		 log.setGraphic(setIcon("plugins\\icons\\log.jpg"));
		 log.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 10, 0 , 0 , 3 );");
		 
		 log.setOnMouseClicked(new EventHandler <MouseEvent>()
	        {
	            public void handle(MouseEvent event)
	            {	
	            	
	            	File sfile = new File("logData.txt");
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

		 rhbox.getChildren().addAll(graph,log);
		 
		 Button send = new Button("Send");
		 send.setStyle("-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); -fx-background-radius: 30; -fx-background-insets: 0,1,1; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
		 rvbox.getChildren().addAll(rhbox,send);
		 
		 //CURSOR
		 log.setCursor(Cursor.HAND);
		 graph.setCursor(Cursor.HAND);
		 send.setCursor(Cursor.HAND);
		 browse.setCursor(Cursor.HAND);
		 run.setCursor(Cursor.HAND);
		 
		 grid.add(rvbox, 1, 0);
		 setTop(grid);
		  
	}
	private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
	public ImageView setIcon(String path){
    	Image image = new Image(new File(path).toURI().toString());
    	ImageView view = new ImageView(image);
        view.setFitWidth(100);
        view.setFitHeight(100);
    	return view;
    }
}
