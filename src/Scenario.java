import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Scenario extends JPanel implements KeyListener {
    private Player player;
    private Obstacle[] obstacles;
    private int currentLane = 1; // 0, 1, 2
    public Scenario() {
        player = new Player("Player", 0, 0);
        obstacles = new Obstacle[10];
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = new Obstacle(0, 0, 0);
        }
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
    }
    public void update() {
        player.move(0);
        for (Obstacle obstacle : obstacles) {
            obstacle.update();
        }
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
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 100);
        g.fillRect(0, 200, 800, 100);
        g.fillRect(0, 400, 800, 100);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.move(0);
                System.out.println("UP");
                break;
            case KeyEvent.VK_RIGHT:
                player.move(1);
                System.out.println("RIGHT");
                break;
            case KeyEvent.VK_DOWN:
                player.move(2);
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                player.move(3);
                System.out.println("LEFT");
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
