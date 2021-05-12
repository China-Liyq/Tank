package com.chinaliyq.abstractfactory;

import com.chinaliyq.entity.Tank;
import com.chinaliyq.util.Group;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:13
 * @Version: 1.0
 **/
public abstract class BaseExplode {
    public Group group = Group.BAD;

    public abstract void paint(Graphics g);

    public abstract void die();

}
