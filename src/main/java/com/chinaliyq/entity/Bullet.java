package com.chinaliyq.entity;

import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.ResourceMgr;
import com.chinaliyq.view.TankFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/8 - 17:30
 * @Version: 1.0
 **/
public class Bullet {
    private static final int SPEED = 10;
    /**
     * 坐标
     */
    private int x, y;
    private Direction dir;
    private TankFrame tankFrame = null;
    private boolean live = true;
    private BufferedImage bufferedImage;
    private int bulletCenterX,bulletCenterY;
    private Group group = Group.BAD;
    private Rectangle rectangle = new Rectangle();


    public void paint(Graphics g) {
        if (!live){
            tankFrame.bullets.remove(this);
        }
        switch (dir){
            case LEFT:
                bufferedImage = ResourceMgr.bulletLeft;
                break;
            case UP:
                bufferedImage = ResourceMgr.bulletUp;
                break;
            case RIGHT:
                bufferedImage = ResourceMgr.bulletRight;
                break;
            case DOWN:
                bufferedImage = ResourceMgr.bulletDown;
                break;
        }
        //x，y坐标
        bulletCenterX = this.x -bufferedImage.getWidth() / 2 ;
        bulletCenterY = this.y -bufferedImage.getHeight() / 2 ;
        g.drawImage(bufferedImage,bulletCenterX,bulletCenterY,null);

        //手动画图
//        Color color = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x,y,WIDTH,HEIGHT);
//        g.setColor(color);
        move();
        updateRectangle();
    }
    private void move() {
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT)live = false;
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    /**
     * 子弹碰撞
     * @param tank
     */
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup())return;
        //Todo
        if (null!=bufferedImage){
//            Rectangle bulletRectangle = new Rectangle(this.x,this.y,bufferedImage.getWidth(),bufferedImage.getHeight());
//            Rectangle tankRectangle = new Rectangle(tank.getX(),tank.getY(),tank.getBufferedImage().getWidth(),tank.getBufferedImage().getHeight());
            if (rectangle.intersects(tank.getRectangle())){
                this.die();
                tank.die();
//            System.out.println(tank.getTankCenterX() +"," + tank.getTankCenterY());
//            tankFrame.explodes.add(new Explode(1,1,tankFrame));
            }
        }
    }

    public void die(){
        this.live = false;
    }
    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                '}';
    }



    public Bullet() {
    }

    public Bullet(int x, int y, Direction dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
//        //加入
//        tankFrame.bullets.add(this);

    }

    private void updateRectangle(){
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();
    }
    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
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

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public int getBulletCenterX() {
        return bulletCenterX;
    }

    public void setBulletCenterX(int bulletCenterX) {
        this.bulletCenterX = bulletCenterX;
    }

    public int getBulletCenterY() {
        return bulletCenterY;
    }

    public void setBulletCenterY(int bulletCenterY) {
        this.bulletCenterY = bulletCenterY;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
