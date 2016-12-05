package omega.appl.lesson;

import fpdo.sundry.S;
import omega.LicenseShow;
import omega.appl.OmegaAppl;
import omega.i18n.T;
import omega.lesson.ChooseLessonFile;
import omega.lesson.appl.LessonRuntime;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class Runtime extends OmegaAppl {

    LessonRuntime le_rt;
    boolean ask;

    private static String toURL(File file) {
	String url_s = null;
//log	omega.Context.sout_log.getLogger().info("ERR: " + "got file " + file);
	try {
	    URL url = file.toURI().toURL();
//log	    omega.Context.sout_log.getLogger().info("ERR: " + "got url " + url);
	    url_s = url.toString();
	} catch (Exception ex) {
	    omega.Context.exc_log.getLogger().throwing(Runtime.class.getName(), "toURL", ex);
	}
	return url_s;
    }

    public Runtime(String fn, boolean ask, boolean with_frame, char run_mode) {
	super("Lesson runtime");
	this.ask = ask;

	omega.Context.lesson_log.getLogger().info("Runtime...");

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
	omega.Context.lesson_log.getLogger().info("started");

	HashMap flag = S.flagAsMap(argv);
	java.util.List argl = S.argAsList(argv);

	omega.Context.omega_lang = (String) flag.get("omega_lang");
	omega.Context.sout_log.getLogger().info("ERR: " + "param omega_lang is " + omega.Context.omega_lang);

	try {
	    UIManager.setLookAndFeel("javax.swing.plaf.MetalLookAndFeel");
//	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	}

	omega.Config.T = false;

	boolean few = flag.get("few") != null;
	if (few) {
	    omega.Context.CACHE_FEW = true;
	}
	boolean demo = flag.get("demo") != null;
	if (demo) {
	    omega.Context.DEMO = true;
	}
	omega.Context.sout_log.getLogger().info("ERR: " + "Omega demo: " + omega.Context.DEMO);

	boolean ask = flag.get("ask") != null;
	String fn = argl.size() > 0 ? (String) argl.get(0) : null;
//log	omega.Context.sout_log.getLogger().info("ERR: " + "start " + ask + ' ' + fn);
	String t_steps = (String) flag.get("T");
	if (t_steps != null) {
	    omega.Config.t_step = Integer.parseInt(t_steps);
	}
	boolean with_frame = flag.get("small") != null;
	boolean logon = flag.get("log") != null;

	omega.Context.setLogon(logon);

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

	if (LicenseShow.showAndAccepted()) {
	    Runtime rt = new Runtime(fn, ask, with_frame, ch);
	} else {
	    System.exit(1);
	}
    }
}
