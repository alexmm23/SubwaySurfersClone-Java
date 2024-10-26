import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player {
    private int x, y;
    private String name;
    private boolean isJumping = false;
    private Image[] images;
    private Timer jumpTimer;
    private int jumpStep = 0;
    private final int JUMP_HEIGHT = 200;
    private final int JUMP_STEPS = 10;
    private final int SLEEP_DURATION = 25;


    public Player(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.images = new Image[2];
        images[0] = Toolkit.getDefaultToolkit().getImage("src/assets/player/sprite_player0.png");
        images[1] = Toolkit.getDefaultToolkit().getImage("src/assets/player/sprite_player1.png");
    }

    public void move(int direction) {
        final int SPEED = 50;
        switch (direction) {
            case 0:
                jump();
                break;
            case 1:
                x += SPEED;
                break;
            case 2:
                y += SPEED;
                break;
            case 3:
                x -= SPEED;
                break;
        }
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            jumpStep = 0;
            jumpTimer = new Timer(SLEEP_DURATION, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (jumpStep < JUMP_STEPS) {
                        y -= JUMP_HEIGHT / JUMP_STEPS;
                    } else if (jumpStep < 2 * JUMP_STEPS) {
                        y += JUMP_HEIGHT / JUMP_STEPS;
                    } else {
                        jumpTimer.stop();
                        isJumping = false;
                    }
                    jumpStep++;
                }
            });
            jumpTimer.start();
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int imageIndex = (int) (System.currentTimeMillis() % 2000 / 1000); // Alterna entre las imágenes
        int imageWidth = 60;
        int imageHeight = 60;
        if (isJumping) {
            g2d.translate(x + imageWidth / 2, y + imageHeight / 2);
            double rotationAngle = Math.toRadians(jumpStep * (360 / (JUMP_STEPS * 2)));
            g2d.rotate(rotationAngle);
            g2d.drawImage(images[imageIndex], -imageWidth / 2, -imageHeight / 2, imageWidth, imageHeight, null);
            g2d.rotate(-rotationAngle);
            g2d.translate(-(x + imageWidth / 2), -(y + imageHeight / 2));
        } else {
            g2d.drawImage(images[imageIndex], x, y, imageWidth, imageHeight, null);
        }
    }


    public void setLane(int lane) {
        this.y = lane * 200 + 50;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
}
