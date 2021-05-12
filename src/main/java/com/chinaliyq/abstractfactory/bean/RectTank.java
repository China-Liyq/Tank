package com.chinaliyq.abstractfactory.bean;

import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.view.GameFrame;
import com.chinaliyq.entity.Explode;
import com.chinaliyq.interfaces.FireStrategy;
import com.chinaliyq.interfaces.imp.DefaultFireStrategy;
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
 * @Date: 2021/5/8 - 17:01
 * @Version: 1.0
 **/
public class RectTank extends BaseTank {
    private int x, y;
    private Direction dir = Direction.UP;
    private final static int SPEED = Integer.parseInt((String)PropertyMgr.getValue("tankSpeed"));
    private boolean moving = false;
    //持有画板来画画
    private GameFrame gameFrame = null;
    private boolean live = true;
    private int index = 0;
    private int count = 0;
    private int boundsWith = 8;
    private BufferedImage bufferedImage;
    private FireStrategy fireStrategy;
    private BufferedImage[] TankLefts;
    private BufferedImage[] TankUps;
    private BufferedImage[] TankRights;
    private BufferedImage[] TankDowns;
    private int explodeX,explodeY;
    private int tankCenterX,tankCenterY;

    @Override
    public void paint(Graphics g) {
        TankLefts = this.group == Group.GOOD ? ResourceMgr.goodTankLefts : ResourceMgr.badTankLefts;
        TankUps = this.group == Group.GOOD ? ResourceMgr.goodTankUps : ResourceMgr.badTankUps;
        TankRights = this.group == Group.GOOD ? ResourceMgr.goodTankRights : ResourceMgr.badTankRights;
        TankDowns = this.group == Group.GOOD ? ResourceMgr.goodTankDowns : ResourceMgr.badTankDowns;
        if (!this.isLive()){
            gameFrame.getTanks().remove(this);
        };
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
        explodeX = this.x + bufferedImage.getWidth() / 2;
        explodeY = this.y + bufferedImage.getHeight() / 2;
        gameFrame.getExplodes().add(new RectExplode(explodeX,explodeY, gameFrame));
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
        if (!moving)return;
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
        if (random.nextInt(100) > 98 && this.group == Group.BAD) randomDirection();
        this.boundsCheck();
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void boundsCheck() {
        boundsWith = bufferedImage.getWidth() / 2;
        if (this.x < boundsWith) x = boundsWith;
        else if ((this.x + bufferedImage.getWidth()+boundsWith)>= gameFrame.getGameWidth()) this.x = gameFrame.getGameWidth()-bufferedImage.getWidth() - boundsWith;
        if (this.y < boundsWith +10) y = boundsWith + 10;
        else if ((this.y + bufferedImage.getHeight()+boundsWith)>= gameFrame.getGameHeight()) this.y = gameFrame.getGameHeight()-bufferedImage.getHeight() - boundsWith;

    }

    private void randomDirection() {
        Direction[] values = Direction.values();
        if (random.nextInt(100) > 90)
        this.dir = values[random.nextInt(values.length)];
    }

    public void fire() {
        fireStrategy.factoryfire(this);
    }

    private void updateRectangle(){
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();
    }
    //获取开枪的模式
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
    }
    public RectTank(int x, int y, Direction dir, Group group, GameFrame frame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameFrame = frame;
        this.group = group;
        this.LoadFireStrategy();
    }
    public RectTank(int x, int y, Direction dir, Group group, GameFrame frame,int id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameFrame = frame;
        this.group = group;
        this.LoadFireStrategy();
        this.ID = id;
    }
    public RectTank(int x, int y, Direction dir, Group group, GameFrame frame, Boolean moving) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameFrame = frame;
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

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
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
