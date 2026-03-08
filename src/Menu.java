import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    JMenuItem aboutItem;
    JButton buttonStart;

    JButton buttonRules;
    JButton buttonRanking;
    Menu() {
        super("Space Invaders");

        //Graphics import and background and icon setting
        ImageIcon SIIcon = new ImageIcon("src/sources/Logofinal.png");
        this.setIconImage(SIIcon.getImage());
        JLabel label = new JLabel();
        label.setIcon(SIIcon);
        label.setHorizontalTextPosition(JLabel.HORIZONTAL);

        //JButton Rules
        buttonRules = new JButton("Rules");
        buttonRules.setBounds(263, 750, 140, 40);
        buttonRules.setFocusable(false);
        buttonRules.addActionListener(e -> new Rules(this));

        //JButton Start
        buttonStart = new JButton("Start");
        buttonStart.setBounds(413, 750, 140, 40);
        buttonStart.setFocusable(false);
        buttonStart.addActionListener(e -> new Start(this));

        //JButton Ranking
        buttonRanking = new JButton("Ranking");
        buttonRanking.setBounds(563, 750, 140, 40);
        buttonRanking.setFocusable(false);
        buttonRanking.addActionListener(e -> new Ranking(this));

        //JMenuBar

        JMenuBar menuBar = new JMenuBar();

        JMenu helpMenu = new JMenu("Help");

        aboutItem = new JMenuItem("About");

        aboutItem.addActionListener(this);

        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);

        //JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(980, 900);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(buttonRanking);
        this.add(buttonRules);
        this.add(buttonStart);
        this.add(label);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aboutItem) {
            new About();
        }
    }

}
