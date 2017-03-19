package omega.swing.filechooser;

import omega.i18n.T;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChoosePImageFile extends JFileChooser {
    public ChoosePImageFile() {
        super(new File("media"));
        ExtensionFileFilter fi = new ExtensionFileFilter(new String[]{"jpg"});
        setFileFilter(fi);
        setApproveButtonText(T.t("Select"));
    }
}
