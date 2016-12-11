package omega.appl;

import omega.Context;

import javax.swing.*;
import java.awt.*;

public class Splash extends JWindow {
    Image im;

    Splash() {
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	int ww = 700;
	int hh = 330 + 22 * 3;
	setLocation((d.width - ww) / 2, (d.height - hh) / 2);
	setSize(ww, hh);
	if (im == null)
	    im = omega.graphic.util.LoadImage.loadAndWaitFromFile(this, "media/default/omega_splash.gif");
	setBackground(Color.black);
	setVisible(true);
    }

    public void paint(Graphics g) {
	g.drawImage(im, 0, 0, null);
	g.setColor(Color.yellow);
	g.drawString(omega.Version.getVersion(), 5, 12);
	g.drawString(omega.Version.getCWD(), 5, 322 + 20 * 0);
	g.drawString(omega.Version.getJavaVersion(), 5, 322 + 20 * 1);
	g.drawString(omega.Version.getXXX(), 5, 322 + 20 * 2);
	g.drawString(omega.Version.getYYY(), 5, 322 + 20 * 3);
    }
}
