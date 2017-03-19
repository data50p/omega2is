package omega.swing.filechooser;

import omega.OmegaContext;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseImageFile2 extends JFileChooser {
    public ChooseImageFile2() {
        super(new File(OmegaContext.omegaAssets("media")));
        ExtensionFileFilter fi = new ExtensionFileFilter(new String[]{"gif",
                "jpg",
                "jpeg",
                "png"});
        setFileFilter(fi);
        setApproveButtonText("Select");
    }
}
