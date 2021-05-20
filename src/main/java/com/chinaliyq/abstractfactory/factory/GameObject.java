package com.chinaliyq.abstractfactory.factory;

import com.chinaliyq.abstractfactory.controller.GameModel;

import java.awt.*;
import java.io.Serializable;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/13 - 21:35
 * @Version: 1.0
 **/
public abstract class GameObject implements Serializable {
    public int x, y;
    //持有画板的对象来画画
    public GameModel gameModel = GameModel.getInstance();

    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}
