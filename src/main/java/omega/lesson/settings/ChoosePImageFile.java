package omega.lesson.settings;

import omega.i18n.T;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;


public class ChoosePImageFile extends JFileChooser {
    ChoosePImageFile() {
        super(new File("media"));
        ExtensionFileFilter fi = new ExtensionFileFilter(new String[]{"jpg"});
        setFileFilter(fi);
        setApproveButtonText(T.t("Select"));
    }
}
