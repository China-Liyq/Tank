package com.chinaliyq.abstractfactory;

import com.chinaliyq.entity.Tank;
import com.chinaliyq.util.Group;

import java.awt.*;
import java.util.Random;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:12
 * @Version: 1.0
 **/
public abstract class BaseBullet {
    public Rectangle rectangle = new Rectangle();
    public Group group = Group.BAD;

    public abstract void paint(Graphics g);

    public abstract void collideWith(Tank tank);

    public abstract void die();

    public abstract int getX();

    public abstract int getY();

}
