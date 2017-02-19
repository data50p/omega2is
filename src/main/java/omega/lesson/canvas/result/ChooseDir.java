package omega.lesson.canvas.result;

import omega.Context;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by lars on 2017-02-19.
 */

public class ChooseDir extends JFileChooser {
    public ChooseDir() {
	super(new File(Context.omegaAssets(".")));
	FileFilter fi = new FileFilter() {
	    @Override
	    public boolean accept(File f) {
		return f.isDirectory();
	    }

	    @Override
	    public String getDescription() {
		return null;
	    }
	};
	setFileFilter(fi);
	setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }
}
