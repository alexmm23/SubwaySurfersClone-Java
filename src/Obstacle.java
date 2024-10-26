import java.awt.*;

public class Obstacle {
    private int x, y;
    private int lane;
    private boolean visible;
    private Image image;

    public Obstacle(int x, int y, int lane) {
        this.x = x;
        this.y = y;
        this.lane = lane;
        this.visible = false;
        this.image = Toolkit.getDefaultToolkit().getImage("src/assets/obstacle/train.png");
    }

    public void update(int speed) {
        if (visible) {
            x -= speed;
            if (x < 0) {
                x = 800;
            }
        }
    }

    public void draw(Graphics g) {
        if (visible) {
            g.drawImage(image, x, y, null);
        }
    }

    public void setLane(int lane) {
        this.y = lane * 200 + 50;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
