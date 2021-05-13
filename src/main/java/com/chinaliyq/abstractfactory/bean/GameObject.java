package com.chinaliyq.abstractfactory.bean;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/13 - 21:35
 * @Version: 1.0
 **/
public abstract class GameObject {
    public int x, y;

    public abstract void paint(Graphics g);
}
