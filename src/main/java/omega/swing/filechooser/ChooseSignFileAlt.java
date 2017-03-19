package omega.swing.filechooser;


import omega.OmegaContext;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChooseSignFileAlt extends JFileChooser {
    public ChooseSignFileAlt() {
        super(new File(OmegaContext.omegaAssets("media")));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension("mp4");
        setFileFilter(fi);
    }
}
