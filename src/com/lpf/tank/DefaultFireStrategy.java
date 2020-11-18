package com.lpf.tank;

public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bx = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH;
        int by = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGHT;
        new Bullet(bx, by, tank.dir, tank.group, tank.tankFrame);

        if (tank.group == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
