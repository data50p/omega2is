package omega.lesson.settings;

import omega.i18n.T;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseMovieFile extends JFileChooser {
    ChooseMovieFile() {
	super(new File("media"));
	ExtensionFileFilter fi = new ExtensionFileFilter(new String[]{"mpg",
		"mpeg",
		"mov",
		"avi"});
	setFileFilter(fi);
	setApproveButtonText(T.t("Select"));
    }
}

