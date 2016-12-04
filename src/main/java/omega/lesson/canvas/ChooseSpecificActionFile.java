package omega.lesson.canvas;


import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChooseSpecificActionFile extends JFileChooser {
    ChooseSpecificActionFile() {
	super(new File("."));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension("mpg");
	fi.addExtension("mpeg");
	fi.addExtension("mov");
	fi.addExtension("avi");
	fi.addExtension("MPG");
	fi.addExtension("MPEG");
	fi.addExtension("MOV");
	fi.addExtension("AVI");
	setFileFilter(fi);
    }
}
