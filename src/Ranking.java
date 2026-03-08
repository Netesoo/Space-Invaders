import javax.swing.*;

public class Ranking extends JFrame {


    Ranking(JFrame menu) {
        super("Ranking");




        ImageIcon SIIcon = new ImageIcon("sources/Logofinal.png");
        this.setIconImage(SIIcon.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(980, 900);
        this.setResizable(false);
        this.setLocationRelativeTo(menu);
        this.setVisible(true);
    }
}
