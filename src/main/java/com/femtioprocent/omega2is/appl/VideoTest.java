package com.femtioprocent.omega2is.appl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;

public class VideoTest extends Application {

    static Scene scene;
    static Group root;
    static JFrame frame;

    private static final String MEDIA1_URL = "file:/Users/lars/project/omega/omega2_dev/github/omega2is/invoke/MOV01445.mp4";
    private static final String MEDIA2_URL = "file:MOV01445.mp4";
    private static final String MEDIA0_URL = "MOV01445.mp4";
    private static final String MEDIA_URL = MEDIA2_URL;

    private JFXPanel initAndShowGUI() {
	// This method is invoked on the EDT thread
	JFrame frame = new JFrame("Swing and JavaFX");
	VideoTest.frame = frame;

	final JFXPanel fxPanel = new JFXPanel();
	frame.add(fxPanel);
	frame.setSize(800, 600);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	Platform.runLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    initFX(fxPanel);
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
	    }
	});
	return fxPanel;
    }

    private void initFX(JFXPanel fxPanel) throws URISyntaxException {
	// This method is invoked on the JavaFX thread
	Scene scene = createScene();
	VideoTest.scene = scene;
	fxPanel.setScene(scene);

	File file = new File(MEDIA0_URL);
	String uu = file.toURI().toString();

	String u = getClass().getResource(MEDIA0_URL).toURI().toString();

	System.err.println("U is " + u);
	System.err.println("UU is " + uu);

	MediaPlayer player = new MediaPlayer(new Media(uu));
	MediaView mediaView = new MediaView(player);
	mediaView.setX(0);
	mediaView.setY(0);
	root.getChildren().add(mediaView);

//        DoubleProperty mvw = mv.fitWidthProperty();
//        DoubleProperty mvh = mv.fitHeightProperty();
//        mvw.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
//        mvh.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
//        mv.setPreserveRatio(true);
//
//        mediaView.setTranslateX(0);
//        mediaView.setTranslateY(0);
	//        Scene scene = new Scene(root, 1024, 768);
	//        fxPanel.setScene(scene);

	//        primaryStage.setMaximized(true);
	//        primaryStage.setFullScreen(true);
	//        primaryStage.setScene(scene);
	//        primaryStage.show();


	player.play();
	player.setOnReady(new Runnable() {
	    @Override
	    public void run() {
		int w = player.getMedia().getWidth();
		int h = player.getMedia().getHeight();

		Dimension d = frame.getSize();
		mediaView.setTranslateX((d.getWidth() - w) / 2.0);
		mediaView.setTranslateY((d.getHeight() - h) / 2.0);
		System.out.println("---++-- " + d + ' ' + w + ' ' + h);


	    }
	});

	Runtime.getRuntime().addShutdownHook(new Thread() {
	    @Override
	    public void run() {
		player.stop();
	    }
	});

    }

    private static Scene createScene() {
	Group root = new Group();
	Scene scene = new Scene(root, Color.BISQUE);
	Text text = new Text();

	text.setX(240);
	text.setY(100);
	text.setFont(new Font(25));
	text.setText("Welcome JavaFX!");

	root.getChildren().add(text);

	VideoTest.root = root;

	return (scene);
    }


    @Override
    public void start(Stage primaryStage) {
	System.err.println("Java Home: " + System.getProperty("java.home"));
	System.err.println("User Home: " + System.getProperty("user.home"));
	System.err.println("User dir: " + System.getProperty("user.dir"));
	JFXPanel fxP = initAndShowGUI();
    }

    public void start2(Stage primaryStage) {

	StackPane root = new StackPane();

	MediaPlayer player = new MediaPlayer(new Media(MEDIA_URL));
	MediaView mediaView = new MediaView(player);
	root.getChildren().add(mediaView);

	Scene scene = new Scene(root, 1024, 768);

//        primaryStage.setMaximized(true);
//        primaryStage.setFullScreen(true);
	//primaryStage.setScene(scene);
	//primaryStage.show();


	player.play();

	Runtime.getRuntime().addShutdownHook(new Thread() {
	    @Override
	    public void run() {
		player.stop();
	    }
	});
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	launch(args);
    }
}
