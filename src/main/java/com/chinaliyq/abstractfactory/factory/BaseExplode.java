package com.chinaliyq.abstractfactory.factory;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.util.Group;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:13
 * @Version: 1.0
 **/
public abstract class BaseExplode extends GameObject {
    //持有画板的对象来画画
    public GameModel gameModel = null;
    public Group group = Group.BAD;

//    public abstract void paint(Graphics g);

    public abstract void die();

}
