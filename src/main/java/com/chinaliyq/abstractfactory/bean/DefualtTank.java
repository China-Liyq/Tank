package com.chinaliyq.abstractfactory.bean;

import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.util.PropertyMgr;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/14 - 17:02
 * @Version: 1.0
 **/
public class DefualtTank extends BaseTank {
    private final static int SPEED = Integer.parseInt((String) PropertyMgr.getValue("tankSpeed"));

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void fire() {

    }

    @Override
    public void die() {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}
