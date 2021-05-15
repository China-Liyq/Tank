package com.chinaliyq.abstractfactory.factory;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.util.Group;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/15 - 15:37
 * @Version: 1.0
 **/
public abstract class BaseWall extends GameObject{
    public int w, h;

    public Rectangle rectangle;

    public boolean live = true;

    public abstract void die();
}
