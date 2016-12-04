package omega.lesson.canvas;


import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChooseSignFile extends JFileChooser {
    ChooseSignFile() {
	super(new File("media/sign"));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension("mpg");
	setFileFilter(fi);
    }
}
