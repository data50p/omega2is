package com.femtioprocent.omega.graphic.util;

import com.femtioprocent.omega.OmegaConfig;
import com.femtioprocent.omega.OmegaContext;
import com.femtioprocent.omega.swing.OmegaSwingUtils;

import java.awt.*;
import java.io.File;

public class LoadImage {

    public static Image loadAndWait(Component comp, String im_name) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image im = null;
        try {
            String fn = OmegaContext.getMediaFile(im_name);
            if (OmegaConfig.T) OmegaContext.sout_log.getLogger().info("ERR: " + "loading file name " + fn);
            File file = new File(OmegaContext.omegaAssets(fn));
            if (file != null && file.canRead())
                im = tk.createImage(fn);
            else
                im = tk.createImage(fn);
        } catch (Exception ex) {
            OmegaContext.sout_log.getLogger().info("ERR: " + "Can't load image " + im_name + '\n' + ex);
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
            String fn = OmegaContext.getMediaFile(im_name);
            if (OmegaConfig.T) OmegaContext.sout_log.getLogger().info("ERR: " + "loading file name " + fn);
            File file = new File(OmegaContext.omegaAssets(fn));
            if (file != null && file.canRead())
                im = tk.createImage(fn);
            else
                im = null;

        } catch (Exception ex) {
            OmegaContext.sout_log.getLogger().info("ERR: " + "Can't load image " + im_name + '\n' + ex);
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
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image im = null;
        try {
            String aImname = OmegaContext.omegaAssets(im_name);
            OmegaContext.sout_log.getLogger().info("load image: (A) " + aImname);
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
    }

    public static Image loadAndWaitFromResource(Component comp, String im_name) {
        return OmegaSwingUtils.getImage(im_name);
    }
}
