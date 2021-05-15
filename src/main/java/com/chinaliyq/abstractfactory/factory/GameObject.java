package com.chinaliyq.abstractfactory.factory;

import com.chinaliyq.abstractfactory.controller.GameModel;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/13 - 21:35
 * @Version: 1.0
 **/
public abstract class GameObject {
    //持有画板的对象来画画
    public GameModel gameModel = GameModel.getInstance();
    public int x, y;

    public abstract void paint(Graphics g);
}
