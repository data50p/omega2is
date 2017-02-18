package omega.swing.properties;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by lars on 2017-02-18.
 */
public class SwingUtils {
    public static ImageIcon getImageIcon(String path) {
        try {
            // will fail miserably in eclipse and after exporting to jar
            URL imageURL = SwingUtils.class.getClassLoader().getResource(path);
            ImageIcon icon = new ImageIcon(imageURL);
            return icon;
        } catch (Exception e) {
            // works like a char in eclipse and after creating the jar file
            // with the files in the same directory
            Image image = Toolkit.getDefaultToolkit().createImage(path);
            System.out.println("getResoruce() did not work");
            ImageIcon icon = new ImageIcon(image);
            return icon;
        }
    }

    public static Image getImage(String path) {
        try {
            ImageIcon icon = getImageIcon(path);
            return icon.getImage();
        } catch (Exception e) {
            // works like a char in eclipse and after creating the jar file
            // with the files in the same directory
            Image image = Toolkit.getDefaultToolkit().createImage(path);
            return image;
        }
    }
}
