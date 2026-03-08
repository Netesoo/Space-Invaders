import javax.swing.*;
import java.awt.*;

public class Plane3Panel extends JPanel {

    Image image;

    Plane3Panel() {

        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(200,200));
        image = new ImageIcon("src/sources/plane3.png").getImage();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image, 0, 0, 200,200,null);
    }
}
