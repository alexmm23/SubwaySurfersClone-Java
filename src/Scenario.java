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
    private Obstacle[] obstacles;
    private int currentLane = 1; // 0, 1, 2
    private JLabel scoreLabel;
    private Timer timer;
    private int score = 0;
    private int width = 800;
    private int height = 800;
    private int speed = 10;
    private ArrayList<Coin> coins;
    private Image railsImage;


    public Scenario() {
        player = new Player("Player", 0, 0);
        player.setLane(currentLane);
        obstacles = new Obstacle[3];
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = new Obstacle(800, 50, i % 3);
        }
        Random rand = new Random();
        int visibleCount = 0;
        while (visibleCount < 2) {
            int index = rand.nextInt(obstacles.length);
            if (!obstacles[index].isVisible()) {
                obstacles[index].setVisible(true);
                visibleCount++;
                System.out.println("Visible count: " + visibleCount);
            }
        }
        coins = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            coins.add(new Coin(800, i * 200 + 50));
        }
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
        //resizes the image to fit the screen

        railsImage = Toolkit.getDefaultToolkit().getImage("src/assets/rieles.png");
    }

    public void updateGame() {
        player.setLane(currentLane);
        int visibleCount = 0;
        Random rand = new Random();
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isVisible()) {
                visibleCount++;
            }
            if (obstacle.getX() <= 0) {
                System.out.println("Obstacle is not visible");
                visibleCount--;
                //obstacle.setVisible(false);
                obstacle.setLane(rand.nextInt(1, 3));
            }
            obstacle.update(speed);
        }
        if (visibleCount < 2) {
            int index = rand.nextInt(obstacles.length);
            if (!obstacles[index].isVisible()) {
                obstacles[index].setVisible(true);
            }
        }
        for (Coin coin : coins) {
            coin.update(speed);
        }
        if (checkCoinCollision()) {
            score += 10;
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
        g.drawImage(railsImage, 0, 50,width,100, null);
        g.drawImage(railsImage, 0, 450,width,100, null);
        g.drawImage(railsImage, 0, 250,width,100, null);
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
