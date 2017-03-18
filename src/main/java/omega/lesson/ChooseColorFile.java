package omega.lesson;

import omega.Context;
import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

//import omega.lesson.test.*;

public class ChooseColorFile extends JFileChooser {
    static String ext = "omega_colors";

    public ChooseColorFile() {
        super(new File(Context.omegaAssets(".")));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension(ext);
        setFileFilter(fi);
    }
}
