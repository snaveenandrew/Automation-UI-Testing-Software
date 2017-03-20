package video;

import application.Main;
import javafx.stage.Stage;

public class mpp {
	public static void main(String args[]){
		try{
		Main m = new Main();
		m.start(new Stage());
		}
		catch(Exception e){
			System.out.println(e.toString());			
		}
	}
}
