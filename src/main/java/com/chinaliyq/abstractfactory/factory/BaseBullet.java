package com.chinaliyq.abstractfactory.factory;

import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.util.Group;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:12
 * @Version: 1.0
 **/
public abstract class BaseBullet {

    public Rectangle rectangle = new Rectangle();

    public Group group = Group.BAD;

    public int ID = 0;

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);

    public abstract void die();
}
