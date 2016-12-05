package omega.appl.animator;

import fpdo.sundry.S;
import omega.LicenseShow;
import omega.anim.appl.AnimEditor;
import omega.appl.OmegaAppl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Editor extends OmegaAppl {
    public AnimEditor ae;

    public Editor(boolean verbose) {
	super("Animator editor");

	KeyboardFocusManager.setCurrentKeyboardFocusManager(new DefaultKeyboardFocusManager() {
	    char last_state = '_';
	    char state = 'r';
	    boolean first_tr = false;

	    boolean P = false;

	    public boolean dispatchKeyEvent(KeyEvent e) {
		char ch = e.getKeyChar();
		int kc = e.getKeyCode();

		if (e.getID() == KeyEvent.KEY_PRESSED) {
		    if (e.getKeyCode() == KeyEvent.VK_F1) {
			omega.anim.appl.AnimEditor.help.showManualAE();
		    }

		}
		return super.dispatchKeyEvent(e);
	    }
	});

	ae = new AnimEditor(verbose);
    }

    public static void main(String[] argv) {
	HashMap flag = S.flagAsMap(argv);
	java.util.List argl = S.argAsList(argv);

	omega.Context.omega_lang = (String) flag.get("omega_lang");
	omega.Context.sout_log.getLogger().info("ERR: " + "param omega_lang is " + omega.Context.omega_lang);

	try {
	    UIManager.setLookAndFeel("javax.swing.plaf.MetalLookAndFeel");
// UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	}

	boolean verbose = false;

	if (flag.get("v") != null)
	    verbose = true;
	if (flag.get("R") != null)
	    omega.Config.RUN_MODE = true;
	if (flag.get("T") != null)
	    omega.Config.T = true;

	String s = null;
	if ((s = (String) flag.get("t")) != null)
	    omega.Config.t_step = Integer.parseInt(s);
//log	omega.Context.sout_log.getLogger().info("ERR: " + "" + omega.Config.t_step);

	if (LicenseShow.showAndAccepted()) {
	    Editor e = new Editor(verbose);
	    S.m_sleep(3000);
	    omega.appl.OmegaAppl.closeSplash();
	} else {
	    System.exit(1);
	}

//log	omega.Context.sout_log.getLogger().info("ERR: " + "--------ok-------");
    }
}