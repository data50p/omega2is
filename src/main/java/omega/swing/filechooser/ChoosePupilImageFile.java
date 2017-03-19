package omega.swing.filechooser;

import omega.t9n.T;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChoosePupilImageFile extends JFileChooser {
    public ChoosePupilImageFile() {
        super(new File("media"));
        ExtensionFileFilter fi = new ExtensionFileFilter(new String[]{"jpg"});
        setFileFilter(fi);
        setApproveButtonText(T.t("Select"));
    }
}
