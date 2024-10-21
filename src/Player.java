import java.awt.*;

public class Player {
    private int x, y;
    private String name;
    private boolean isJumping = false;
    private Image[] images;


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
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    final int JUMP_HEIGHT = 200;
                    final int JUMP_STEPS = 10;
                    final int SLEEP_DURATION = 25;

                    for (int i = 0; i < JUMP_STEPS; i++) {
                        y -= JUMP_HEIGHT / JUMP_STEPS;
                        try {
                            Thread.sleep(SLEEP_DURATION);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < JUMP_STEPS; i++) {
                        y += JUMP_HEIGHT / JUMP_STEPS;
                        try {
                            Thread.sleep(SLEEP_DURATION);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    isJumping = false;
                }
            });
            thread.start();
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawImage(images[(int) (System.currentTimeMillis() % 2000 / 1000)], x, y, 60, 60, null);
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
