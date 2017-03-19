package omega.swing.filechooser;

import omega.Context;
import omega.i18n.T;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseImageFile extends JFileChooser {
    public ChooseImageFile() {
        super(new File(Context.omegaAssets("media")));
        ExtensionFileFilter fi = new ExtensionFileFilter(new String[]{"gif",
                "jpg",
                "jpeg",
                "png"});
        setFileFilter(fi);
        setApproveButtonText(T.t("Select"));
    }
}
