package com.chinaliyq.abstractfactory.bean;

import com.chinaliyq.abstractfactory.factory.BaseWall;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/15 - 14:05
 * @Version: 1.0
 **/
public class DefualtWall extends BaseWall {
    private transient BufferedImage bufferedImage;
    private int fillWidth = 20,fillHeight = 20;
    @Override
    public void paint(Graphics g) {
        if (!live){
            gameModel.getGameObjects().remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x,y,width,height);
        g.setColor(color);
    }

    @Override
    public int getWidth() {
        return bufferedImage==null? fillWidth:bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        return bufferedImage==null? fillHeight : bufferedImage.getHeight();
    }

    public DefualtWall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.rectangle = new Rectangle(x,y,w,h);
    }

    @Override
    public void die() {
        this.live = false;
    }
}
