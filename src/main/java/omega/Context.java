package omega;

import omega.subsystem.Subsystem;
import omega.util.Log;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class Context {
    static Object lock = new Object();
    static HashMap subsystems = new HashMap();
    public static String URL_BASE = "http://localhost:8089/";
    public static String URL_BASE_AS_FILE = "media/";
    public static String FILE_BASE = "media/";  // null

    public static boolean logon = !false;

    public static Log def_log = new Log("default", logon);
    public static Log sout_log = new Log("stdout", logon);
    public static Log exc_log = new Log("exception", logon);
    public static Log story_log = new Log("story", logon);
    public static Log lesson_log = new Log("lesson", logon);
    public static Log audio_log = new Log("audio", true);

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
	logon = b;
	def_log.setOn(b);
	story_log.setOn(b);
    }

    public static String getLessonLang() {
	return lesson_lang;
    }

    public static void setLessonLang(String s) {
	omega.Context.lesson_log.getLogger().info("old, new: " + lesson_lang + ' ' + s);
	lesson_lang = s;
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
