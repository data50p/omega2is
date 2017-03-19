package omega.swing.filechooser;

import omega.i18n.T;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseSpeechFile extends JFileChooser {
    public ChooseSpeechFile() {
        super(new File("media"));
        ExtensionFileFilter fi = new ExtensionFileFilter(new String[]{"wav", "mp3"});
        setFileFilter(fi);
        setApproveButtonText(T.t("Select"));
    }
}


