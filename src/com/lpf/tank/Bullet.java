package com.lpf.tank;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    Rectangle rectangle = new Rectangle();

    private int x;
    private int y;
    private Dir dir;
    private boolean live = true;

    private Group group = Group.BAD;

    private TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

        tankFrame.bullets.add(this);
    }

    public void paint(Graphics g) {
        if (!live) {
           tankFrame.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        //update rect
        rectangle.x = x;
        rectangle.y = y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }

    public void collideWith(Tank tank) {
        if (group == tank.getGroup()) return;

        Rectangle rectangle1 = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rectangle1.intersects(rectangle2)) {
            tank.die();
            this.die();
            int ex = tank.getX() + Tank.WIDTH / 2 - Explodes.WIDTH / 2;
            int ey = tank.getY() + Tank.HEIGHT / 2 - Explodes.HEIGHT / 2;
            tankFrame.explodes.add(new Explodes(ex, ey, tankFrame));
        }
    }

    private void die() {
        live = false;
    }
}
