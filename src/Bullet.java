import javax.swing.*;
import java.awt.*;

public class Bullet extends JPanel {

    public Bullet(int x, int y) {
        setSize(10, 20);
        setLocation(x, y);
        setBackground(Color.RED);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
