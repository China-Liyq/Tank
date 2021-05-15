package com.chinaliyq.abstractfactory.bean;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.factory.BaseWall;
import com.chinaliyq.abstractfactory.factory.GameObject;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/15 - 14:05
 * @Version: 1.0
 **/
public class DefualtWall extends BaseWall {
    @Override
    public void paint(Graphics g) {
        if (!live){
            gameModel.getGameObjects().remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x,y,w,h);
        g.setColor(color);
    }

    public DefualtWall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rectangle = new Rectangle(x,y,w,h);
    }

    @Override
    public void die() {
        this.live = false;
    }
}
