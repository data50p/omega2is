package omega.lesson.canvas;

import omega.Context;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseActionFile extends JFileChooser {
    ChooseActionFile() {
        super(new File(Context.omegaAssets(".")));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension("omega_lesson");
        setFileFilter(fi);
    }
}

