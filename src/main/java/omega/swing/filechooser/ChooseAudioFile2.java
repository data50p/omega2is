package omega.swing.filechooser;

import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseAudioFile2 extends JFileChooser {
    public ChooseAudioFile2() {
        super(new File("media/audio"));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension("wav");
        fi.addExtension("mp3");
        setFileFilter(fi);
    }
}
