package com.chinaliyq.abstractfactory.decorator;

import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.abstractfactory.factory.GameObject;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: bullet
 * @Date: 2021/5/15 - 21:57
 * @Version: 1.0
 **/
public class TailDecorator extends GameObjectDecorator {

    public TailDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        this.x = gameObject.x;
        this.y = gameObject.y;
        gameObject.paint(g);

        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawLine(x-getWidth() / 2,y,x+this.getWidth() * 3 / 2,y);
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
