package com.chinaliyq.abstractfactory.factory;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.util.Group;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:12
 * @Version: 1.0
 **/
public abstract class BaseBullet extends GameObject {
    public Rectangle rectangle = new Rectangle();

    public boolean live = true;

    public Group group = Group.BAD;

    public int ID = 0;

    public abstract void collideWith(BaseTank tank);

    public abstract void die();
}
