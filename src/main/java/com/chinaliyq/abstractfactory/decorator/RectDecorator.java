package com.chinaliyq.abstractfactory.decorator;

import com.chinaliyq.abstractfactory.factory.GameObject;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/15 - 21:57
 * @Version: 1.0
 **/
public class RectDecorator extends GameObjectDecorator {

    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        gameObject.paint(g);
        this.x = gameObject.x-this.getWidth()/2;
        this.y = gameObject.y-this.getHeight()/2;
        Color color = g.getColor();
        g.setColor(Color.red);
        g.drawRect(x,y,this.getWidth(),this.getHeight());
        g.setColor(color);

    }

    @Override
    public int getWidth() {
        return gameObject.getWidth();
    }

    @Override
    public int getHeight() {
        return gameObject.getHeight();
    }
}
