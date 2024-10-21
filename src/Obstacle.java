import java.awt.*;

public class Obstacle {
    private int x,y;
    private int lane;
    public Obstacle(int x, int y, int lane) {
        this.x = x;
        this.y = y;
        this.lane = lane;
    }
    public void update() {

    }
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 50);
    }
}
