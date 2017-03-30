package omega.lesson.appl;

import omega.OmegaConfig;
import omega.OmegaContext;
import omega.help.HelpSystem;
import omega.servers.httpd.Server;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;


public class ApplLesson extends JFrame {
    public static JFrame TOP_JFRAME;
    public static HelpSystem help;
    Server httpd;
    public static boolean is_editor;

    public static boolean isMac = false;

    ApplLesson(String title, boolean is_editor) {
        super(title);
        this.is_editor = is_editor;
        TOP_JFRAME = this;
        OmegaContext.init("Httpd", null);
        httpd = ((omega.subsystem.Httpd) (OmegaContext.getSubsystem("Httpd"))).httpd;

        if (OmegaConfig.fullScreen) {
            try {
                Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
                Class params[] = new Class[]{Window.class, Boolean.TYPE};
                Method method = util.getMethod("setWindowCanFullScreen", params);
                method.invoke(util, this, true);
                isMac = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
//	getRootPane().putClientProperty("apple.awt.fullscreenable", Boolean.valueOf(true));
        /*
	if ( FullScreenUtilities.class != null ) {
	    FullScreenUtilities.setWindowCanFullScreen(this, true);
	    Application.getApplication().requestToggleFullScreen(this);
	}
	*/
        }

        help = new HelpSystem();
    }
}
