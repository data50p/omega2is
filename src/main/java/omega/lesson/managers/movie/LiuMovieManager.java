/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package omega.lesson.managers.movie;

import omega.Config;
import omega.Context;
import omega.lesson.EachWordMovie;
import omega.lesson.canvas.LessonCanvas;
import omega.lesson.machine.Item;
import omega.lesson.machine.Target;
import omega.lesson.managers.Manager;
import omega.media.video.VideoUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @author lars
 */
public class LiuMovieManager extends Manager {

    Window window;
    EachWordMovie eWmovie = null;

    Component glp = null;
    LessonCanvas le_canvas;

    public LiuMovieManager(Window window, LessonCanvas le_canvas) {
        super("LiuMovieManager");
        this.window = window;
        this.repeat_mode = LiuMovieManager.RepeatMode.CAN_REPEAT;
        this.le_canvas = le_canvas;
    }

    public boolean prepare(String prefix, String movieNameBase, boolean init) {
        if (!Config.LIU_Mode)
            return false;

        omega.Context.sout_log.getLogger().info("prepare play movie: (~A) " + prefix + ' ' + movieNameBase + ' ' + init);

        if (init)
            return prepare(prefix + movieNameBase);
        else
            return prepareAgain(prefix + movieNameBase);
    }

    private boolean prepare(String movieFileName) {
        String movieName = movieFileName;

        final Component[] cs = window.getComponents();
        JRootPane jrp = (JRootPane) cs[0];
        final Container cop = jrp.getContentPane();
        glp = jrp.getGlassPane();

        eWmovie = new EachWordMovie();
        try {
            JComponent jcomp = (JComponent) glp;
            JComponent canvas = eWmovie.prepare(movieName, jcomp);
            if (canvas == null) {
                eWmovie = null;
                return false;
            }
            omega.Context.sout_log.getLogger().info("LiuMovieManager: movie loaded");
            return true;
        } catch (Exception ex) {
            omega.Context.sout_log.getLogger().info("ERR: " + "" + ex);
            ex.printStackTrace();
        }
        return false;
    }

    private boolean prepareAgain(String movieFileName) {
        String movieName = movieFileName;

        try {
            if (eWmovie != null) {
                JComponent jcomp = (JComponent) glp;
                JComponent canvas = eWmovie.prepare(movieName, jcomp);
                if (canvas == null) {
                    eWmovie = null;
                    return false;
                }
                omega.Context.sout_log.getLogger().info("LiuMovieManager: movie loaded");
                return true;
            }
        } catch (Exception ex) {
            omega.Context.sout_log.getLogger().info("ERR: " + "" + ex);
            ex.printStackTrace();
        }
        return false;
    }

    public static enum RepeatMode {
        CAN_REPEAT,
        NO_REPEAT,
        DO_REPEAT;
    }

    public static RepeatMode repeat_mode = RepeatMode.CAN_REPEAT;

    public Rectangle start(int x, int y, double scale) {
        try {
            if (eWmovie != null) {
                eWmovie.perform(x, y, scale);
                Rectangle r = getRectangle();
                omega.Context.sout_log.getLogger().info("rect: " + r);
                Thread.sleep(500);
                r = getRectangle();
                omega.Context.sout_log.getLogger().info("rect: " + r);
                return r;
            }
        } catch (Exception ex) {
            omega.Context.sout_log.getLogger().info("ERR: " + "" + ex);
            ex.printStackTrace();
        }
        return null;
        //jrp.setContentPane(cop);
    }

    public void wait(int x, int y, double scale) {
        try {
            if (eWmovie != null) {
                eWmovie.waitEnd();
                if (repeat_mode == RepeatMode.DO_REPEAT) {
                    repeat_mode = repeat_mode.NO_REPEAT;
                    le_canvas.repaint();
                    eWmovie.reset();
                    eWmovie.perform(x, y, scale);
                    eWmovie.waitEnd();
                }
            }
        } catch (Exception ex) {
            omega.Context.sout_log.getLogger().info("ERR: " + "" + ex);
            ex.printStackTrace();
        }
        //jrp.setContentPane(cop);
    }

    public void cleanup() {
        if (eWmovie != null) {
            eWmovie.dispose();
            eWmovie = null;
        }
    }

    public Rectangle getRectangle() {
        return eWmovie.getMovieRectangle();
    }

    public String getSignMovieFileName(Item sitm, Target tg, int ix) {
        String sfn = sitm.getSignD();
        if (sfn != null && sfn.length() > 0) {
            sfn = tg.fillVarHere(ix, sfn);
            if (mediaFileExist(sfn))
                return sfn;
        }
        sfn = "sign-"
                + omega.Context.getLessonLang()
                + "/"
                + sitm.getTextD()
                + ".mpg";
        if (mediaFileExist(sfn))
            return sfn;
        return null;
    }

    private boolean mediaFileExist(String sfn) {
        String smFn = VideoUtil.findSupportedFname(Context.getMediaFile(sfn));
        if (smFn == null)
            return false;
        File f = new File(smFn);
        boolean exist = f.exists() && f.canRead();
        omega.Context.sout_log.getLogger().info("mediaFileExist: " + sfn + ' ' + exist);
        return exist;
    }

}
