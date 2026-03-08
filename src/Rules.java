import javax.swing.*;
import java.awt.*;

public class Rules extends JFrame {

    Rules(JFrame menu) {
        super("Rules");


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel linijka1 = new JLabel("Rules: ");
        JLabel linijka2 = new JLabel("Your job is to move left and right and shoot at incoming enemies.");
        JLabel linijka3 = new JLabel("Every enemy hit is 1 points");
        JLabel linijka4 = new JLabel("You will lose if the enemies reach you");

        linijka1.setAlignmentX(Component.CENTER_ALIGNMENT);
        linijka2.setAlignmentX(Component.CENTER_ALIGNMENT);
        linijka3.setAlignmentX(Component.CENTER_ALIGNMENT);
        linijka4.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(linijka1);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(linijka2);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(linijka3);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(linijka4);
        panel.add(Box.createRigidArea(new Dimension(0, 70)));


        ImageIcon SIIcon = new ImageIcon("sources/Logofinal.png");
        this.setIconImage(SIIcon.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 200);
        this.setResizable(false);
        this.add(panel);
        this.setVisible(true);
        this.setLocationRelativeTo(menu);

    }
}
