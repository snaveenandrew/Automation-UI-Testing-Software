package application;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class second extends BorderPane{
	public second(Main m){
		
		Button b = new Button("Change image");
		b.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
	         @Override
	            public void handle(MouseEvent event) {
	        	 if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
	        		 first f = new first(new Image("file:image\\sample2.jpg"),m);
	        	 }
	         }
	     });
		setTop(b);
	}
}
