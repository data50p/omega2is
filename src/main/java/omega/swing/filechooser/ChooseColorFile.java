package omega.swing.filechooser;

import omega.OmegaContext;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

//import omega.lesson.test.*;

public class ChooseColorFile extends JFileChooser {
    public static String ext = "omega_colors";

    public ChooseColorFile() {
        super(new File(OmegaContext.omegaAssets(".")));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension(ext);
        setFileFilter(fi);
    }
}
