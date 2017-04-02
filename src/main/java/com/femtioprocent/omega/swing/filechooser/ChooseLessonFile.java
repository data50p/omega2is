package com.femtioprocent.omega.swing.filechooser;

import com.femtioprocent.omega.OmegaContext;
import com.femtioprocent.omega.util.ExtensionFileFilter;

import javax.swing.*;
import java.io.File;

public class ChooseLessonFile extends JFileChooser {
    public static String ext = "omega_lesson";
    public static File lastFile;

    public ChooseLessonFile() {
            super(new File(OmegaContext.omegaAssets(".")));
            ExtensionFileFilter fi = new ExtensionFileFilter();
            fi.addExtension(ext);
            setFileFilter(fi);
    }

    public ChooseLessonFile(int dirStep) {
        super(new File(OmegaContext.omegaAssets(getDir(dirStep))));
        ExtensionFileFilter fi = new ExtensionFileFilter();
        fi.addExtension(ext);
        setFileFilter(fi);
    }

    private static String getDir(int dirStep) {
        if ( dirStep == -1 )
            return ".";
        if ( lastFile == null )
            return ".";
        File f = lastFile;
        while(dirStep-- > 0 ) {
            f = f.getParentFile();
        }
        return f.getPath();
    }
}