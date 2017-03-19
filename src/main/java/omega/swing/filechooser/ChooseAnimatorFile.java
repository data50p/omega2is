package omega.swing.filechooser;

import omega.OmegaContext;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChooseAnimatorFile extends JFileChooser {
    public static String ext = "omega_anim";

    public ChooseAnimatorFile() {
        super(new File(OmegaContext.omegaAssets("anim")));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension(ext);
        setFileFilter(fi);
    }
}

