package omega.lesson;

import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseLessonFile extends JFileChooser {
    public static String ext = "omega_lesson";

    public ChooseLessonFile() {
	super(new File("."));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension(ext);
	setFileFilter(fi);
    }
}