package omega.swing;

import omega.util.Log;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Created by lars on 2017-02-18.
 */
public class SwingUtils {
    public static ImageIcon getImageIcon(String path) {
        try {
            Image im = getImage(path);
            ImageIcon icon = new ImageIcon(im);
            return icon;
        } catch (Exception e) {
            Log.getLogger().warning("Can't find ImageIcon for " + path);
            return null;
        }
    }

    public static Image getImage(String path) {
        try {
            URL url = SwingUtils.class.getClassLoader().getResource(path);
            BufferedImage c = ImageIO.read(url);
            return c;
        } catch (Exception e) {
            Image image = Toolkit.getDefaultToolkit().createImage(path);
            System.out.println("getImage() from resource did not work for: " + path + ' ' + image);
            return image;
        }
    }
}
