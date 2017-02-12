package omega.lesson.canvas.result;

import omega.Context;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseOmegaAssetsFile extends JFileChooser {
    public ChooseOmegaAssetsFile() {
        this(null);
    }

    public ChooseOmegaAssetsFile(Boolean zipped) {
	super(new File(Context.omegaAssets("..")));
	ExtensionFileFilter fi = new ExtensionFileFilter();
	if ( zipped == null ) {
	    fi.addExtension("omega_assets");
	    fi.addExtension("omega_assets.zip");
	} else if ( zipped ) {
	    fi.addExtension("omega_assets.zip");
	} else {
	    fi.addExtension("omega_assets");
	}
	setFileFilter(fi);
    }
}

