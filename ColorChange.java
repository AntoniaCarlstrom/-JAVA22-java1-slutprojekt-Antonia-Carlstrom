import javax.swing.*;
import java.awt.*;
/**
 * Class for methods related to fun color changing things
 */
public class ColorChange {
    /**
     * The colorChange method sets the color of the background panel at random
     */
    public static void colorChange(JPanel container) {
        int r = (int) (Math.random()*256);
        int g = (int) (Math.random()*256);
        int b = (int) (Math.random()*256);
        Color color = new Color (r,g,b);
        container.setBackground(color);
    }


}
