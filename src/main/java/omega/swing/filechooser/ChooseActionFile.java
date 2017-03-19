package omega.swing.filechooser;

import omega.OmegaContext;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseActionFile extends JFileChooser {
    public ChooseActionFile() {
        super(new File(OmegaContext.omegaAssets(".")));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension("omega_lesson");
        setFileFilter(fi);
    }
}

