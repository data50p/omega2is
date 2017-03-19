package omega.swing.filechooser;


import omega.Context;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChooseSignFile extends JFileChooser {
    public ChooseSignFile() {
        super(new File(Context.omegaAssets("media/sign")));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension("mpg");
        setFileFilter(fi);
    }
}
