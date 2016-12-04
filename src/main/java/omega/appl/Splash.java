package omega.appl;

import javax.swing.*;
import java.awt.*;

public class Splash extends JWindow {
    Image im;

    Splash() {
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	int ww = 400;
	int hh = 300;
	setLocation((d.width - ww) / 2, (d.height - hh) / 2);
	setSize(ww, hh);
	if (im == null)
	    im = omega.graphic.util.LoadImage.loadAndWaitFromFile(this, "media/default/omega_splash.gif");
	show();
    }

    public void paint(Graphics g) {
	g.drawImage(im, 0, 0, null);
	g.setColor(Color.yellow);
	g.drawString(omega.Version.getVersion(), 5, 12);
    }

    public void hide() {
	super.hide();
    }
}
