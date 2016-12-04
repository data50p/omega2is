package omega.anim.panels.path;

import omega.anim.tool.path.Path;
import omega.i18n.T;
import omega.swing.GBC_Factory;
import omega.swing.properties.OmegaProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PathProperties extends OmegaProperties implements ActionListener {
    Path bound_pa = null;
    Container con;

    GBC_Factory c = new GBC_Factory();

    public PathProperties(JFrame owner) {
	super(owner);
	setTitle("Properties");
    }

    public Path getPA() {
	return (Path) obj;
    }

    public void refresh() {
	Path pa = getPA();
	if (pa != null) {
	    if (buildProperties(pa)) {
		JButton jb = new JButton(T.t("Close"));
		jb.setActionCommand("Close");
		jb.addActionListener(this);
		con.add(new JPanel().add(jb));
	    }
	    pack();
	} else {
	    omega.Context.sout_log.getLogger().info("ERR: " + "pa null");
	}
    }

    JLabel c_pa_nid;
    JLabel c_pa_len;
    JTextField c_pa_imname;

    private boolean buildProperties(Path pa) {
	boolean ret = false;
	bound_pa = pa;
	if (con == null) {
	    con = getContentPane();
	    updAll(pa);
	    con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));

	    con.add(c_pa_nid = new JLabel(T.t("Path for timeline") + ' ' + pa.nid));
	    c_pa_nid.setForeground(Color.black);
	    con.add(c_pa_len = new JLabel(T.t("length") + " = " + pa.getLength()));

	    JPanel pp = new JPanel();
	    pp.setLayout(new GridBagLayout());

	    int row = 0;
	    pp.add(new JLabel(T.t("Actor Image Name")), c.create(0, row));
	    pp.add(c_pa_imname = new JTextField(), c.create(1, row));
	    JButton jb = new JButton("...");
	    pp.add(jb, c.create(2, row));
	    row++;

	    con.add(pp);
	    ret = true;
	}
	c_pa_nid.setText(T.t("Path for timeline") + ' ' + pa.nid);
	c_pa_len.setText(T.t("length = ") + pa.getLength());
	c_pa_imname.setText(T.t("--unknown--"));
	return ret;
    }

    public void updAll(Path pa) {
    }

    public void actionPerformed(ActionEvent ev) {
	if (ev.getActionCommand().equals("Close")) {
	    setVisible(false);
	    return;
	}
	if (bound_pa != null) {
	    if (ev.getActionCommand().equals("comboBoxChanged")) {
		updAll(bound_pa);
		return;
	    } else {
		Object o = ev.getSource();
		if (o instanceof JTextField) {
		    String fname = ev.getActionCommand();
		    updAll(bound_pa);
		    return;
		}
	    }
	}
    }
}
