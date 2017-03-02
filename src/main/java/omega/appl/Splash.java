package omega.appl;

import omega.Context;

import javax.swing.*;
import java.awt.*;

public class Splash extends JWindow {
    Image im;

    Splash() {
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	int ww = 500;
	int hh = 330 + 22 * 4;
	setLocation((d.width - ww) / 2, (d.height - hh) / 2);
	setSize(ww, hh);
	if (im == null)
	    im = omega.graphic.util.LoadImage.loadAndWaitFromFile(this, Context.media() + "default/omega_splash.gif");
	setBackground(Color.black);
	setVisible(true);
    }

    public void paint(Graphics g) {
	g.drawImage(im, 50, 10, null);
	g.setColor(Color.yellow);
	int line = 0;
	int yoff = 324;
	g.drawString(omega.Version.getOmegaVersion(), 5, yoff + 20 * line++);
	g.drawString(omega.Version.getCWD(), 5, yoff + 20 * line++);
	g.drawString(omega.Version.getJavaVersion(), 5, yoff + 20 * line++);
	g.drawString(omega.Version.getJavaVendor(), 5, yoff + 20 * line++);
	g.drawString(omega.Version.getJavaHome(), 5, yoff + 20 * line++);
    }
}
