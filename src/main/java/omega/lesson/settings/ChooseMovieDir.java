package omega.lesson.settings;

import omega.i18n.T;

import javax.swing.*;
import java.io.File;

public class ChooseMovieDir extends JFileChooser {
    ChooseMovieDir() {
        super(new File("media"));
        setFileSelectionMode(DIRECTORIES_ONLY);
        setApproveButtonText(T.t("Select Directory"));
    }
}

