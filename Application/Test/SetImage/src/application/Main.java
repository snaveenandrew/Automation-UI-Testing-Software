package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	GridPane grid;
	first first;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,440,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image i = new Image("file:image\\sample1.jpg");
			System.out.println(i);
			
			 grid = new GridPane();
	        //grid.setStyle("-fx-background-color:  #373737;");
	        grid.setAlignment(Pos.TOP_LEFT); 
	        grid.setHgap(20); 
	        grid.setVgap(10); 
	        grid.setPadding(new Insets(0, 10, 10, 10));
			
			first = new first(i,this);
			second second = new second(this);
			grid.add(first, 0 , 0);
			grid.add(second, 0 , 1);
			root.setTop(grid);
			
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void remove(){
		grid.getChildren().remove(first);
	}
	public void add(){
		grid.add(first, 0 , 0);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
