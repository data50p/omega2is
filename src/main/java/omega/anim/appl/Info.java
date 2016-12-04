package omega.anim.appl;

import omega.Context;

import javax.swing.*;
import java.awt.*;

public class Info extends JPanel {
    JLabel[] info = new JLabel[3];
    JPanel panel_info;

    public Info() {
	panel_info = new JPanel();
	panel_info.setLayout(new GridLayout(0, 1));
	Font font = new Font("sans-serif", Font.PLAIN, 10);
	for (int i = 0; i < 3; i++) {
	    info[i] = new JLabel("                                    ");
	    info[i].setFont(font);
	    info[i].setForeground(i == 1 ? Color.black : Color.gray);
	    panel_info.add(info[i]);
	}
	add(panel_info);
    }

    String[] info_string;

    public void setInfo(String[] sa) {
	info_string = sa;
	setInfoIx(0);
    }

    public void setInfoIx(int ix) {
	try {
	    if (info_string == null) {
		info[0].setText(" ");
		info[1].setText(" ");
		info[2].setText(" ");
	    } else {
		if (ix > 0)
		    info[0].setText("" + (ix) + ": " + info_string[ix - 1]);
		else
		    info[0].setText(" ");
		info[1].setText("" + (ix + 1) + ": " + info_string[ix]);
		if (ix + 1 < info_string.length)
		    info[2].setText("" + (ix + 2) + ": " + info_string[ix + 1]);
		else
		    info[2].setText(" ");
	    }
	} catch (Exception ex) {
	    Context.exc_log.getLogger().throwing(Info.class.getCanonicalName(), "setinfo", ex);
	}
    }
}
