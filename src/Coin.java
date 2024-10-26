import java.awt.*;

public class Coin {
    private int x, y;
    private boolean visible;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        this.visible = false;
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
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, 25, 25);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLane(int lane) {
        this.y = lane * 200 + 50;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
