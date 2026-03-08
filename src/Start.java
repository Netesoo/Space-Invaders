import javax.swing.*;
import java.awt.*;

public class Start extends JFrame {
    private int chosenPlane;
    private int chosenDifficulty;
    private String nickName;

    Start(JFrame menu) {
        super("Start");

        Planes planes = new Planes();
        NickModel model = new NickModel();
        NickView view = new NickView();
        NickController nickController = new NickController(model, view);
        DifficultyPanel difficultyPanel = new DifficultyPanel();

        //Front
        JButton startButton = new JButton("Start");
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> {
                SwingUtilities.invokeLater(() -> {
                    Game game = new Game(chosenDifficulty, chosenPlane);
                  /*  game.setDifficulty(chosenDifficulty);
                    game.setPlane(chosenPlane);*/
                    game.setVisible(true);
                    game.getMovingPanel().setLocation(game.getX(), game.getHeight() - game.getMovingPanel().getHeight() - 100);
                });
        });


        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        mainPanel.add(view);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        mainPanel.add(difficultyPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        mainPanel.add(planes);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        mainPanel.add(startButton);

        // JFrame
        ImageIcon SIIcon = new ImageIcon("src/sources/Logofinal.png");
        this.setIconImage(SIIcon.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(980, 900);
        this.setResizable(false);
        this.add(mainPanel);
        this.setLocationRelativeTo(menu);
        this.setVisible(true);

        // Add listener to update nickName when user enters a new nickname
        view.addTextListener(new NickView.TextListener() {
            @Override
            public void textChanged(String text) {
                nickName = text;  // Update the nickName variable
                System.out.println("Nickname set to: " + nickName);  // Print to console for verification
            }
        });

        planes.setPlaneSelectionListener(new Planes.PlaneSelectionListener() {
            @Override
            public void planeSelected(int planeNumber) {
                chosenPlane = planeNumber;
                System.out.println("Chosen Plane: " + chosenPlane); // For verification
            }
        });

        difficultyPanel.setDifficultyListener(new DifficultyPanel.DifficultyListener() {
            @Override
            public void diffcultySelected(int difficulty) {
                chosenDifficulty = difficulty;
                System.out.println("Chosen Difficulty: " + chosenDifficulty);
            }
        });

    }
}
