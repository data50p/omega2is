package omega.lesson.canvas.result;

import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseExportFile extends JFileChooser {
    static String ext = "omega_export";

    ChooseExportFile() {
	super(new File("."));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension(ext);
	setFileFilter(fi);
    }
}
