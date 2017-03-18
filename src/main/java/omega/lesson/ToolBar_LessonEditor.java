package omega.lesson;

import omega.swing.ToolBar;
import omega.swing.ToolExecute;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar_LessonEditor extends ToolBar implements ActionListener {
    ToolExecute texec;

    public ToolBar_LessonEditor(ToolExecute texec) {
        this.texec = texec;
    }

    public ToolBar_LessonEditor(ToolExecute texec, int orientation) {
        super(orientation);
        this.texec = texec;
    }

    public void populateRest() {
    }

    public void actionPerformed(ActionEvent ae) {
    }
}
