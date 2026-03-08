import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class Game extends JFrame {
    private CustomPanel movingPanel;
    private int x = 50;
    private final int y;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private boolean movingRight = true;
    private boolean gameOver = false;
    private int score = 0;
    private JLabel scoreLabel;
    private int difficulty;
    private int plane;
    private String path;

    public Game(int difficulty, int plane) {
        setTitle("Game");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setDifficulty(difficulty);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Area where the moving panel will be placed
        JPanel centerPanel = new JPanel(null);
        centerPanel.setBackground(Color.WHITE);

        // Calculate initial y position to place the panel at the bottom
        y = getHeight() - 150;

        // Moving panel
        movingPanel = createCustomPanelBasedOnPlane(plane);
        movingPanel.setSize(50, 50);
        movingPanel.setLocation(x, y);

        // Add moving panel to center panel
        centerPanel.add(movingPanel);

        // Initialize enemy and bullet lists
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();

        // Add center panel to main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JPanel scorePanel = new JPanel();
        scorePanel.add(scoreLabel);
        mainPanel.add(scorePanel, BorderLayout.NORTH);


        // Create buttons
        JButton leftButton = new JButton("Left");
        JButton shootButton = new JButton("Shoot");
        JButton rightButton = new JButton("Right");

        // Add action listeners to buttons
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveLeft(centerPanel);
                Game.this.requestFocusInWindow();
            }
        });

        shootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoot(centerPanel);
                Game.this.requestFocusInWindow();
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRight(centerPanel);
                Game.this.requestFocusInWindow();
            }
        });

        // Create a panel for buttons and add them
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(leftButton);
        buttonPanel.add(shootButton);
        buttonPanel.add(rightButton);

        // Add button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        // Key listener for arrow keys and shooting
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver) return;

                int key = e.getKeyCode();
                int panelWidth = movingPanel.getWidth();
                int frameWidth = getWidth() - getInsets().left - getInsets().right;

                // Move the panel based on arrow key pressed
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        moveLeft(centerPanel);
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRight(centerPanel);
                        break;
                    case KeyEvent.VK_SPACE:
                        shoot(centerPanel);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // Make sure the frame is focusable to capture key events
        setFocusable(true);

        // Schedule enemy creation and movement down
        Timer enemyManagementTimer = new Timer();
        enemyManagementTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!gameOver) {
                    SwingUtilities.invokeLater(() -> addRowAndMoveEnemiesDown(centerPanel));
                }
            }
        }, 0, 5000);

        // Schedule enemy movement left and right
        Timer moveSideTimer = new Timer();
        moveSideTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!gameOver) {
                    SwingUtilities.invokeLater(() -> moveEnemiesSide(centerPanel));
                }
            }
        }, 0, 2500);

        // Schedule bullet movement
        Timer bulletTimer = new Timer();
        bulletTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!gameOver) {
                    SwingUtilities.invokeLater(() -> moveBullets(centerPanel));
                }
            }
        }, 0, 50);
    }

    private void addRowAndMoveEnemiesDown(JPanel centerPanel) {
        // Move existing enemies down
        for (Enemy enemy : enemies) {
            Point location = enemy.getLocation();
            location.y += 60; // Move enemy down by 60 pixels
            enemy.setLocation(location);

            // Check for collision with the player
            if (location.y + enemy.getHeight() >= y && Math.abs(location.x - x) < enemy.getWidth()) {
                gameOver = true;
                JOptionPane.showMessageDialog(this, "Game Over! Enemy has reached you.");
                return;
            }
        }

        // Add a new row of enemies at the top
        addEnemies(centerPanel);

        centerPanel.repaint();
    }

    private void addEnemies(JPanel centerPanel) {
        int numEnemies = difficulty;
        int enemyWidth = 50;
        int enemyHeight = 50;
        int spacing = 10;
        int startX = 10;
        int yPosition = 10;

        for (int i = 0; i < numEnemies; i++) {
            Enemy enemy = new Enemy("src/sources/enemyfinal.png");
            enemy.setSize(enemyWidth, enemyHeight);
            int xPosition = startX + i * (enemyWidth + spacing);
            enemy.setLocation(xPosition, yPosition);
            enemies.add(enemy);
            centerPanel.add(enemy);
        }
    }

    private void moveEnemiesSide(JPanel centerPanel) {
        if (enemies.isEmpty()) return;

        int frameWidth = getWidth() - getInsets().left - getInsets().right;
        int enemyWidth = enemies.get(0).getWidth();

        for (Enemy enemy : enemies) {
            Point location = enemy.getLocation();
            if (movingRight) {
                location.x += 50; // Move enemy right by 50 pixels
            } else {
                location.x -= 50; // Move enemy left by 50 pixels
            }
            enemy.setLocation(location);
        }

        // Check if any enemy has reached the edge of the window
        for (Enemy enemy : enemies) {
            Point location = enemy.getLocation();
            if (location.x + enemyWidth >= frameWidth || location.x <= 0) {
                movingRight = !movingRight; // Change direction
                break;
            }
        }

        centerPanel.repaint();
    }

    private void shoot(JPanel centerPanel) {
        Bullet bullet = new Bullet(x + movingPanel.getWidth() / 2 - 5, y);
        bullets.add(bullet);
        centerPanel.add(bullet);
    }

    private void moveBullets(JPanel centerPanel) {
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Point location = bullet.getLocation();
            location.y -= 10; // Move bullet up by 10 pixels
            bullet.setLocation(location);

            // Remove bullet if it goes off the screen
            if (location.y < 0) {
                bulletIterator.remove();
                centerPanel.remove(bullet);
                continue;
            }

            // Check for collision with enemies
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    // Remove bullet and enemy
                    bulletIterator.remove();
                    enemyIterator.remove();
                    centerPanel.remove(bullet);
                    centerPanel.remove(enemy);

                    // Update score
                    score++;
                    scoreLabel.setText("Score: " + score);
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    break;
                }
            }
        }

        centerPanel.repaint();
    }

    private void moveLeft(JPanel centerPanel) {
        int frameWidth = getWidth() - getInsets().left - getInsets().right;
        if (x - 10 >= 0) {
            x -= 10;
            movingPanel.setLocation(x, y);
            centerPanel.repaint();
        }
    }

    private void moveRight(JPanel centerPanel) {
        int panelWidth = movingPanel.getWidth();
        int frameWidth = getWidth() - getInsets().left - getInsets().right;
        if (x + panelWidth + 10 <= frameWidth) {
            x += 10;
            movingPanel.setLocation(x, y);
            centerPanel.repaint();
        }
    }

    private CustomPanel createCustomPanelBasedOnPlane(int plane) {
        switch (plane) {
            case 1:
                return new CustomPanel("src/sources/plane1.png");
            case 2:
                return new CustomPanel("src/sources/plane2.png");
            case 3:
                return new CustomPanel("src/sources/plane3.png");
            default:
                return new CustomPanel("src/sources/plane3.png");
        }
    }

    public CustomPanel getMovingPanel() {
        return movingPanel;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setMovingPanel(CustomPanel movingPanel) {
        this.movingPanel = movingPanel;
    }

    public void setPlane(int plane) {
        this.plane = plane;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}
