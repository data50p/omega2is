package omega;

import javax.swing.*;
import java.awt.event.*;

public class ShowLicense extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea1;
    String s = "Licence Agreement\n" +
	    "Please review the licence terms before installing Omega-is.\n" +
	    "Copyright (C) 2004 The Omega-is Group:\n" +
	    "Heimann, Lundälv, Tjus, Nelson\n" +
	    "Topic Dos Hb, Meloton Hb, Tomas Tjus Psykologbyrå SuperImpact Images Inc.\n" +
	    "All rights reserved.\n" +
	    "This software is provided as-is, without any express or implied warranty.\n" +
	    "In no event will the authors or suppliers be held liable for any damages\n" +
	    "arising from the use of this software. Warning: This computer program is\n" +
	    "protected by copyright law and international treaties.\n" +
	    "Unauthorized reproduction or distribution of this program, or any portion\n" +
	    "of it may result in severe civil and criminal penalties, and will be\n" +
	    "prosecuted to the maximum extent as possible under law.\n" +
	    "Title, ownership rights, and intellectual property rights in and to the\n" +
	    "software shall remain with The Omega-is Group.  You agree to abide by the\n" +
	    "copyright law and all other applicable including, but not limited to,\n" +
	    "export control laws.  You acknowledge that the software in source code form remains\n" +
	    "a confidential trade secret of The Omega-is Group and therefore you agree\n" +
	    "not to modify the software\n" +
	    "or attempt to decipher, decompile, disassemble or reverse engineer the software,\n" +
	    "except to the extent&#xa;applicable laws specifically prohibit such restriction.\n" +
	    "--------------------------------------------------\n" +
	    "If you accept all the terms of the agreement, choose I accept... to continue.\n" +
	    "You must accept the agreement to install Omega-is.";

    Boolean accepted = null;

    public ShowLicense() {
	setContentPane(contentPane);
	setModal(true);
	getRootPane().setDefaultButton(buttonOK);

	textArea1.append(s);
	buttonOK.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		onOK();
	    }
	});

	buttonCancel.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		onCancel();
	    }
	});

	// call onCancel() when cross is clicked
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		onCancel();
	    }
	});

	// call onCancel() on ESCAPE
	contentPane.registerKeyboardAction(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		onCancel();
	    }
	}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        accepted = true;
	dispose();
    }

    private void onCancel() {
        accepted = false;
	dispose();
    }

    public static void main(String[] args) {
	ShowLicense dialog = new ShowLicense();
	dialog.pack();
	dialog.setVisible(true);
	System.exit(0);
    }
}
