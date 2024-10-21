import java.awt.*;

public class Player {
    private int x,y;
    private String name;
    public Player(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public void move(int direction) {
        switch (direction) {
            case 0:
                y--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y++;
                break;
            case 3:
                x--;
                break;
        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 50);
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
}
