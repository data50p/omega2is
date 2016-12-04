package omega.media.video;

import fpdo.sundry.S;

import javax.media.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//  import	javax.swing.event.InternalFrameAdapter;
//  import	javax.swing.event.InternalFrameEvent;

public class MpgPlayer implements ControllerListener {
    private Player this_player;
    public Component visual;

    public int vw;
    public int vh;

    public int vw_orig;
    public int vh_orig;

    public double aspect = 1.0;

    private boolean prefetch_done = false;
    private boolean ready = false;

    public MpgPlayer(Player player, String title) {
	this_player = player;
	this_player.addControllerListener(this);
	this_player.realize();

	for (int i = 0; i < 100; i++)
	    if (prefetch_done == false)
		S.m_sleep(100);
    }

    public void controllerUpdate(ControllerEvent ce) {
	if (ce instanceof RealizeCompleteEvent) {
	    this_player.prefetch();
	} else if (ce instanceof ResourceUnavailableEvent) {
	    prefetch_done = true;
	    ready = true;
	} else if (ce instanceof PrefetchCompleteEvent) {
	    if (visual != null)
		return;

	    if ((visual = this_player.getVisualComponent()) != null) {
		omega.Context.lesson_log.getLogger().info("MpgPlayer: Got visual " + visual);

		Dimension size = visual.getPreferredSize();
		vw_orig = vw = size.width;
		vh_orig = vh = size.height;
		vw *= 1;
		vh *= 1;
		aspect = (double) vw / vh;
		omega.Context.lesson_log.getLogger().info("      loaded movie size " + size + ' ' + aspect);
		visual.setSize(new Dimension(vw, vh));
	    } else {
		// set default vindow size
		vw = 320;
		vh = 240;
	    }
//  	    this_player.start();
	    prefetch_done = true;
	} else if (ce instanceof EndOfMediaEvent) {        // REPEAT !!
	    ready = true;
//  	    this_player.setMediaTime( new Time (0) );
//  	    this_player.start();
	}
    }

//      public Dimension getPreferredSize() {
//  	return new Dimension(vw, vh);
//      }
//      public Dimension getMinimumSize() {
//  	return new Dimension(vw, vh);
//      }
//      public Dimension getMaximumSize() {
//  	return new Dimension(vw, vh);
//      }

    public void reset() {
	ready = false;
	this_player.setMediaTime(new Time(0));
    }

    public void start() {
	this_player.start();
    }

    public void stop() {
	this_player.stop();
    }

    public void wait4() {
	while (ready == false)
	    S.m_sleep(200);
    }

    public void dispose(JComponent jcomp) {
	if (this_player != null) {
	    this_player.removeControllerListener(this);
	    this_player.close();
	}
	if (jcomp != null && visual != null)
	    jcomp.remove(visual);
	if (this_player != null) {
	    this_player.deallocate();
	    this_player = null;
	}
	if (visual != null)
	    visual.setVisible(false);
	visual = null;
	System.gc();
	ready = true;
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
	URL url = null;

	omega.Context.lesson_log.getLogger().info("create mpgPlayer jcomp: " + fn);

	try {
	    url = new URL("file:" + fn);

	    try {
		Player player = Manager.createPlayer(url);
		if (player != null) {
		    MpgPlayer mp = new MpgPlayer(player, "null");
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
	    } catch (NoPlayerException e) {
		omega.Context.lesson_log.getLogger().info("NoPlayerEx: " + e);
	    }

	} catch (MalformedURLException e) {
	    omega.Context.lesson_log.getLogger().info("ERR: " + "MUE Error:" + e);
	} catch (IOException e) {
	    omega.Context.lesson_log.getLogger().info("ERR: " + "IOException:" + e);
	} catch (Exception e) {
	    omega.Context.lesson_log.getLogger().info("ERR: " + "Exception:" + e);
	}
	return null;
    }
}
