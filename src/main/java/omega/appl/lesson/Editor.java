package omega.appl.lesson;

import fpdo.sundry.S;
import omega.Context;
import omega.LicenseShow;
import omega.appl.OmegaAppl;
import omega.lesson.appl.LessonEditor;

import javax.swing.*;
import java.util.HashMap;

public class Editor extends OmegaAppl {
    LessonEditor le;

    public Editor(String fn) {
        super("Lesson editor");
        le = new LessonEditor("Omega - Lesson Editor:", Context.antiOmegaAssets(fn));
    }

    public static void main(String[] argv) {
        HashMap flag = S.flagAsMap(argv);
        java.util.List argl = S.argAsList(argv);

        if (flag.get("help") != null) {
            System.err.println("-help");
            System.err.println("-omega_assets=<assets name>");
            System.err.println("-omega_lang=<lang>");
            System.err.println("-T=<step>");
            System.err.println("-");
            System.err.println("-");
            System.exit(1);
        }

        Context.setOmegaAssets((String) flag.get("omega_assets"));

        omega.Context.omega_lang = (String) flag.get("omega_lang");
        omega.Context.sout_log.getLogger().info("ERR: " + "param omega_lang is " + omega.Context.omega_lang);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.MetalLookAndFeel");
//	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        String t_steps = (String) flag.get("T");
        if (t_steps != null)
            omega.Config.t_step = Integer.parseInt(t_steps);

        String fn = argl.size() > 0 ? (String) argl.get(0) : null;

        if (LicenseShow.showAndAccepted()) {
            Editor e = new Editor(fn);
        } else {
            System.exit(1);
        }
    }
}
