package omega.appl.lesson;

import fpdo.sundry.S;
import omega.LicenseShow;
import omega.appl.OmegaAppl;
import omega.lesson.appl.LessonEditor;

import javax.swing.*;
import java.util.HashMap;

public class Editor extends OmegaAppl {
    LessonEditor le;

    public Editor(String fn) {
	super("Lesson editor");
	le = new LessonEditor("Omega - Lesson Editor:", fn);
    }

    public static void main(String[] argv) {
	HashMap flag = S.flagAsMap(argv);
	java.util.List argl = S.argAsList(argv);

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
