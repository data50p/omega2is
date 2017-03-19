package omega.swing.filechooser;

import omega.Context;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChooseAnimatorFile extends JFileChooser {
    public static String ext = "omega_anim";

    public ChooseAnimatorFile() {
        super(new File(Context.omegaAssets("anim")));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension(ext);
        setFileFilter(fi);
    }
}

