package application;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class first extends BorderPane {
	public first(Image image,Main m){

		//m.remove();
		ImageView i = new ImageView();
		i.setImage(image);
		System.out.println(image);
		
		
		Button b = new Button("Change image");
		b.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
	         @Override
	            public void handle(MouseEvent event) {
	        	 if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
	        		 i.setImage(new Image("file:image\\sample2.jpg"));
	        	 }
	         }
	     });
		
		setBottom(b);
		setTop(i);
		//m.first = this;
		//m.add();
	}

}
