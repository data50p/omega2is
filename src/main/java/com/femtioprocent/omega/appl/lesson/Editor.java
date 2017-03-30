package com.femtioprocent.omega.appl.lesson;

import com.femtioprocent.omega.LicenseShowManager;
import com.femtioprocent.omega.OmegaConfig;
import com.femtioprocent.omega.OmegaContext;
import com.femtioprocent.omega.appl.OmegaAppl;
import com.femtioprocent.omega.lesson.appl.LessonEditor;
import com.femtioprocent.omega.util.SundryUtils;

import javax.swing.*;
import java.util.HashMap;

public class Editor extends OmegaAppl {
    LessonEditor le;

    public Editor(String fn) {
        super("Lesson editor");
        le = new LessonEditor("Omega - Lesson Editor:", OmegaContext.antiOmegaAssets(fn));
    }

    public static void main(String[] argv) {
        HashMap flag = SundryUtils.flagAsMap(argv);
        java.util.List argl = SundryUtils.argAsList(argv);

        if (flag.get("help") != null) {
            System.err.println("-help");
            System.err.println("-omega_assets=<assets name>");
            System.err.println("-omega_lang=<lang>");
            System.err.println("-T=<step>");
            System.err.println("-");
            System.err.println("-");
            System.exit(1);
        }

        OmegaContext.setOmegaAssets((String) flag.get("omega_assets"));

        OmegaContext.omega_lang = (String) flag.get("omega_lang");
        OmegaContext.sout_log.getLogger().info("ERR: " + "param omega_lang is " + OmegaContext.omega_lang);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.MetalLookAndFeel");
//	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        String t_steps = (String) flag.get("T");
        if (t_steps != null)
            OmegaConfig.t_step = Integer.parseInt(t_steps);

        String fn = argl.size() > 0 ? (String) argl.get(0) : null;

        if (LicenseShowManager.showAndAccepted()) {
            Editor e = new Editor(fn);
        } else {
            System.exit(1);
        }
    }
}
