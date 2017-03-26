package omega.appl.lesson;

import fpdo.sundry.S;
import omega.LicenseShowManager;
import omega.OmegaConfig;
import omega.OmegaContext;
import omega.appl.OmegaAppl;
import omega.t9n.T;
import omega.swing.filechooser.ChooseLessonFile;
import omega.lesson.appl.LessonRuntime;
import omega.util.Files;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;

public class Runtime extends OmegaAppl {

    LessonRuntime le_rt;
    boolean ask;

    private static String toURL(File file) {
        return Files.toURL(file);
    }

    public Runtime(String fn, boolean ask, boolean with_frame, char run_mode) {
        super("Lesson runtime");
        this.ask = ask;

        OmegaContext.lesson_log.getLogger().info("Runtime...");

        if (ask) {
            ChooseLessonFile choose_f = new ChooseLessonFile();

            String url_s = null;
            int rv = choose_f.showDialog(null, T.t("Select"));
            if (rv == JFileChooser.APPROVE_OPTION) {
                File file = choose_f.getSelectedFile();
                url_s = toURL(file);
                if (url_s.startsWith("file:")) {
                    fn = url_s.substring(5);
                } else {
                    fn = url_s;
                }
                if (!fn.endsWith("." + ChooseLessonFile.ext)) {
                    fn = fn + "." + ChooseLessonFile.ext;
                }
            } else {
                System.exit(0);
            }
        } else {
        }
        le_rt = new LessonRuntime(name, fn, with_frame, run_mode);
    }

    static long last_logged = S.ct();

    public static void main(String[] argv) {
        OmegaContext.lesson_log.getLogger().info("started");

        HashMap flag = S.flagAsMap(argv);
        java.util.List argl = S.argAsList(argv);

        OmegaContext.omega_lang = (String) flag.get("omega_lang");
        OmegaContext.sout_log.getLogger().info("ERR: " + "param omega_lang is " + OmegaContext.omega_lang);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.MetalLookAndFeel");
//	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        OmegaConfig.T = !false;

        boolean few = flag.get("few") != null;
        if (few) {
            OmegaContext.CACHE_FEW = true;
        }
        boolean demo = flag.get("demo") != null;
        if (demo) {
            OmegaContext.DEMO = true;
        }
        OmegaContext.sout_log.getLogger().info("ERR: " + "Omega demo: " + OmegaContext.DEMO);

        boolean ask = flag.get("ask") != null;
        String fn = argl.size() > 0 ? (String) argl.get(0) : null;
//log	omega.OmegaContext.sout_log.getLogger().info("ERR: " + "start " + ask + ' ' + fn);
        String t_steps = (String) flag.get("T");
        if (t_steps != null) {
            OmegaConfig.t_step = Integer.parseInt(t_steps);
        }
        boolean with_frame = flag.get("small") != null;
        boolean logon = flag.get("log") != null;

        OmegaContext.setLogon(OmegaContext.isDeveloper() || logon);

        boolean b_p = flag.get("pupil") != null;
        boolean b_a = flag.get("admin") != null;
        boolean b_t = flag.get("teacher") != null;

        char ch = '?';
        if (b_p) {
            ch = 'p';
        }
        if (b_t) {
            ch = 't';
        }
        if (b_a) {
            ch = 'a';
        }

        if (LicenseShowManager.showAndAccepted()) {
            Runtime rt = new Runtime(fn, ask, with_frame, ch);
        } else {
            System.exit(1);
        }
    }
}
