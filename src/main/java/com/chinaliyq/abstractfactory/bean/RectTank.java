package com.chinaliyq.abstractfactory.bean;

import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.interfaces.FireStrategy;
import com.chinaliyq.abstractfactory.interfaces.IExplode;
import com.chinaliyq.abstractfactory.interfaces.imp.DefaultIExplode;
import com.chinaliyq.abstractfactory.observer.TankFireEvent;
import com.chinaliyq.abstractfactory.observer.TankFireHandler;
import com.chinaliyq.abstractfactory.observer.TankFireObserver;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.PropertyMgr;
import com.chinaliyq.util.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/8 - 17:01
 * @Version: 1.0
 **/
public class RectTank extends BaseTank {
    private final static int SPEED = Integer.parseInt((String)PropertyMgr.getValue("tankSpeed"));
    private final static String DEFAULTFIRE = (String) PropertyMgr.getValue("defaultFire");
    private final static String FOURDIRECTIONFIRE = (String) PropertyMgr.getValue("fourDirectionFire");
    private final static String DEFAULTIEXPLODE = (String) PropertyMgr.getValue("defaultIExplode");
    private final static String BOMIEXPLODE = (String) PropertyMgr.getValue("bomIExplode");

    private static Direction[] directions = Direction.values();
    private int index = 0;
    private int count = 0;
    public int boundsWith = 8;
    private transient BufferedImage bufferedImage;

    private FireStrategy fireStrategy;
    private IExplode explode;

    private transient BufferedImage[] TankLefts;
    private transient BufferedImage[] TankUps;
    private transient BufferedImage[] TankRights;
    private transient BufferedImage[] TankDowns;
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
        this.badTankfire();
        this.changShape();
        this.updateRectangle();
    }

    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    @Override
    public void die() {
        this.live = false;
        explode.explode(this);
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
    private int oldX = x, oldY = y;
    @Override
    public void move() {
        oldX = this.x;
        oldY = this.y;
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
        if (this.boundsCheck() && group == Group.BAD){
            randomDirection();
        };
        rectangle.x = this.x;
        rectangle.y = this.y;

    }
    //同类坦克移动当前判断是否碰撞
    @Override
    public void back(){
        this.x = oldX;
        this.y = oldY;
    }
    private boolean boundsCheck() {
        boundsWith = bufferedImage.getWidth() / 2;
        if (this.x < boundsWith) {
            x = boundsWith;
            return true;
        }
        else if ((this.x + bufferedImage.getWidth() + boundsWith) >= gameModel.GAME_WIDTH) {
            this.x = gameModel.GAME_WIDTH - bufferedImage.getWidth() - boundsWith;
            return true;
        }
        if (this.y < boundsWith +10){
            y = boundsWith + 10;
            return true;
        }
        else if ((this.y + bufferedImage.getHeight()+boundsWith)>= gameModel.GAME_HEIGHT) {
            this.y = gameModel.GAME_HEIGHT - bufferedImage.getHeight() - boundsWith;
            return true;
        }
        return false;
    }

    private void randomDirection() {
        if (random.nextInt(100) > 80)
            this.dir = directions[random.nextInt(directions.length)];
    }

    private void updateRectangle(){
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();
    }
    //电脑开启和方向切换
    public void badTankfire(){
        //电脑自动开枪
        if (this.group == Group.BAD && random.nextInt(100) > 98) this.fire();
        //电脑移动
        if (this.group == Group.BAD && random.nextInt(100) > 90) randomDirection();
    }
    @Override
    public void fire() {
        if (live == true) fireStrategy.factoryfire(this);
    }
    //获取开枪等 的模式-默认
    private void LoadStrategy(){
        this.defualtFireStrategy();

    }
    @Override
    public void defualtFireStrategy(){
        try {
            fireStrategy = (FireStrategy) Class.forName(DEFAULTFIRE).getDeclaredConstructor().newInstance();
            explode = (IExplode) Class.forName(DEFAULTIEXPLODE).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        explode = new DefaultIExplode();
    }
    @Override
    public void fourDirectionFire(){
        try {
            fireStrategy = (FireStrategy) Class.forName(FOURDIRECTIONFIRE).getDeclaredConstructor().newInstance();
            explode = (IExplode) Class.forName(BOMIEXPLODE).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tankFire() {
        for (int i = 0; i < tankFireObservers.size(); i++) {
            tankFireObservers.get(i).actionOnFire(tankFireEvent);
        }
    }

    public RectTank(int x, int y, Direction dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.LoadStrategy();
    }
    public RectTank(int x, int y, Direction dir, Group group,int id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.ID = id;
        this.LoadStrategy();
        tankFireEvent = new TankFireEvent(this);
        tankFireObservers =new ArrayList<>();
        tankFireObservers.add(new TankFireHandler());
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
