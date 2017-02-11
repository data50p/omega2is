package omega.lesson.canvas;


import omega.Context;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChooseSpecificActionFile extends JFileChooser {
    ChooseSpecificActionFile() {
	super(new File(Context.omegaAssets("media")));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension("mpg");
	fi.addExtension("mpeg");
	fi.addExtension("mov");
	fi.addExtension("avi");
	fi.addExtension("MPG");
	fi.addExtension("MPEG");
	fi.addExtension("MOV");
	fi.addExtension("AVI");
	fi.addExtension("mp4");
	fi.addExtension("mpv");
	fi.addExtension("MP4");
	fi.addExtension("MPV");
	setFileFilter(fi);
    }
}
