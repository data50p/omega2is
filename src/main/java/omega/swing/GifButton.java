package omega.swing;

import javax.swing.*;
import java.awt.*;

public class GifButton extends JButton {
    Image im;

    public GifButton(String label, String im_fn) {
        super(label);
        im = omega.graphic.util.LoadImage.loadAndWait(this, "omega/" + im_fn);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isEnabled())
            g.drawImage(im, 0, 0, null);
    }
}

