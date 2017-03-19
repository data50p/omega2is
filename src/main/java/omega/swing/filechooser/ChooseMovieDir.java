package omega.swing.filechooser;

import omega.i18n.T;

import javax.swing.*;
import java.io.File;

public class ChooseMovieDir extends JFileChooser {
    public ChooseMovieDir() {
        super(new File("media"));
        setFileSelectionMode(DIRECTORIES_ONLY);
        setApproveButtonText(T.t("Select Directory"));
    }
}

