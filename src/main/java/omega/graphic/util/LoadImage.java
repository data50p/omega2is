package omega.graphic.util;

import omega.Context;
import omega.swing.properties.SwingUtils;

import java.awt.*;
import java.io.File;

public class LoadImage {

    public static Image loadAndWait(Component comp, String im_name) {
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image im = null;
	try {
	    String fn = omega.Context.FILE_BASE + im_name;
	    if (omega.Config.T) omega.Context.sout_log.getLogger().info("ERR: " + "loading file name " + fn);
	    File file = new File(Context.omegaAssets(fn));
	    if (file != null && file.canRead())
		im = tk.createImage(fn);
	    else
		im = tk.createImage(fn);
	} catch (Exception ex) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "Can't load image " + im_name + '\n' + ex);
	    return null;
	}
	MediaTracker mt = new MediaTracker(comp);
	mt.addImage(im, 0);
	try {
	    mt.waitForID(0);
	} catch (InterruptedException e) {
	}
	return im;
    }

    public static Image loadAndWaitOrNull(Component comp, String im_name) {
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image im = null;
	try {
	    String fn = omega.Context.FILE_BASE + im_name;
	    if (omega.Config.T) omega.Context.sout_log.getLogger().info("ERR: " + "loading file name " + fn);
	    File file = new File(Context.omegaAssets(fn));
	    if (file != null && file.canRead())
		im = tk.createImage(fn);
	    else
		im = null;

	} catch (Exception ex) {
	    omega.Context.sout_log.getLogger().info("ERR: " + "Can't load image " + im_name + '\n' + ex);
	    return null;
	}

	MediaTracker mt = new MediaTracker(comp);
	mt.addImage(im, 0);
	try {
	    mt.waitForID(0);
	} catch (InterruptedException e) {
	}
	return im;
    }

    public static Image loadAndWaitFromFile(Component comp, String im_name) {
	    return SwingUtils.getImage(im_name);
/*	Toolkit tk = Toolkit.getDefaultToolkit();
	Image im = null;
	try {
	    String aImname = Context.omegaAssets(im_name);
	    Context.sout_log.getLogger().info("load image: (A) " + aImname);
	    im = tk.createImage(aImname);
	} catch (Exception ex) {
	    return null;
	}
	MediaTracker mt = new MediaTracker(comp);
	mt.addImage(im, 0);
	try {
	    mt.waitForID(0);
	} catch (InterruptedException e) {
	    //	    im=null;
	}
	return im;
	*/
    }
}
