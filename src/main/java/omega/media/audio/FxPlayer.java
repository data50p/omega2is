package omega.media.audio;

//åäö

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import omega.util.MilliTimer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class FxPlayer {
    String fn;
    boolean done = false;
    MediaPlayer mediaPlayer = null;
    Object lock = new Object();

    String realy_name;

    FxPlayer(String fn) {
	omega.Context.sout_log.getLogger().info("ERR: " + "FxPlayer: fn = " + fn);

	this.fn = fn;
	realy_name = fn;
	if (fn.endsWith(".mp3")) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "FxPlayer: fn -> " + fn);
	    return;
	}


	String fn3 = fn.replaceAll("\\.wav", ".mp3");
	File file3 = new File(fn3);
	File file2 = new File(fn);
	if (file3.exists() && !file2.exists()) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "FxPlayer: fn -> " + fn3);
	    realy_name = fn3;
	} else {
	    omega.Context.sout_log.getLogger().info("ERR: " + "FxPlayer: fn -> " + fn3);
	    realy_name = fn;
	}
    }

    public static SourceDataLine getSourceDataLine(AudioFormat format) throws LineUnavailableException {
	DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

	return (SourceDataLine) AudioSystem.getLine(info);
    }

    void play(boolean wait) {
	playFX(realy_name);
	if (wait) {
	    synchronized (lock) {
		try {
		    while (!done)
			lock.wait(200);
		} catch (InterruptedException ex) {
		}
		System.err.println("fxPlayed waited ... notified done");
	    }
	}
    }

    void playFX(String fn) {
	System.err.println("Enter playFX " + fn);
	doOnce();
	Platform.runLater(() -> {
	MilliTimer mt = new MilliTimer();
	File f = new File(fn);
	String bip = null;
	bip = f.toURI().toString();
	System.err.println("fxPrepare " + bip + ' ' + mt.getString());
	Media hit = new Media(bip);
	mediaPlayer = new MediaPlayer(hit);

	    mediaPlayer.setOnEndOfMedia(() -> {
		synchronized (lock) {
		    mediaPlayer.dispose();
		    System.err.println("fxPlayed eof" + ' ' + mt.getString());
		    done = true;
		    lock.notifyAll();
		}
	    });
	    System.err.println("fxPlay..." + ' ' + mt.getString());
	    mediaPlayer.play();
	});
	System.err.println("Leave playFX " + fn);
    }

    private static boolean once = false;

    private void doOnce() {
	if (once == false) {
	    once = true;
	    initFxFramework();
	    try {
		Thread.sleep(300);
	    } catch (InterruptedException e) {
	    }
	}
    }

    static JFXPanel z = null;

    private void initFxFramework() {
	SwingUtilities.invokeLater(() -> {
	    z = new JFXPanel(); // this will prepare JavaFX toolkit and environment
	    Platform.setImplicitExit(false);
	    /*
	    Platform.runLater(() -> {
		StageBuilder.create()
			.scene(SceneBuilder.create()
			.width(100).height(61)
			.root(LabelBuilder.create()
				.font(Font.font("Arial", 10))
				.text("JavaFX")
				.build())
			.build())
			.onCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent windowEvent) {
				;//System.exit(0);
			    }
			}).build().show();
	    });
	    */
	});
    }
}
