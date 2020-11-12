package com.lpf.tank;

import java.awt.*;
import java.util.Random;

public class Tank {

    private int x;
    private int y;
    private Dir dir = Dir.DOWN;
    private TankFrame tankFrame;
    private static final int SPEED = 10;

    private Random random = new Random();
    private Group group = Group.BAD;

    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private boolean moving = true;
    private boolean living = true;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        if (!living) tankFrame.tanks.remove(this);
        switch (dir) {
            case LEFT:
                g.drawImage(group == Group.BAD ? ResourceMgr.badTankL : ResourceMgr.goodTankL, x, y, null);
                break;
            case UP:
                g.drawImage(group == Group.BAD ? ResourceMgr.badTankU : ResourceMgr.goodTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(group == Group.BAD ? ResourceMgr.badTankR : ResourceMgr.goodTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(group == Group.BAD ? ResourceMgr.badTankD : ResourceMgr.goodTankD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
        if (!moving) return;
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

        if (this.group == Group.BAD && random.nextInt(100) > 95) this.fire();

        if (group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }

        boundsCheck();
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {
        if (this.group == Group.GOOD) return;
        this.dir = Dir.values()[random.nextInt(4)];
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void fire() {
        int bx = x + Tank.WIDTH / 2 - Bullet.WIDTH;
        int by = y + Tank.HEIGHT / 2 - Bullet.HEIGHT;
        tankFrame.list.add(new Bullet(bx, by, dir, this.group, tankFrame));
    }

    public void die() {
        living = false;
    }
}
