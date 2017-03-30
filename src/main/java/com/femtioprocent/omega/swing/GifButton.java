package com.femtioprocent.omega.swing;

import com.femtioprocent.omega.graphic.util.LoadImage;

import javax.swing.*;
import java.awt.*;

public class GifButton extends JButton {
    Image im;

    public GifButton(String label, String im_fn) {
        super(label);
        im = LoadImage.loadAndWait(this, "com/femtioprocent/omega/" + im_fn);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isEnabled())
            g.drawImage(im, 0, 0, null);
    }
}

