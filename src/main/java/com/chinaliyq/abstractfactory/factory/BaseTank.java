package com.chinaliyq.abstractfactory.factory;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.observer.TankFireEvent;
import com.chinaliyq.abstractfactory.observer.TankFireObserver;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:12
 * @Version: 1.0
 **/
public abstract class BaseTank extends GameObject {
    public Group group = Group.BAD;
    public Rectangle rectangle = new Rectangle();
    public Random random = new Random();
    public boolean bLeft = false;
    public boolean bUp = false;
    public boolean bRight = false;
    public boolean bDown = false;
    public boolean live = true;
    public boolean moving = false;
    public Direction dir = Direction.UP;
    //持有画板的对象来画画
//    public GameModel gameModel = null;
    public Direction[] directions = Direction.values();
    public int ID = 0;
    public int score = 0;
//    public abstract void paint(Graphics g);
    public abstract void die();

    public abstract int getX();

    public abstract int getY();

    public abstract void fire();

    public abstract void move();

    public abstract void back();

    public abstract void defualtFireStrategy();

    public abstract void fourDirectionFire();

    public Group getGroup() {
        return group;
    }

    public List<TankFireObserver> tankFireObservers;
    public TankFireEvent tankFireEvent;

    public abstract void tankFire();

}
