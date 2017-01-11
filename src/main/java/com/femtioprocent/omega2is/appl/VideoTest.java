package com.femtioprocent.omega2is.appl;

import fpdo.sundry.S;
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
import omega.Context;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class VideoTest /*extends Application*/ {

    static Scene scene;
    static Group root;
    static JFrame frame;
    static JComponent jcomp;
    MediaPlayer player = null;
    boolean initDone = false;
    boolean stopped = false;
    JFXPanel fxPanel = null;

    private static final String MEDIA_FN = Context.omegaAssets("media/feedback/film1/feedback1.mp4");
    private static final String MEDIA_FN2 = Context.omegaAssets("media/feedback/film1/feedback2.mp4");

    private JFXPanel initAndShowGUI() {
        stopped = false;
        initDone = false;
	// This method is invoked on the EDT thread
	JFrame frame = new JFrame("Swing and JavaFX");
	JComponent jcomp = new JPanel();
	jcomp.setLayout(new BorderLayout(0, 0));
	frame.add(jcomp, BorderLayout.CENTER);//setContentPane(jcomp);
	frame.setSize(800,600);
	frame.setVisible(true);

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	return initAndShowGUI(jcomp, MEDIA_FN);
    }

//    private JFXPanel initAndShowGUI(JFrame frame) {
//	// This method is invoked on the EDT thread
//	VideoTest.frame = frame;
//
//	final JFXPanel fxPanel = new JFXPanel();
//	frame.add(fxPanel);
//	frame.setSize(800, 600);
//	frame.setVisible(true);
//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//	Platform.runLater(new Runnable() {
//	    @Override
//	    public void run() {
//		try {
//		    initFX(fxPanel);
//		    play();
//		} catch (URISyntaxException e) {
//		    e.printStackTrace();
//		}
//	    }
//	});
//	return fxPanel;
//    }

    public JFXPanel initAndShowGUI(JComponent jcomp, String fn) {
	System.err.println("enter initAndShowGUI");
	// This method is invoked on the EDT thread
	VideoTest.jcomp = jcomp;
	boolean snd = true;

	if ( fxPanel == null ) {
	    fxPanel = new JFXPanel();
	    fxPanel.setSize(1112, 1112);
	    fxPanel.setLocation(0, 0);
	    jcomp.add(fxPanel);
	    snd = false;
	    //jcomp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	final boolean snd_ = snd;

	Platform.runLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    if ( snd_ ) {
			initFX2(fxPanel, fn);
		    } else {
			initFX(fxPanel, fn);
			initFX2(fxPanel, fn);
		    }
		    player.play();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
		initDone = true;
	    }
	});

	System.err.println("leave initAndShowGUI");
	return fxPanel;
    }

    public void initAndShowGUI2(String fn) {
	System.err.println("enter initAndShowGUI2");
	player.play();
    }

    private void initFX(JFXPanel fxPanel, String fn) throws URISyntaxException {
	System.err.println("enter initFX");
	// This method is invoked on the JavaFX thread
	Scene scene = createScene();
	VideoTest.scene = scene;
	fxPanel.setScene(scene);
    }

    private void initFX2(JFXPanel fxPanel, String fn) throws URISyntaxException {
	File file = new File(fn);
	String uu = file.toURI().toString();
	System.err.println("UU is " + uu);

	Class<? extends VideoTest> aClass = getClass();
	System.err.println("aClass is " + aClass);
	URL resource = null;//aClass.getResource(MEDIA0_URL);
	try {
	    resource = file.toURI().toURL();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	System.err.println("resource is " + resource);
	String u = resource.toURI().toString();
	System.err.println("U is " + u);

	player = new MediaPlayer(new Media(uu));
	MediaView mediaView = new MediaView(player);
	mediaView.setX(0);
	mediaView.setY(0);
	root.getChildren().clear();
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


//	player.play();
	player.setOnReady(new Runnable() {
	    @Override
	    public void run() {
		int w = player.getMedia().getWidth();
		int h = player.getMedia().getHeight();

		Dimension d = jcomp.getSize();
		double xx = (d.getWidth() - w) / 2.0;
		double yy = (d.getHeight() - h) / 2.0;
		mediaView.setTranslateX(xx);
		mediaView.setTranslateY(yy);
		System.out.println("---++-- " + d + ' ' + w + ' ' + h + ' ' + xx + ' ' + yy);
		System.err.println("VP " + mediaView.getX());
//		player.play();
	    }
	});

	player.setOnEndOfMedia(() -> {
	    System.out.println("EOF ");
	    stopped = true;
//	    player.dispose();
	});

	Runtime.getRuntime().addShutdownHook(new Thread() {
	    @Override
	    public void run() {
		try {
		    player.stop();
		} catch (Exception ex) {
		    System.err.println("stoppping " + ex);
		}
		try {
		    player.dispose();
		} catch (Exception ex) {
		    System.err.println("disposing " + ex);
		}
	    }
	});
	System.err.println("leave initFX");
    }

    public void play() {
        if ( true )
            return;
	for(int i = 0; i < 100; i++)
	    if ( initDone )
		break;
	    else
		S.m_sleep(100);

	if ( player != null ) {
	    System.err.println("Play the movie...");
	    player.play();
	}
    }

    private static Scene createScene() {
	Group root = new Group();
	Scene scene = new Scene(root, Color.BISQUE);
	Text text = new Text();

	text.setX(20);
	text.setY(40);
	text.setFont(new Font(25));
	text.setText("Welcome JavaFX! movie");

	//root.getChildren().add(text);

	VideoTest.root = root;

	return (scene);
    }

    public void dispose() {
	if ( player != null ) {
	    MediaPlayer mp = player;
//	    Platform.runLater(() -> {
	        mp.stop();
	        mp.dispose();
//	    });
	    player = null;
	}
    }

    public void wait4done() {
	while (stopped == false)
	    S.m_sleep(200);
    }


    public void start(Stage primaryStage) {
	System.err.println("Java Home: " + System.getProperty("java.home"));
	System.err.println("User Home: " + System.getProperty("user.home"));
	System.err.println("User dir: " + System.getProperty("user.dir"));

	Thread th = new Thread(() -> {initAndShowGUI();});
	th.start();
	while (stopped == false)
	    S.m_sleep(200);

	th = new Thread(() -> {initAndShowGUI(jcomp, MEDIA_FN2);});
	th.start();
	while (stopped == false)
	    S.m_sleep(200);

	dispose();
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

        //launch(args);
	VideoTest vt = new VideoTest();
	vt.start(null);
    }



}
