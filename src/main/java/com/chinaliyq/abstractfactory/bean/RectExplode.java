package com.chinaliyq.abstractfactory.bean;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.factory.BaseExplode;
import com.chinaliyq.abstractfactory.view.GameFrame;
import com.chinaliyq.util.Audio;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/9 - 16:59
 * @Version: 1.0
 **/
public class RectExplode extends BaseExplode {
    private boolean live = true;
    private BufferedImage bufferedImage;
    private int explodeCenterX,explodeCenterY;
    private Group group = Group.BAD;

    private int step = 0;
    @Override
    public void paint(Graphics g) {
        bufferedImage = ResourceMgr.specialExplodes[step++];
        explodeCenterX = this.x - bufferedImage.getWidth() / 2;
        explodeCenterY = this.y - bufferedImage.getHeight() / 2;
        g.drawImage(bufferedImage,explodeCenterX,explodeCenterY,null);
        if (step >= ResourceMgr.specialExplodes.length){
           gameModel.getGameObjects().remove(this);
        }
    }

    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    public RectExplode(int x, int y) {
        this.x = x;
        this.y = y;
        Audio audio = new Audio("audio/explode.wav");
        audio.start();
    }
    @Override
    public void die(){
        this.live = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public int getExplodeCenterX() {
        return explodeCenterX;
    }

    public void setExplodeCenterX(int explodeCenterX) {
        this.explodeCenterX = explodeCenterX;
    }

    public int getExplodeCenterY() {
        return explodeCenterY;
    }

    public void setExplodeCenterY(int explodeCenterY) {
        this.explodeCenterY = explodeCenterY;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
