package omega.swing;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import fpdo.sundry.PreferenceUtil;
import javafx.embed.swing.JFXPanel;
import omega.appl.Settings;
import omega.appl.lesson.Editor;
import omega.appl.lesson.Runtime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by lars on 2016-12-10.
 */
public class Omega2Is extends JDialog {
    private JPanel contentPane;
    private JButton lessonEditorButton;
    private JButton settingsButton;
    private JButton animEditorButton;
    private JButton lessonRuntimeButton;
    private JCheckBox rememberSelectionCheckBox;

    String[] args;
    private static Integer selection = null;

    private static PreferenceUtil pu = new PreferenceUtil(Omega2Is.class);

    public Omega2Is() {
        super((Frame) null, "Omega2Is");
	initFx();

	HashMap settings = (HashMap) pu.getObject("settings", new HashMap());

	setTitle("Omega2Is - Selection");
	setContentPane(contentPane);
	setModal(true);
	getRootPane().setDefaultButton(settingsButton);

	lessonEditorButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
		selection = 1;
		savePref(settings);
	    }
	});
	lessonRuntimeButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
		selection = 2;
		//savePref(settings);
	    }
	});
	animEditorButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
		selection = 3;
		savePref(settings);
	    }
	});
	settingsButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
		selection = 4;
	    }
	});
    }

    public static void enableStarter() {
	HashMap settings = (HashMap) pu.getObject("settings", new HashMap());
	settings.put("selection", 0);
	pu.save("settings", settings);
    }

    private void savePref(HashMap settings) {
	if (rememberSelectionCheckBox.isSelected()) {
	    settings.put("selection", selection);
	} else {
	    settings.put("selection", 0);
	}
	pu.save("settings", settings);
    }

    public static void initFx() {
	SwingUtilities.invokeLater(() -> {
	    new JFXPanel();
	});
    }

    public static void main(String[] args) {
	HashMap settings = (HashMap) pu.getObject("settings", new HashMap());
	Integer setting_selection = (Integer) settings.get("selection");
	if (false && setting_selection != null && setting_selection > 0) {
	    selection = setting_selection;
	} else {
	    Omega2Is ss = new Omega2Is();
	    ss.args = args;
	    ss.pack();
	    ss.setVisible(true);
	}
	while (selection == null || selection == 0) {
	    try {
		Thread.sleep(200);
	    } catch (InterruptedException e) {
	    }
	}

	switch (selection) {
	    case 1:
		Editor.main(args);
		break;
	    case 2:
		Runtime.main(args);
		break;
	    case 3:
		omega.appl.animator.Editor.main(args);
		break;
	    case 4:
		Settings.main(args);
		break;
	}
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
	$$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
	contentPane = new JPanel();
	contentPane.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
	final JPanel panel1 = new JPanel();
	panel1.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
	contentPane.add(panel1, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
	lessonEditorButton = new JButton();
	lessonEditorButton.setText("Lesson Editor");
	panel1.add(lessonEditorButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	settingsButton = new JButton();
	settingsButton.setText("Settings");
	panel1.add(settingsButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	animEditorButton = new JButton();
	animEditorButton.setText("Anim Editor");
	panel1.add(animEditorButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	lessonRuntimeButton = new JButton();
	lessonRuntimeButton.setText("Lesson Runtime");
	panel1.add(lessonRuntimeButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	final Spacer spacer1 = new Spacer();
	contentPane.add(spacer1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
	final JPanel panel2 = new JPanel();
	panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
	contentPane.add(panel2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
	rememberSelectionCheckBox = new JCheckBox();
	rememberSelectionCheckBox.setText("Remember Selection");
	panel2.add(rememberSelectionCheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	final Spacer spacer2 = new Spacer();
	panel2.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
	final Spacer spacer3 = new Spacer();
	contentPane.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
	return contentPane;
    }
}
