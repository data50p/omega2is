package omega.anim.appl;

import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChooseAnimatorFile extends JFileChooser {
    static String ext = "omega_anim";

    ChooseAnimatorFile() {
	super(new File("anim"));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension(ext);
	setFileFilter(fi);
    }
}

