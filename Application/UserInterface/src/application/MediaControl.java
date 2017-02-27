package application;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MediaControl extends BorderPane {
	
	String filename;
	ImageView imageView;
	Image image;
	int x=0,y=0,w,h;
	Group group = new Group();
	Measure measure = new Measure();
	
    private MediaPlayer mp;
    private MediaView mediaView;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private Slider timeSlider;
    private Label playTime;
    private Slider volumeSlider;
    private HBox mediaBar;

    public MediaControl(final MediaPlayer mp) {
    	    	
        this.mp = mp;
    	        
        
    	mediaView = new MediaView(mp);
        mediaView.setFitWidth(measure.panelWidth); 
        mediaView.setFitHeight(measure.panelHeight); 
        mediaView.setPreserveRatio(false);
       
        GridPane mvPane = new GridPane(); 
        mvPane.setAlignment(Pos.CENTER); 
        mvPane.setHgap(10); 
        mvPane.setVgap(10);
        mvPane.getChildren().add(mediaView);
        mvPane.setStyle("-fx-background-color: black;");
        mvPane.setAlignment(Pos.CENTER);

        mediaBar = new HBox();
        mediaBar.setId("mediaBar");
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        BorderPane.setAlignment(mediaBar, Pos.CENTER);
        	
        //Play / Pause Button
        final Button playButton = new Button();
        playButton.setGraphic(setIcon("plugins\\icons\\playBlack.png"));
        //Capture Button
        final Button capture = new Button();
        capture.setGraphic(setIcon("plugins\\icons\\cameraBlack.png"));
        // Add spacer
        Label spacer1 = new Label("   ");
        Label spacer = new Label("   ");
        // Add Time label
        Label timeLabel = new Label("Time: ");
        timeLabel.setId("Time");
        // Add time slider
        timeSlider = new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);
        // Add Play label
        playTime = new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);
        playTime.setId("Time");
        // Add the volume label
        Label volumeLabel = new Label();
        volumeLabel.setGraphic(setIcon("plugins\\icons\\volumeBlack.png"));
        // Add Volume slider
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        
//Funtionalities
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Status status = mp.getStatus();
                if (status == Status.UNKNOWN || status == Status.HALTED) {
                    // don't do anything in these states
                    return;
                }
                if (status == Status.PAUSED
                        || status == Status.READY
                        || status == Status.STOPPED) {
                    // rewind the movie if we're sitting at the end
                    if (atEndOfMedia) {
                        mp.seek(mp.getStartTime());
                        atEndOfMedia = false;
                    }
                    mp.play();
                } else {
                    mp.pause();
                }
            }
        });
        capture.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e){
        		 WritableImage image = mediaView.snapshot(new SnapshotParameters(), null);

        		    // TODO: probably use a file chooser here
        		    File file = new File("plugins\\capture\\capture.png");
        		    try {
						ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		    imageView.setImage(new Image("file:"+file));
        	}
		});
        mp.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });
        mp.setOnPlaying(new Runnable() {
            public void run() {
                if (stopRequested) {
                    mp.pause();
                    stopRequested = false;
                } else {
                    playButton.setGraphic(setIcon("plugins\\icons\\pauseBlack.png"));
                }
            }
        });
        mp.setOnPaused(new Runnable() {
            public void run() {
                playButton.setGraphic(setIcon("plugins\\icons\\playBlack.png"));
            }
        });
        mp.setOnReady(new Runnable() {
            public void run() {
                duration = mp.getMedia().getDuration();
                updateValues();
            }
        });
        mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    playButton.setGraphic(setIcon("plugins\\icons\\playBlack.png"));
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
        });
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mp.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });
        
