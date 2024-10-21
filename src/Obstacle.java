import java.awt.*;

public class Obstacle {
    private int x,y;
    private int lane;
    public Obstacle(int x, int y, int lane) {
        this.x = x;
        this.y = y;
        this.lane = lane;
    }
    public void update(int speed) {
        x -= speed;
        if (x < 0) {
            x = 800;
        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 50);
    }
    private void setLane(int lane) {
        this.y = lane * 200;
    }
}
