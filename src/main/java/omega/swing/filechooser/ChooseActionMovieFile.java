package omega.swing.filechooser;


import omega.OmegaContext;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseActionMovieFile extends JFileChooser {
    public ChooseActionMovieFile() {
        super(new File(OmegaContext.omegaAssets("anim")));
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
        fi.addExtension("mp4");
        fi.addExtension("mpv");
        fi.addExtension("MP4");
        fi.addExtension("MPV");
        setFileFilter(fi);
    }
}
