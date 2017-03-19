package omega.swing.filechooser;

import omega.t9n.T;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseMovieFile extends JFileChooser {
    public ChooseMovieFile() {
        super(new File("media"));
        ExtensionFileFilter fi = new ExtensionFileFilter(new String[]{"mpg",
                "mpeg",
                "mov",
                "avi"});
        setFileFilter(fi);
        setApproveButtonText(T.t("Select"));
    }
}

