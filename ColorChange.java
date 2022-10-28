import javax.swing.*;
import java.awt.*;

public class ColorChange extends GUI {

    public static void colorChange(JPanel container) {
        int r = (int) (Math.random()*256);
        int g = (int) (Math.random()*256);
        int b = (int) (Math.random()*256);
        Color color = new Color (r,g,b);
        container.setBackground(color);
    }


}
