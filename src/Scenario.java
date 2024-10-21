import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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


    public Scenario() {
        player = new Player("Player", 0, 0);
        player.setLane(currentLane);
        obstacles = new Obstacle[10];
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = new Obstacle(0, 50, 0);
        }
        scoreLabel = new JLabel("Score: " + score);
        timer = new Timer(1000, this);
        timer.start();
        add(scoreLabel);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
    }

    public void updateGame() {
        player.setLane(currentLane);
        if (timer.isRunning() && score % 10 == 0) {
            speed += 20;
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.update(speed);
        }
        score += timer.getDelay() / 100;
        scoreLabel.setText("Score: " + score);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLanes(g);
        player.draw(g);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
    }

    public void drawLanes(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 50, width, height);
        g.setColor(Color.WHITE);
        g.fillRect(0, 50, width, 100);
        g.fillRect(0, 250, width, 100);
        g.fillRect(0, 450, width, 100);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                //jump
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
