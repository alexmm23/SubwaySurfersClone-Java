import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Scenario extends JPanel implements KeyListener, ActionListener {
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Coin> coins;
    private int currentLane = 1; // 0, 1, 2
    private JLabel scoreLabel;
    private Timer timer;
    private int score = 0;
    private int width = 800;
    private int height = 800;
    private int speed = 10;
    private Image railsImage;
    private Random rand;

    public Scenario() {
        player = new Player("Player", 0, 0);
        player.setLane(currentLane);
        obstacles = new ArrayList<>();
        coins = new ArrayList<>();
        rand = new Random();

        generateObstacles();
        generateCoins();

        scoreLabel = new JLabel("Score: " + score);
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
            }
        });
        timer.start();
        add(scoreLabel);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        railsImage = Toolkit.getDefaultToolkit().getImage("src/assets/rieles.png");
    }

    private void generateObstacles() {
        int numObstacles = rand.nextInt(3) + 1; // Random number of obstacles (1 to 3)
        for (int i = 0; i < numObstacles; i++) {
            int lane = rand.nextInt(3); // Random lane (0, 1, 2)
            Obstacle obstacle = new Obstacle(width, lane * 200 + 50, lane);
            obstacle.setVisible(true);
            obstacles.add(obstacle);
        }
    }

    private void generateCoins() {
        int numCoins = rand.nextInt(3) + 1; // Random number of coins (1 to 3)
        for (int i = 0; i < numCoins; i++) {
            int lane = rand.nextInt(3); // Random lane (0, 1, 2)
            Coin coin = new Coin(width, lane * 200 + 50);
            coin.setVisible(true);
            coins.add(coin);
        }
    }

    public void updateGame() {
        player.setLane(currentLane);
        for (Obstacle obstacle : obstacles) {
            obstacle.update(speed);
        }
        for (Coin coin : coins) {
            coin.update(speed);
        }
        if (checkCoinCollision()) {
            score += 100;
        }
        if (checkCollision()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over! Your score is: " + score);
        }
        score++;
        scoreLabel.setText("Score: " + score);
        repaint();
    }

    private boolean checkCollision() {
        for (Obstacle obstacle : obstacles) {
            if (player.getX() < obstacle.getX() + 50 && player.getX() + 50 > obstacle.getX() && player.getY() < obstacle.getY() + 50 && player.getY() + 50 > obstacle.getY() && !player.isJumping()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCoinCollision() {
        for (Coin coin : coins) {
            if (player.getX() < coin.getX() + 50 && player.getX() + 50 > coin.getX() && player.getY() < coin.getY() + 50 && player.getY() + 50 > coin.getY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLanes(g);
        player.draw(g);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
        for (Coin coin : coins) {
            coin.draw(g);
        }

    }

    public void drawLanes(Graphics g) {
        g.setColor(Color.GRAY);
        //Set the rieles.png image as the background
        g.fillRect(0, 0, width, height);
        g.setColor(Color.GRAY);

        g.fillRect(0, 50, width, height);
        g.setColor(Color.WHITE);
        g.fillRect(0, 50, width, 100);
        g.fillRect(0, 250, width, 100);
        g.fillRect(0, 450, width, 100);
        g.drawImage(railsImage, 0, 50, width, 100, null);
        g.drawImage(railsImage, 0, 450, width, 100, null);
        g.drawImage(railsImage, 0, 250, width, 100, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.jump();
                System.out.println("UP");
                break;
            case KeyEvent.VK_RIGHT:
                if (currentLane < 2) {
                    currentLane++;
                }
                player.setLane(currentLane);
                System.out.println("RIGHT");
                break;
            case KeyEvent.VK_DOWN:
                //player.move(2);
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                if (currentLane > 0) {
                    currentLane--;
                }
                player.setLane(currentLane);
                System.out.println("LEFT");
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
    }
}
