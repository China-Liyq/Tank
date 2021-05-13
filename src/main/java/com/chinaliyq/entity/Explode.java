package com.chinaliyq.entity;

import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.ResourceMgr;
import com.chinaliyq.util.Audio;
import com.chinaliyq.view.TankFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/9 - 16:59
 * @Version: 1.0
 **/
public class Explode {

    private static final int SPEED = 10;
    /**
     * 坐标
     */
    private int x, y;
    private Direction dir;

    private boolean live = true;
    private TankFrame tankFrame = null;

    private BufferedImage bufferedImage;

    private int explodeCenterX,explodeCenterY;
    private Group group = Group.BAD;

    private int step = 0;
    public void paint(Graphics g) {
        bufferedImage = ResourceMgr.specialExplodes[step++];
        explodeCenterX = this.x - bufferedImage.getWidth() / 2;
        explodeCenterY = this.y - bufferedImage.getHeight() / 2;
        g.drawImage(bufferedImage,explodeCenterX,explodeCenterY,null);
        if (step >= ResourceMgr.specialExplodes.length){
           tankFrame.getExplodes().remove(this);
        }
    }





    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        Audio audio = new Audio("audio/explode.wav");
        audio.start();
    }
    public void die(){
        this.live = false;
    }

    public Explode(int x, int y, Direction dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    public static int getSPEED() {
        return SPEED;
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

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
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
