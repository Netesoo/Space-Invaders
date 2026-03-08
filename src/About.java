import javax.swing.*;

class About extends JFrame {
    public About() {
        this.setTitle("About");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel("Game something like Space Invaders S30998");
        this.setSize(300, 100);
        this.add(label);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}