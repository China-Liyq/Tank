package com.chinaliyq.abstractfactory.interfaces.imp;

import com.chinaliyq.abstractfactory.bean.RectBullet;
import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.abstractfactory.decorator.RectDecorator;
import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.entity.Bullet;
import com.chinaliyq.entity.Tank;
import com.chinaliyq.abstractfactory.interfaces.FireStrategy;
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

    @Override
    public void factoryfire(RectTank t) {
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

        Direction[] values = Direction.values();
        for (int i = 0; i < values.length; i++) {
            gameModel.getGameObjects().add(new RectBullet(tankCenterX,tankCenterY,values[i],t.group,t.ID));
        }
//        BaseBullet bullet1 = new RectBullet(tankCenterX, tankCenterY, Direction.UP, t.getGroup(), t.ID);
//        BaseBullet bullet2 = new RectBullet(tankCenterX, tankCenterY, Direction.DOWN, t.getGroup(), t.ID);
//        BaseBullet bullet3 = new RectBullet(tankCenterX, tankCenterY, Direction.RIGHT, t.getGroup(), t.ID);
//        BaseBullet bullet4 = new RectBullet(tankCenterX, tankCenterY, Direction.LEFT, t.getGroup(), t.ID);
//        RectDecorator rectDecorator1 = new RectDecorator(bullet1);
//        RectDecorator rectDecorator2 = new RectDecorator(bullet2);
//        RectDecorator rectDecorator3 = new RectDecorator(bullet3);
//        RectDecorator rectDecorator4 = new RectDecorator(bullet4);
//        gameModel.getGameObjects().add(rectDecorator1);
//        gameModel.getGameObjects().add(rectDecorator2);
//        gameModel.getGameObjects().add(rectDecorator3);
//        gameModel.getGameObjects().add(rectDecorator4);
//        gameModel.getGameObjects().add(bullet1);
//        gameModel.getGameObjects().add(bullet2);
//        gameModel.getGameObjects().add(bullet3);
//        gameModel.getGameObjects().add(bullet4);
        //队友不能打队友
        if (t.getGroup() == Group.GOOD)
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();

    }
}