//Adding to media bar child nodes
        mediaBar.getChildren().add(playButton);
        mediaBar.getChildren().add(spacer);
        mediaBar.getChildren().add(capture);
        mediaBar.getChildren().add(spacer1);
        mediaBar.getChildren().add(timeLabel);
        mediaBar.getChildren().add(timeSlider);
        mediaBar.getChildren().add(playTime);
        mediaBar.getChildren().add(volumeLabel);
        mediaBar.getChildren().add(volumeSlider);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(mvPane,mediaBar);
        vbox.setStyle("-fx-padding: 10 10 10 10;  -fx-background-color:    linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),    linear-gradient(#020b02, #3a3a3a),    linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),    linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%),    linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);    -fx-background-insets: 0,1,4,5,6;    -fx-background-radius: 9,8,5,4,3;");
        setLeft(vbox);
        
        //Image Panel
        imageView = new ImageView();
		image = new Image("file:plugins\\capture\\capture.png");
        VBox v2 = new VBox();
        w = (int) measure.panelWidth;
		h = (int) measure.panelHeight;
		v2.setStyle("-fx-padding: 10 10 10 10;  -fx-background-color:    linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),    linear-gradient(#020b02, #3a3a3a),    linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),    linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%),    linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);    -fx-background-insets: 0,1,4,5,6;    -fx-background-radius: 9,8,5,4,3;");
		Pane imageViewParent = new Pane();
		Rectangle rectBound = new Rectangle();
		rectBound.setFill(Color.TRANSPARENT);
	    rectBound.setStroke(Color.GOLD);     
	    ///#############
	    System.out.println(image);
	     
	     HBox hbox =new HBox();
	     
	     group.getChildren().add(imageView);
	     
	     imageView.setImage(image);
	     imageView.setFitWidth(measure.panelWidth);
	     imageView.setFitHeight(measure.panelHeight);
	     imageView.setPreserveRatio(true);
	     imageView.setSmooth(true);
	     imageView.setCache(true);
	     imageView.setLayoutX(0.0);
	     imageView.setLayoutY(0.0);
	     
	     hbox.getChildren().add(imageView);
	     hbox.getChildren().add(group);
	     
	     imageViewParent.getChildren().add(hbox);
	     
	     imageView.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
	         @Override
	            public void handle(MouseEvent event) {
	
	                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
	                    if (rectBound.getParent() == null) {
	                        rectBound.setWidth(0.0); 
	                        rectBound.setHeight(0.0);
	                        rectBound.setLayoutX(event.getX()); 
	                        rectBound.setLayoutY(event.getY());
	                        x = (int) rectBound.getLayoutX();
		                	y = (int) rectBound.getLayoutY();
		                	// setX or setY
	                        group.getChildren().add(rectBound);
	                    }
	                } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
	
	                } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
	                    rectBound.setWidth(event.getX() - rectBound.getLayoutX());
	                    rectBound.setHeight(event.getY() - rectBound.getLayoutY());
	                    w = (int) rectBound.getWidth();
	                	h = (int) rectBound.getHeight();
	                } else if (event.getEventType() == MouseEvent.MOUSE_CLICKED
	                        && event.getButton() == MouseButton.SECONDARY) {
	
	                    if (rectBound.getParent() != null) {
	                        group.getChildren().remove(rectBound);
	                    }
	                } else if (event.getEventType() == MouseEvent.MOUSE_CLICKED
	                        && event.getButton() == MouseButton.PRIMARY && event.getClickCount() > 1) {
	                    //////////////// i crop here //////////////
	                    
	                	x = (int) rectBound.getLayoutX();
	                	y = (int) rectBound.getLayoutY();
	                	w = (int) rectBound.getWidth();
	                	h = (int) rectBound.getHeight();
	                	PixelReader reader = imageView.getImage().getPixelReader();
	                    WritableImage newImage = new WritableImage(reader, (int) rectBound.getLayoutX(),
	                            (int) rectBound.getLayoutY(),
	                            (int) rectBound.getWidth(),
	                            (int) rectBound.getHeight());
	
	                    File file = new File("image.png"); 
	                    try {
							ImageIO.write(SwingFXUtils.fromFXImage(newImage, null), "png", file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    //root.getChildren().add(new ImageView(newImage));
	                }
	
	            }
	    });
	     
	     
	
	     group = new Group(imageView, rectBound);
	
	     HBox option = new HBox();
	     option.setAlignment(Pos.CENTER);
	     
	     TextField Name = new TextField ();
	     
	     Button save = new Button();
	     save.setGraphic(setIcon("plugins\\icons\\saveBlack.png"));
	     
	     save.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent e){
	                WritableImage image = imageView.snapshot(new SnapshotParameters(), null);
	                PixelReader reader = image.getPixelReader();
	                WritableImage newImage = new WritableImage(reader, (int)(x), (int)(y), w, h);
	                    // TODO: probably use a file chooser here
	                filename = Name.getText().toString()+"_"+String.valueOf(x)+"_"+String.valueOf(y)+"_"+String.valueOf(w)+"_"+String.valueOf(h);
	                    File file = new File(filename+".png");
	                    try {
	                        ImageIO.write(SwingFXUtils.fromFXImage(newImage, null), "png", file);
	                    } catch (IOException ec) {
	                        // TODO: handle exception here
	                    }
	            }
	        }); 
	     option.getChildren().add(Name);
	     option.getChildren().add(save);
	     v2.getChildren().addAll(imageViewParent,group,option);
	     /*setTop(imageViewParent);
	     setCenter(group);
	     setBottom(option);*/
        //-----------
        setRight(v2);
        /*setCenter(mvPane);
        setBottom(mediaBar);*/
    }

    protected void updateValues() {
        if (playTime != null && timeSlider != null && volumeSlider != null) {
            Platform.runLater(new Runnable() {
                @SuppressWarnings("deprecation")
				public void run() {
                    Duration currentTime = mp.getCurrentTime();
                    playTime.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());
                    if (!timeSlider.isDisabled()
                            && duration.greaterThan(Duration.ZERO)
                            && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis()
                                * 100.0);
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int) Math.round(mp.getVolume()
                                * 100));
                    }
                }
            });
        }
    }
    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60
                    - durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d", elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }
    public ImageView setIcon(String path){
    	Image image = new Image(new File(path).toURI().toString());
    	ImageView view = new ImageView(image);
        view.setFitWidth(40);
        view.setFitHeight(40);
    	return view;
    }
}