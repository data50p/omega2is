package omega.swing.filechooser;


import omega.OmegaContext;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChooseSpecificActionFile extends JFileChooser {
    public ChooseSpecificActionFile() {
        super(new File(OmegaContext.omegaAssets("anim")));
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
