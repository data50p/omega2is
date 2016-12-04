package omega.anim.panels.timeline;

import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseAudioFile extends JFileChooser {
    ChooseAudioFile() {
	super(new File("media/audio"));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension("wav");
	fi.addExtension("mp3");
	setFileFilter(fi);
    }
}
