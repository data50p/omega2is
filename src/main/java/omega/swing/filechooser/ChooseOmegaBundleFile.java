package omega.swing.filechooser;

import omega.OmegaConfig;
import omega.OmegaContext;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseOmegaBundleFile extends JFileChooser {
    public ChooseOmegaBundleFile() {
        super(new File(OmegaContext.omegaAssets("..")));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension(OmegaConfig.OMEGA_BUNDLE);
        setFileFilter(fi);
    }
}

