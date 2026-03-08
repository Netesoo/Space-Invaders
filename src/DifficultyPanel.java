import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyPanel extends JPanel implements ActionListener {

    private int difficulty;
    private JRadioButton easy;
    private JRadioButton medium;
    private JRadioButton hard;
    private DifficultyListener listener;

    DifficultyPanel() {

        easy = new JRadioButton("Easy");
        easy.setBackground(Color.WHITE);
        easy.setFocusable(false);
        easy.addActionListener(this);

        medium = new JRadioButton("Medium");
        medium.setBackground(Color.WHITE);
        medium.setFocusable(false);
        medium.addActionListener(this);

        hard = new JRadioButton("Hard");
        hard.setBackground(Color.WHITE);
        hard.setFocusable(false);
        hard.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(easy);
        group.add(medium);
        group.add(hard);

        this.setLayout(new GridLayout(1,1));
        this.add(easy);
        this.add(medium);
        this.add(hard);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easy) {
            difficulty = 1;
        }
        else if (e.getSource() == medium) {
            difficulty = 3;
        }
        else if (e.getSource() == hard) {
            difficulty = 5;
        }
        if (listener != null) {
            listener.diffcultySelected(difficulty);
        }
    }

    public void setDifficultyListener(DifficultyListener listener){
        this.listener = listener;
    }

    public interface DifficultyListener {
        void diffcultySelected(int difficulty);
    }
}
