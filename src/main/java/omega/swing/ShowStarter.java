package omega.swing;

import omega.appl.lesson.Editor;
import omega.appl.lesson.Runtime;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by lars on 2016-12-10.
 */
public class ShowStarter extends JDialog {
private JPanel contentPane;
private JButton lessonEditorButton;
private JButton settingsButton;
private JButton animEditorButton;
private JButton lessonRuntimeButton;
private JCheckBox rememberSelectionCheckBox;

public ShowStarter() {
    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(settingsButton);

    lessonEditorButton.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    setVisible(false);
	    Editor.main(new String[]{});
	}
    });
    lessonRuntimeButton.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    setVisible(false);
	    Runtime.main(new String[]{});
	}
    });
    animEditorButton.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    setVisible(false);
	    omega.appl.animator.Editor.main(new String[]{});
	}
    });
    settingsButton.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {

	}
    });
}

public static void main(String[] args) {
    ShowStarter ss = new ShowStarter();
    ss.pack();
    ss.setVisible(true);
    System.exit(0);
}
}
