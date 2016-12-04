package omega.lesson.canvas;


import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChooseAudioFile extends JFileChooser {
    ChooseAudioFile() {
	super(new File("media/audio"));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension("mp3");
	fi.addExtension("wav");
	setFileFilter(fi);
    }
}
