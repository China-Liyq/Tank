package com.chinaliyq.interfaces.imp;

import com.chinaliyq.entity.Bullet;
import com.chinaliyq.entity.Tank;
import com.chinaliyq.interfaces.FireStrategy;
import com.chinaliyq.util.Audio;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 15:05
 * @Version: 1.0
 **/
public class FourDirectionFireStrategy implements FireStrategy {
    private int tankCenterX,tankCenterY;
    @Override
    public void fire(Tank t) {
        //获取枪口中心的位置
        switch (t.getDir()){
            case LEFT:
                tankCenterX = t.getX() + 0;
                tankCenterY = t.getY() + t.getBufferedImage().getHeight() / 2;
                break;
            case UP:
                tankCenterX = t.getX() + t.getBufferedImage().getWidth() / 2;
                tankCenterY = t.getY() + 0;
                break;
            case RIGHT:
                tankCenterX = t.getX() + t.getBufferedImage().getWidth();
                tankCenterY = t.getY() + t.getBufferedImage().getHeight() / 2;
                break;
            case DOWN:
                tankCenterX = t.getX() + t.getBufferedImage().getWidth() / 2;
                tankCenterY = t.getY() + t.getBufferedImage().getHeight();
                break;
        }
        Bullet bullet1 = new Bullet(tankCenterX, tankCenterY, Direction.UP,t.getGroup(), t.getTankFrame());
        Bullet bullet2 = new Bullet(tankCenterX, tankCenterY, Direction.DOWN,t.getGroup(), t.getTankFrame());
        Bullet bullet3 = new Bullet(tankCenterX, tankCenterY, Direction.RIGHT,t.getGroup(), t.getTankFrame());
        Bullet bullet4 = new Bullet(tankCenterX, tankCenterY, Direction.LEFT,t.getGroup(), t.getTankFrame());
        t.getTankFrame().bullets.add(bullet1);
        t.getTankFrame().bullets.add(bullet2);
        t.getTankFrame().bullets.add(bullet3);
        t.getTankFrame().bullets.add(bullet4);
        //队友不能打队友
        if (t.getGroup() == Group.GOOD)
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();

    }
}
