package com.chinaliyq.abstractfactory;

import com.chinaliyq.util.Group;

import java.awt.*;
import java.util.Random;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:12
 * @Version: 1.0
 **/
public abstract class BaseTank {
    public Group group = Group.BAD;
    public Rectangle rectangle = new Rectangle();
    public Random random = new Random();
    public abstract void paint(Graphics g);

    public abstract void die();

    public abstract int getX();

    public abstract int getY();

    public Group getGroup() {
        return group;
    }



}
