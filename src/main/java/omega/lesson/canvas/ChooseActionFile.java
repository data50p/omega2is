package omega.lesson.canvas;

import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseActionFile extends JFileChooser {
    ChooseActionFile() {
	super(new File("."));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension("omega_lesson");
	setFileFilter(fi);
    }
}

