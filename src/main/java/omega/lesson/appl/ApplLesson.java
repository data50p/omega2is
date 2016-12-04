package omega.lesson.appl;

import omega.connect.httpd.Server;
import omega.help.HelpSystem;

import javax.swing.*;


public class ApplLesson extends JFrame {
    public static JFrame TOP_JFRAME;
    public static HelpSystem help;
    Server httpd;
    public static boolean is_editor;

    ApplLesson(String title, boolean is_editor) {
	super(title);
	this.is_editor = is_editor;
	TOP_JFRAME = this;
	omega.Context.init("Httpd", null);
	httpd = ((omega.subsystem.Httpd) (omega.Context.getSubsystem("Httpd"))).httpd;

	help = new HelpSystem();
    }
}
