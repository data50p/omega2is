package omega.lesson.settings;

import omega.i18n.T;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseImageFile extends JFileChooser {
    ChooseImageFile() {
	super(new File("media"));
	ExtensionFileFilter fi = new ExtensionFileFilter(new String[]{"gif",
		"jpg",
		"jpeg",
		"png"});
	setFileFilter(fi);
	setApproveButtonText(T.t("Select"));
    }
}
