package com.chinaliyq.abstractfactory.bean;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.view.GameFrame;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.PropertyMgr;
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
//@SuppressWarnings("all")
public class RectBullet extends BaseBullet {
    private static final int SPEED = Integer.parseInt((String)PropertyMgr.getValue("bulletSpeed"));
//    private int x, y;
    private Direction dir;
    private GameModel gameModel = null;
    private boolean live = true;
    private BufferedImage bufferedImage;
    private int bulletCenterX,bulletCenterY;

    @Override
    public void paint(Graphics g) {
        if (!live){
            gameModel.getGameObjects().remove(this);
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
        bulletCenterX = this.x - bufferedImage.getWidth() / 2 ;
        bulletCenterY = this.y - bufferedImage.getHeight() / 2 ;
        g.drawImage(bufferedImage,bulletCenterX,bulletCenterY,null);
        move();
        updateRectangle();
    }

    @Override
    public void collideWith(BaseTank tank) {
        if (this.group == tank.getGroup())return;
        if (rectangle.intersects(tank.rectangle) && this.live == true && tank.live == true){
            this.die();
            tank.die();
            countPlayerScore();
        }
    }

    private void countPlayerScore() {
        if (this.ID == 1){
            gameModel.player_one.score += 1;
        }
        else if (this.ID == 2){
            gameModel.player_one.score += 1;
        }
//        System.out.println("玩家1：" + gameFrame.getPlayer_one().score);
//        System.out.println("玩家2：" + gameFrame.getPlayer_two().score);
    }

    @Override
    public void die() {
        this.live = false;
    }

    public int getX() {
        return 0;
    }

    public int getY() {
        return 0;
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


    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                '}';
    }

    public RectBullet(int x, int y, Direction dir, Group group, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
        this.group = group;
    }
    public RectBullet(int x, int y, Direction dir, Group group, GameModel gameModel,int id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
        this.group = group;
        this.ID = id;
    }

    private void updateRectangle(){
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
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

    public void setX(int x) {
        this.x = x;
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
}
