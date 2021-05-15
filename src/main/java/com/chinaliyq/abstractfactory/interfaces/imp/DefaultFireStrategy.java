package com.chinaliyq.abstractfactory.interfaces.imp;

import com.chinaliyq.abstractfactory.bean.RectBullet;
import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.entity.Bullet;
import com.chinaliyq.entity.Tank;
import com.chinaliyq.abstractfactory.interfaces.FireStrategy;
import com.chinaliyq.util.Audio;
import com.chinaliyq.util.Group;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 13:43
 * @Version: 1.0
 **/
public class DefaultFireStrategy implements FireStrategy {
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
        Bullet bullet = new Bullet(tankCenterX, tankCenterY, t.getDir(),t.getGroup(), t.getTankFrame());
        t.getTankFrame().bullets.add(bullet);
        //队友不能打队友
        if (t.getGroup() == Group.GOOD)
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();

    }

    @Override
    public void factoryfire(RectTank tank) {
        //获取枪口中心的位置
        switch (tank.getDir()){
            case LEFT:
                tankCenterX = tank.getX() + 0;
                tankCenterY = tank.getY() + tank.getBufferedImage().getHeight() / 2;
                break;
            case UP:
                tankCenterX = tank.getX() + tank.getBufferedImage().getWidth() / 2;
                tankCenterY = tank.getY() + 0;
                break;
            case RIGHT:
                tankCenterX = tank.getX() + tank.getBufferedImage().getWidth();
                tankCenterY = tank.getY() + tank.getBufferedImage().getHeight() / 2;
                break;
            case DOWN:
                tankCenterX = tank.getX() + tank.getBufferedImage().getWidth() / 2;
                tankCenterY = tank.getY() + tank.getBufferedImage().getHeight();
                break;
        }
        RectBullet bullet = new RectBullet(tankCenterX, tankCenterY, tank.getDir(),tank.getGroup(),tank.ID);
        gameModel.getGameObjects().add(bullet);
        //队友和自己不能打队友
        if (tank.getGroup() == Group.GOOD)
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

}
