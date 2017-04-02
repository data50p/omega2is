package com.femtioprocent.omega.swing.filechooser;

import com.femtioprocent.omega.OmegaConfig;
import com.femtioprocent.omega.OmegaContext;
import com.femtioprocent.omega.util.ExtensionFileFilter;
import com.femtioprocent.omega.util.LoggerFactory;

import javax.swing.*;
import java.io.File;

public class ChooseLessonFile extends JFileChooser {
    public static String ext = "omega_lesson";
    private static File lastFile;

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
        LoggerFactory.getLogger(ChooseLessonFile.class).info("getDir " + dirStep + ' ' + lastFile);
        if ( dirStep == -1 )
            return ".";
        if ( lastFile == null )
            return ".";
        File f = lastFile;
        LoggerFactory.getLogger(ChooseLessonFile.class).info("getDir file " + f);
        while(dirStep-- > 0 ) {
            f = f.getParentFile();
            LoggerFactory.getLogger(ChooseLessonFile.class).info("getDir parent " + f);
        }
        LoggerFactory.getLogger(ChooseLessonFile.class).info("getDir return " + f.getPath());
        return f.getPath();
    }

    public void setLastFile(File lastFile) {
        LoggerFactory.getLogger(ChooseLessonFile.class).info("getDir setLast<>File " + lastFile + " -> " + ChooseLessonFile.lastFile);
        ChooseLessonFile.lastFile = lastFile;
    }
}
