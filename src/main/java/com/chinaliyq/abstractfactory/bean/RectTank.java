package com.chinaliyq.abstractfactory.bean;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.view.GameFrame;
import com.chinaliyq.abstractfactory.interfaces.FireStrategy;
import com.chinaliyq.abstractfactory.interfaces.IExplode;
import com.chinaliyq.abstractfactory.interfaces.imp.DefaultFireStrategy;
import com.chinaliyq.abstractfactory.interfaces.imp.DefaultIExplode;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.PropertyMgr;
import com.chinaliyq.util.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/8 - 17:01
 * @Version: 1.0
 **/
public class RectTank extends BaseTank {
    private final static int SPEED = Integer.parseInt((String)PropertyMgr.getValue("tankSpeed"));
    private static Direction[] directions = Direction.values();
    private int index = 0;
    private int count = 0;
    private int boundsWith = 8;
    private BufferedImage bufferedImage;

    private FireStrategy fireStrategy;
    private IExplode explode;

    private BufferedImage[] TankLefts;
    private BufferedImage[] TankUps;
    private BufferedImage[] TankRights;
    private BufferedImage[] TankDowns;
    private int explodeX,explodeY;
    private int tankCenterX,tankCenterY;

    @Override
    public void paint(Graphics g) {
        if (!this.live){
            gameModel.getGameObjects().remove(this);
            return;
        };
        TankLefts = this.group == Group.GOOD ? ResourceMgr.goodTankLefts : ResourceMgr.badTankLefts;
        TankUps = this.group == Group.GOOD ? ResourceMgr.goodTankUps : ResourceMgr.badTankUps;
        TankRights = this.group == Group.GOOD ? ResourceMgr.goodTankRights : ResourceMgr.badTankRights;
        TankDowns = this.group == Group.GOOD ? ResourceMgr.goodTankDowns : ResourceMgr.badTankDowns;
        switch (dir){
            case LEFT:
                bufferedImage = TankLefts[index];
                break;
            case UP:
                bufferedImage = TankUps[index];
                break;
            case RIGHT:
                bufferedImage = TankRights[index];
                break;
            case DOWN:
                bufferedImage = TankDowns[index];
                break;
            default:
                break;
        }
        g.drawImage(bufferedImage,x,y,null);
        this.move();
        this.changShape();
        this.updateRectangle();
    }

    @Override
    public void die() {
        this.live = false;
        explode.explode(this);
//        explodeX = this.x + bufferedImage.getWidth() / 2;
//        explodeY = this.y + bufferedImage.getHeight() / 2;
//        gameModel.getExplodes().add(new RectExplode(explodeX,explodeY, gameModel));
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    /**
     * 动态图片切换
     */
    Long a,b;
    private void changShape(){
        if (count==0){
            a =  System.currentTimeMillis();
        }
        if (count++ > 160){
            index = index == 0 ? 1 : 0;
            count = 0;
        }
        if (count==160){
            b = System.currentTimeMillis();
//            System.out.println("使用时间为：--" +(b-a));
        }
    }

    private void move() {
        if (!moving) return;
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
        //电脑自动开枪
        if (random.nextInt(100) > 97 && this.group == Group.BAD) this.fire();
        //电脑移动
        if (random.nextInt(100) > 98 && this.group == Group.BAD) randomDirection(80);
        this.boundsCheck();
        rectangle.x = this.x;
        rectangle.y = this.y;
    }
    private void boundsCheck() {
        boundsWith = bufferedImage.getWidth() / 2;
        if (this.x < boundsWith) {
            x = boundsWith;
            this.randomDirection(-1);
        }
        else if ((this.x + bufferedImage.getWidth() + boundsWith) >= gameModel.GAME_WIDTH) {
            this.x = gameModel.GAME_WIDTH - bufferedImage.getWidth() - boundsWith;
            this.randomDirection(-1);
        }
        if (this.y < boundsWith +10){
            y = boundsWith + 10;
            this.randomDirection(-1);
        }
        else if ((this.y + bufferedImage.getHeight()+boundsWith)>= gameModel.GAME_HEIGHT) {
            this.y = gameModel.GAME_HEIGHT - bufferedImage.getHeight() - boundsWith;
            this.randomDirection(-1);
        }
    }
    private void randomDirection(int num) {
        if (random.nextInt(100) > num)
        this.dir = directions[random.nextInt(directions.length)];
    }

    private void updateRectangle(){
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();
    }

    @Override
    public void fire() {
        if (live==true) fireStrategy.factoryfire(this);
    }
    //获取开枪等 的模式
    private void LoadFireStrategy(){
        if (this.group == Group.GOOD){
            String goodFS = (String) PropertyMgr.getValue("goodFS");
            try {
                fireStrategy = (FireStrategy) Class.forName(goodFS).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            fireStrategy = new DefaultFireStrategy();
        }
        explode = new DefaultIExplode();
    }
    public RectTank(int x, int y, Direction dir, Group group, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
        this.group = group;
        this.LoadFireStrategy();
    }
    public RectTank(int x, int y, Direction dir, Group group, GameModel gameModel,int id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
        this.group = group;
        this.ID = id;
        this.LoadFireStrategy();
    }
    public RectTank(int x, int y, Direction dir, Group group, GameModel gameModel, Boolean moving) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
        this.group = group;
        this.moving =moving;
        this.LoadFireStrategy();
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

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getBoundsWith() {
        return boundsWith;
    }

    public void setBoundsWith(int boundsWith) {
        this.boundsWith = boundsWith;
    }

    public int getExplodeX() {
        return explodeX;
    }

    public void setExplodeX(int explodeX) {
        this.explodeX = explodeX;
    }

    public int getExplodeY() {
        return explodeY;
    }

    public void setExplodeY(int explodeY) {
        this.explodeY = explodeY;
    }

    public int getTankCenterX() {
        return tankCenterX;
    }

    public void setTankCenterX(int tankCenterX) {
        this.tankCenterX = tankCenterX;
    }

    public int getTankCenterY() {
        return tankCenterY;
    }

    public void setTankCenterY(int tankCenterY) {
        this.tankCenterY = tankCenterY;
    }

    public Long getA() {
        return a;
    }

    public void setA(Long a) {
        this.a = a;
    }

    public Long getB() {
        return b;
    }

    public void setB(Long b) {
        this.b = b;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public FireStrategy getFireStrategy() {
        return fireStrategy;
    }

    public void setFireStrategy(FireStrategy fireStrategy) {
        this.fireStrategy = fireStrategy;
    }

    public BufferedImage[] getTankLefts() {
        return TankLefts;
    }

    public void setTankLefts(BufferedImage[] tankLefts) {
        TankLefts = tankLefts;
    }

    public BufferedImage[] getTankUps() {
        return TankUps;
    }

    public void setTankUps(BufferedImage[] tankUps) {
        TankUps = tankUps;
    }

    public BufferedImage[] getTankRights() {
        return TankRights;
    }

    public void setTankRights(BufferedImage[] tankRights) {
        TankRights = tankRights;
    }

    public BufferedImage[] getTankDowns() {
        return TankDowns;
    }

    public void setTankDowns(BufferedImage[] tankDowns) {
        TankDowns = tankDowns;
    }
}