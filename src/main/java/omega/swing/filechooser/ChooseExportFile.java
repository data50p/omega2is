package omega.swing.filechooser;

import omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseExportFile extends JFileChooser {
    public static String ext = "omega_export";

    public ChooseExportFile() {
        super(new File("."));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension(ext);
        setFileFilter(fi);
    }
}
