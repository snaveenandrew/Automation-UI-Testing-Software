package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GraphView extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		String string;
		
		Measure measure = new Measure();
		primaryStage.setTitle("STB Automation Testing Software - Log Graph");
	    primaryStage.getIcons().add(new Image("file:plugins//icons//logo2.png"));
	    Group root = new Group();
	    Scene scene = new Scene(root, measure.width, measure.height);
	    scene.getStylesheets().add (Main.class.getResource("application.css").toExternalForm());
	    
	    GridPane grid = new GridPane();
	    grid.setStyle("-fx-background-color:  #fff;");
        grid.setAlignment(Pos.TOP_LEFT); 
        grid.setHgap(20); 
        grid.setVgap(10); 
        grid.setPadding(new Insets(0, 10, 10, 10));
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("TestCase");     
        yAxis.setLabel("Percentage");
        
        final CategoryAxis xAxis1 = new CategoryAxis();
        final NumberAxis yAxis1 = new NumberAxis();
        xAxis1.setLabel("TestCase");     
        yAxis1.setLabel("Percentage");
        
        final CategoryAxis xAxis2 = new CategoryAxis();
        final NumberAxis yAxis2 = new NumberAxis();
        xAxis2.setLabel("TestCase");     
        yAxis2.setLabel("Percentage");
        
        final CategoryAxis xAxis3 = new CategoryAxis();
        final NumberAxis yAxis3 = new NumberAxis();
        xAxis3.setLabel("TestCase");     
        yAxis3.setLabel("Percentage");
        
        BufferedReader br = new BufferedReader(new FileReader("E:\\Final year project\\UserInterface\\Data\\graphData.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    string = sb.toString();
		    System.out.println(string);
	
		} finally {
		    br.close();
		}
		
		StringTokenizer st = new StringTokenizer(string);
		
		final LineChart<String,Number> lineChart1 = new LineChart<String,Number>(xAxis,yAxis);                
        lineChart1.setTitle("Image Comparison");
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Image Test");
	    	    
	    final LineChart<String,Number> lineChart2 = new LineChart<String,Number>(xAxis1,yAxis1);                
        lineChart2.setTitle("Text Matching");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Text Test");
	    
	    final LineChart<String,Number> lineChart3 = new LineChart<String,Number>(xAxis2,yAxis2);                
        lineChart3.setTitle("Audio Detection");
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("Audio Test");
	    	    
	    final LineChart<String,Number> lineChart4 = new LineChart<String,Number>(xAxis3,yAxis3);                
        lineChart4.setTitle("Video Detection");
		XYChart.Series series4 = new XYChart.Series();
		series4.setName("Video Test");
	    
		int i=0;
	    while (st.hasMoreTokens()){
	    	Double data = Double.parseDouble(st.nextToken().toString());
	    	System.out.println(data);
	    	series4.getData().add(new XYChart.Data(String.valueOf(i), data));
	    	data = Double.parseDouble(st.nextToken().toString());
	    	System.out.println(data);
	    	series3.getData().add(new XYChart.Data(String.valueOf(i), data));
	    	data = Double.parseDouble(st.nextToken().toString());
	    	System.out.println(data);
	    	series2.getData().add(new XYChart.Data(String.valueOf(i), data));
	    	st.nextToken();
	    	data = Double.parseDouble(st.nextToken().toString());
	    	System.out.println(data);
	    	series1.getData().add(new XYChart.Data(String.valueOf(i), data));
	    	i++;
	    }
	    
	    lineChart1.getData().add(series1);
	    lineChart2.getData().add(series2);
	    lineChart3.getData().add(series3);
	    lineChart4.getData().add(series4);
	    
	    grid.add(lineChart1, 0 ,0);
	    grid.add(lineChart2, 1 ,0);
	    grid.add(lineChart3, 0 ,1);
	    grid.add(lineChart4, 1 ,1);
	    scene.setRoot(grid);
	    
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

}
