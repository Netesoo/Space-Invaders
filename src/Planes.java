import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Planes extends JPanel implements ActionListener {

    private String nickName;
    private int planeNumber;
    private JRadioButton plane1Button;
    private JRadioButton plane2Button;
    private JRadioButton plane3Button;
    private Plane1Panel p1 = new Plane1Panel();
    private Plane2Panel p2 = new Plane2Panel();
    private Plane3Panel p3 = new Plane3Panel();

    private PlaneSelectionListener listener;

    Planes() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(200, 200));
        this.setLayout(new GridLayout(2, 1, 5, 5));

        plane1Button = new JRadioButton("Plane1");
        plane1Button.setBackground(Color.WHITE);
        plane1Button.setFocusable(false);
        plane1Button.addActionListener(this);

        plane2Button = new JRadioButton("Plane2");
        plane2Button.setBackground(Color.WHITE);
        plane2Button.setFocusable(false);
        plane2Button.addActionListener(this);

        plane3Button = new JRadioButton("Plane3");
        plane3Button.setBackground(Color.WHITE);
        plane3Button.setFocusable(false);
        plane3Button.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(plane1Button);
        group.add(plane2Button);
        group.add(plane3Button);

        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(plane1Button);
        this.add(plane2Button);
        this.add(plane3Button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == plane1Button) {
            planeNumber = 1;
        } else if (e.getSource() == plane2Button) {
            planeNumber = 2;
        } else if (e.getSource() == plane3Button) {
            planeNumber = 3;
        }
        if (listener != null) {
            listener.planeSelected(planeNumber);
        }
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPlaneNumber(int planeNumber) {
        this.planeNumber = planeNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public int getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneSelectionListener(PlaneSelectionListener listener) {
        this.listener = listener;
    }

    public interface PlaneSelectionListener {
        void planeSelected(int planeNumber);
    }
}
