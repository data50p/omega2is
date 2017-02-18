package omega.lesson.canvas.result;

import omega.Config;
import omega.Context;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseOmegaBundleFile extends JFileChooser {
    public ChooseOmegaBundleFile() {
        this(null);
    }

    public ChooseOmegaBundleFile(Boolean zipped) {
	super(new File(Context.omegaAssets("..")));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	fi.addExtension(Config.OMEGA_BUNDLE);
	setFileFilter(fi);
    }
}
