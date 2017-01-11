package omega.media.video;

import com.femtioprocent.omega2is.appl.VideoTest;
import fpdo.sundry.S;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MpgPlayer {
    public int vw;
    public int vh;

    public int vw_orig;
    public int vh_orig;

    public double aspect = 1.0;

    private boolean prefetch_done = false;
    private boolean ready = false;

    public Component visual;
    VideoTest vt = null;

    public MpgPlayer(Object player, String title) {
        if ( false ) {
	    for (int i = 0; i < 100; i++)
		if (prefetch_done == false)
		    S.m_sleep(100);
	}
    }

    public void reset() {
	ready = false;
}

    public void start() {
        if ( vt != null )
            vt.play();
    }

    public void stop() {
    }

    public void wait4() {
	if ( vt != null )
	    vt.wait4done();
//	if ( true ) {
//	    S.m_sleep(4000);
//	    return;
//	}
//	while (ready == false)
//	    S.m_sleep(200);
    }

    public void dispose(JComponent jcomp) {
        if ( vt != null )
            vt.dispose();
        vt = null;
	ready = true;
	visual = null;
    }

    public int getX() {
	return visual.getX();
    }

    public int getY() {
	return visual.getY();
    }

    public int getW() {
	return vw;
    }

    public int getH() {
	return vh;
    }

    public int getOrigW() {
	return vw_orig;
    }

    public int getOrigH() {
	return vh_orig;
    }

    public void setSize(int w, int h) {
	vw = w;
	vh = h;
	visual.setSize(new Dimension(vw, vh));
	omega.Context.lesson_log.getLogger().info("set m size is: " + w + ' ' + h);
    }

    public void setLocation(int x, int y) {
	visual.setLocation(x, y);
	omega.Context.lesson_log.getLogger().info("set m loc is: " + x + ' ' + y);
    }

    static public MpgPlayer createMpgPlayer(String fn, JComponent jcomp) {
        if ( true ) {
            return createMpgPlayer2(fn, jcomp);
	}
	URL url = null;

	omega.Context.lesson_log.getLogger().info("create mpgPlayer jcomp: " + fn);

	try {
	    url = new URL("file:" + fn);

	    try {
		if (true) {
		    MpgPlayer mp = new MpgPlayer(null, "null");
//  		    if ( old != null )
//  			jpan.remove(old);
		    mp.visual.setVisible(false);
		    //mp.setSize(1, 1);
		    //mp.setLocation(10, 10);
		    jcomp.add(mp.visual);//, BorderLayout.CENTER);
		    mp.visual.setVisible(false);
//                    mp.visual.setVisible(false);
		    return mp;
		}
	    } catch (Exception e) {
		omega.Context.lesson_log.getLogger().info("NoPlayerEx: " + e);
	    }

	} catch (MalformedURLException e) {
	    omega.Context.lesson_log.getLogger().info("ERR: " + "MUE Error:" + e);
	} catch (Exception e) {
	    omega.Context.lesson_log.getLogger().info("ERR: " + "Exception:" + e);
	}
	return null;
    }

    static public MpgPlayer createMpgPlayer2(String fn, JComponent jcomp) {
	URL url = null;

	omega.Context.lesson_log.getLogger().info("create mpgPlayer jcomp: " + fn);

	try {
	    url = new URL("file:" + fn);

	    try {
		if (true) {
		    VideoTest vt = new VideoTest();
		    JFXPanel fxP = vt.initAndShowGUI(jcomp, fn);
		    MpgPlayer mp = new MpgPlayer(null, "null");
		    mp.visual = jcomp;
//  		    if ( old != null )
//  			jpan.remove(old);
//		    mp.visual.setVisible(!false);
		    //mp.setSize(1, 1);
		    //mp.setLocation(10, 10);
//		    jcomp.add(mp.visual);//, BorderLayout.CENTER);
//		    mp.visual.setVisible(!false);
//                    mp.visual.setVisible(false);
		    mp.vt = vt;
		    return mp;
		}
	    } catch (Exception e) {
		e.printStackTrace();
		omega.Context.lesson_log.getLogger().info("NoPlayerEx: " + e);
	    }

	} catch (MalformedURLException e) {
	    omega.Context.lesson_log.getLogger().info("ERR: " + "MUE Error:" + e);
	} catch (Exception e) {
	    omega.Context.lesson_log.getLogger().info("ERR: " + "Exception:" + e);
	}
	return null;
    }
}
