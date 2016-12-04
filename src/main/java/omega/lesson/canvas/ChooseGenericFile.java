package omega.lesson.canvas;


import javax.swing.*;
import java.io.File;


public class ChooseGenericFile extends JFileChooser {
    ChooseGenericFile() {
	super(new File("."));
    }
}


