package omega.media.video;

import fpdo.sundry.S;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import omega.Context;
import omega.lesson.canvas.MsgItem;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class FxMoviePlayer {

    Scene scene;
    Group root;
    static JFrame frame;
    JComponent jcomp;
    MediaPlayer player = null;
    boolean initDone = false;
    boolean stopped = false;
    JFXPanel fxPanel = null;

    public int mediaW;
    public int mediaH;

    private static final String MEDIA_FN = Context.omegaAssets("media/feedback/film1/feedback1.mp4");
    private static final String MEDIA_FN2 = Context.omegaAssets("media/feedback/film1/feedback2.mp4");

    boolean ready = false;
    int winW;
    int winH;
    public boolean messageShown;


    FxMoviePlayer(int winW, int winH) {
        this.winW = winW;
        this.winH = winH;
    }

    private JFXPanel initGUI() {
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

	return initGUI(jcomp, MEDIA_FN);
    }

    public JFXPanel initGUI(JComponent jcomp, String fn) {
	System.err.println("enter initGUI " + Platform.isFxApplicationThread());
	// This method is invoked on the EDT thread
	this.jcomp = jcomp;
	boolean snd = true;

	if ( fxPanel == null ) {
	    fxPanel = new JFXPanel();
	    fxPanel.setSize(291, 251);
	    fxPanel.setLocation(62, 72);
	    jcomp.add(fxPanel);//, BorderLayout.CENTER);
	    snd = false;
	    //jcomp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	final boolean snd_ = snd;

	Platform.runLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    System.err.println("runLater: 100");
		    if ( snd_ ) {
			initFX2(fxPanel, fn);
		    } else {
			initFX(fxPanel, fn);
			initFX2(fxPanel, fn);
		    }
		    System.err.println("runLater: play()...");
		    player.play();
		    System.err.println("runLater: ...play()");
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
		initDone = true;
	    }
	});

	System.err.println("leave initGUI");
	return fxPanel;
    }

    public void waitReady() {
        while ( ! ready )
	    S.m_sleep(100);

    }

    private void initFX(JFXPanel fxPanel, String fn) throws URISyntaxException {
	System.err.println("enter initFX FxAppThread => " + Platform.isFxApplicationThread());
	// This method is invoked on the JavaFX thread
	this.root = new Group();
	Scene scene = new Scene(this.root, winW, winH, new Color(0.24, 0.44, 0.84, 0.184));
	this.scene = scene;
	scene.setOnMousePressed(new EventHandler<MouseEvent>() {
	    public void handle(MouseEvent me) {
		System.out.println("Mouse pressed");
		messageShown = false;
	    }
	});

	scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	    public void handle(KeyEvent ke) {
		System.out.println("Key Pressed: " + ke.getText() + ' ' + ke.getCode());
	    }
	});

	scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	    public void handle(KeyEvent ke) {
		System.out.println("Key Released: " + ke.getText()+ ' ' + ke.getCode());
	    }
	});
	fxPanel.setScene(scene);
    }

    private void initFX2(JFXPanel fxPanel, String fn) throws URISyntaxException {
	File file = new File(fn);
	String uu = file.toURI().toString();
	System.err.println("UU is " + uu);

	Class<? extends FxMoviePlayer> aClass = getClass();
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

	player.setOnReady(new Runnable() {
	    @Override
	    public void run() {
		int w = player.getMedia().getWidth();
		int h = player.getMedia().getHeight();
		mediaW = w;
		mediaH = h;

		Dimension d = jcomp.getSize();
		d.setSize(winW, winH);
		jcomp.setSize(mediaW, mediaH);
		jcomp.setLocation(111, 111);
		double xx = (d.getWidth() - w) / 2.0;
		double yy = (d.getHeight() - h) / 2.0;
		xx = 0; yy = 0;
		//mediaView.setFitHeight(h);
		double scal = 1.5;
		fxPanel.setSize((int)(scal*w), (int)(scal*h));
		mediaView.setTranslateX(xx);
		mediaView.setTranslateY(yy);
		System.out.println("---++-- " + d + " media: " + w + ' ' + h + " translate: " + xx + ' ' + yy);
		System.err.println("VP " + mediaView.getX());
		ready = true;
//		player.play();
	    }
	});

	player.setOnEndOfMedia(() -> {
	    System.out.println("EOF ");
	    stopped = true;
	    player.dispose();
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

    public void dispose() {
	if ( player != null ) {
	    MediaPlayer mp = player;
//	    Platform.runLater(() -> {
//	        mp.stop();
//	        mp.dispose();
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

	(new Thread(() -> initGUI())).start();
	while (stopped == false)
	    S.m_sleep(200);

	(new Thread(() -> initGUI(jcomp, MEDIA_FN2))).start();
	while (stopped == false)
	    S.m_sleep(200);

	dispose();
    }

    public static void main(String[] args) {
	FxMoviePlayer fxp = new FxMoviePlayer(800, 600);
	fxp.start(null);
    }

    public void showMsg(MsgItem mi) {
	Platform.runLater(() -> {
	    Text text = new Text(mi.text);
	    text.setX(55);
	    text.setY(55);
	    text.setFont(new Font(25));
	    root.getChildren().add(text);
	});
    }
}
