package omega.lesson.canvas;


import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseActionFile2 extends JFileChooser {
    ChooseActionFile2() {
	super(new File("anim"));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension("omega_anim");
	fi.addExtension("mpeg");
	fi.addExtension("mpg");
	fi.addExtension("mov");
	fi.addExtension("avi");
	fi.addExtension("MPEG");
	fi.addExtension("MPG");
	fi.addExtension("MOV");
	fi.addExtension("AVI");
	setFileFilter(fi);
    }
}
