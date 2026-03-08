import javax.swing.*;
import java.awt.*;

public class NickView extends JPanel {
    private JTextField nickField;
    private JLabel nickLabel;
    private JButton nickButton;
    private JLabel greetingLabel;

    public NickView() {

        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout());

        nickLabel = new JLabel("Enter nickname");
        nickField = new JTextField(20);
        nickButton = new JButton("Enter");
        nickButton.setFocusable(false);
        greetingLabel = new JLabel("");  // Etykieta do wyświetlania powitania

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.add(nickLabel);
        topPanel.add(nickField);
        topPanel.add(nickButton);

        this.add(nickLabel);
        this.add(topPanel);
        this.add(greetingLabel);  // Dodajemy etykietę do głównego panelu

    }

    public void setNick(String nick) {
        nickField.setText(nick);
    }

    public String getNickname() {
        return nickField.getText();
    }

    public void setDisplayText(String nick) {
        nickField.setText(nick);
    }

    public void addTextListener(TextListener listener) {
        nickButton.addActionListener(e -> {
            String nick = nickField.getText();
            listener.textChanged(nick);
            greetingLabel.setText("Cześć " + nick);  // Ustawiamy tekst powitania
        });
    }

    interface TextListener {
        void textChanged(String text);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Nickname View");
        NickView nickView = new NickView();
        nickView.addTextListener(text -> System.out.println("Nickname entered: " + text));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(nickView);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
