package omega;

import omega.subsystem.Subsystem;
import omega.util.Log;
import omega.util.SundryUtils;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;


public class Context {
    public static final String OMEGA_ASSETS_SUFFIX = ".omega_assets";
    public static final String defaultOmegaAssets = "default" + OMEGA_ASSETS_SUFFIX;
    public static final String alternateOmegaAssets = "alternate" + OMEGA_ASSETS_SUFFIX;

    private  static String omegaAssets = defaultOmegaAssets;

    static Object lock = new Object();
    static HashMap subsystems = new HashMap();
    public static String URL_BASE = "http://localhost:8089/";
    public static String URL_BASE_AS_FILE = omegaAssets + "/" + "media/";
    public static String FILE_BASE = omegaAssets + "/" + "media/";  // null

    public static boolean logon = !false;

    public static Log def_log = new Log();
    public static Log sout_log = def_log;
    public static Log exc_log = def_log;
    public static Log story_log = def_log;
    public static Log lesson_log = def_log;
    public static Log audio_log = def_log;

    private static String lesson_lang = omega.i18n.T.lang;
    private static String lesson_lang_editor = omega.i18n.T.lang;

    public static String SPEED = "";

    public static boolean CACHE_FEW = false;

    public static Color COLOR_WARP = new Color(0xe5, 0xe5, 0xe5); // transfer color from anim panel to mpg panel
    public static Color COLOR_TEXT_WARP = new Color(0, 0, 0); // transfer color from anim panel to mpg panel

    public static boolean extern_help_browser = true;

    public static Map variables = null;

    public static boolean DEMO = false;
    public static String omega_lang = null;

    /**
     * Get the full path for current omega assets
     *
     * @param path
     * @return
     */
    public static String omegaAssets(String path) {
	if (path == null ) {
	    return null;
	}

        boolean noAssets = path.contains("toolbarButtonGraphics");

	if (path != null && path.startsWith(omegaAssets)) {
	    sout_log.getLogger().warning("omegaAssets(): Already omega_assets: " + path);
	    if ( noAssets )
	        return antiOmegaAssets(path);
	    return path;
	}
	if ( noAssets )
	    return path;
        return omegaAssets + '/' + path;
    }

    public static String antiOmegaAssets(String afn) {
	if ( afn == null || afn.length() == 0 )
	    return afn;
	if ( afn.startsWith(omegaAssets("") ) ) {
	    return afn.substring(omegaAssets("").length());
	}
	if ( afn.startsWith("./" + omegaAssets("") ) ) {
	    return afn.substring(("./" + omegaAssets("")).length());
	}
	return afn;
    }

    public static String[] antiOmegaAssets(String[] afns) {
	if ( afns == null || afns.length == 0 )
	    return afns;

	String[] asa = new String[afns.length];
	int ix = 0;
	for(String s : afns) {
	    asa[ix++] = antiOmegaAssets(s);
	}
	return asa;
    }

    /**
     * Set the from now on choosen omega assets
     *
     * @param omega_assets_name null value restores default
     */
    public static void setOmegaAssets(String omega_assets_name) throws IllegalArgumentException {
        if (SundryUtils.empty(omega_assets_name) ) {
	    omegaAssets = defaultOmegaAssets;
	    Context.sout_log.getLogger().info("setOmegaAssets: " + omegaAssets);
	} else {
	    if ( !omega_assets_name.endsWith(OMEGA_ASSETS_SUFFIX))
		omega_assets_name = omega_assets_name + OMEGA_ASSETS_SUFFIX;
	    if ( (new File(omegaAssets)).exists() ) {
		Context.sout_log.getLogger().info("setOmegaAssets: " + omegaAssets);
		omegaAssets = omega_assets_name;
		return;
	    }
	    Context.sout_log.getLogger().info("setOmegaAssets: unable to dep_set " + omegaAssets);
	}
    }

    public static class HelpStack {
	Stack stack = new Stack();


	public void push(String s) {
	    stack.push(s);
	    //	    omega.Context.sout_log.getLogger().info("ERR: " + "H push " + stack);
	}

	public String get() {
	    if (stack.empty())
		return null;
	    //	    omega.Context.sout_log.getLogger().info("ERR: " + "H get " + (String)stack.peek());
	    return (String) stack.peek();
	}

	public void pop(String s) {
	    if (s == null)
		return;
	    if (s.length() == 0 || s.equals(get()))
		if (!stack.empty())
		    stack.pop();
	    //	    omega.Context.sout_log.getLogger().info("ERR: " + "H pop " + stack);
	}
    }

    ;
    static public HelpStack HELP_STACK = new HelpStack();

    public static void setLogon(boolean b) {
        Log.getLogger().setLevel(b ? Level.ALL : Level.OFF);
    }

    public static String getLessonLang() {
	return lesson_lang;
    }

    public static void setLessonLang(String s) {
	omega.Context.lesson_log.getLogger().info("old, new: " + lesson_lang + ' ' + s);
	lesson_lang = s;
    }

    public static boolean isDeveloper() {
        File f = new File("../.git");
        return f.exists();
    }
//    public static String getEditorLessonLang() {
//	return lesson_lang_editor;
//    }
//
//    public static void setEditorLessonLang(String s) {
//        omega.Context.lesson_log.getLogger().info("EditorLessonLang: old, new: " + lesson_lang_editor + ' ' + s);
//	lesson_lang_editor = s;
//    }

    public Context() {
    }

    public static void init(String s, Object arg) {
	synchronized (lock) {
	    try {
		if (subsystems.get(s) != null)
		    return;
		Class cl = Class.forName("omega.subsystem." + s);
		Subsystem ss = (Subsystem) cl.newInstance();
		ss.init(arg);
		subsystems.put(s, ss);
	    } catch (Exception ex) {
		omega.Context.sout_log.getLogger().info("ERR: " + "Can't start subsystem " + s + '\n' + ex);
	    }
	}
    }

    static public Subsystem getSubsystem(String s) {
	return (Subsystem) subsystems.get(s);
    }
}

