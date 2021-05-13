package com.chinaliyq.entity;

import com.chinaliyq.abstractfactory.interfaces.FireStrategy;
import com.chinaliyq.abstractfactory.interfaces.imp.DefaultFireStrategy;
import com.chinaliyq.abstractfactory.interfaces.imp.FourDirectionFireStrategy;
import com.chinaliyq.util.*;
import com.chinaliyq.view.TankFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/8 - 17:01
 * @Version: 1.0
 **/
public class Tank {
    private int x, y;
    private Direction dir = Direction.UP;
    private final static int SPEED = 4;
    private boolean moving = false;
    //持有画板来画画
    private TankFrame tankFrame = null;
    private BufferedImage bufferedImage;
    private boolean live = true;

    private Random random = new Random();
    private Group group = Group.BAD;
    private int index = 0;
    private int count = 0;
    private FireStrategy defaultFireStrategy = new DefaultFireStrategy();
    private FireStrategy fourDirectionFireStrategy = new FourDirectionFireStrategy();
    private FireStrategy fireStrategy;

    private BufferedImage[] TankLefts;
    private BufferedImage[] TankUps;
    private BufferedImage[] TankRights;
    private BufferedImage[] TankDowns;
    private Rectangle rectangle = new Rectangle();


    public void paint(Graphics g) {
        TankLefts = this.group == Group.GOOD ? ResourceMgr.goodTankLefts : ResourceMgr.badTankLefts;
        TankUps = this.group == Group.GOOD ? ResourceMgr.goodTankUps : ResourceMgr.badTankUps;
        TankRights = this.group == Group.GOOD ? ResourceMgr.goodTankRights : ResourceMgr.badTankRights;
        TankDowns = this.group == Group.GOOD ? ResourceMgr.goodTankDowns : ResourceMgr.badTankDowns;
        if (!this.isLive()){
            tankFrame.tanks.remove(this);
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
        this.updateRectangel();
//手画图片
//        Color color = g.getColor();
//        g.setColor(Color.PINK);
//        g.fillRect(x,y,50,50);
//        g.setColor(color);
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
        if (count==200){
            b = System.currentTimeMillis();
            System.out.println("使用时间为：--" +(b-a));
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

        boundsCheck();
        rectangle.x = this.x;
        rectangle.y = this.y;
    }
    private int boundsWith = 8;
    private void boundsCheck() {
        boundsWith = bufferedImage.getWidth() / 2;
        if (this.x < boundsWith) x = boundsWith;
        else if ((this.x + bufferedImage.getWidth()+boundsWith)>= tankFrame.getGameWidth()) this.x = tankFrame.getGameWidth()-bufferedImage.getWidth() - boundsWith;
        if (this.y < boundsWith +10) y = boundsWith + 10;
        else if ((this.y + bufferedImage.getHeight()+boundsWith)>= tankFrame.getGameHeight()) this.y = tankFrame.getGameHeight()-bufferedImage.getHeight() - boundsWith;

    }

    private void randomDirection() {
        Direction[] values = Direction.values();
        this.dir = values[random.nextInt(values.length)];
    }

    private int tankCenterX,tankCenterY;
    public void fire() {
       fireStrategy.fire(this);
    }

//    public void fire() {
//        //获取枪口中心的位置
//        switch (dir){
//            case LEFT:
//                tankCenterX = x + 0;
//                tankCenterY = y + bufferedImage.getHeight() / 2;
//                break;
//            case UP:
//                tankCenterX = x + bufferedImage.getWidth() / 2;
//                tankCenterY = y + 0;
//                break;
//            case RIGHT:
//                tankCenterX = x + bufferedImage.getWidth();
//                tankCenterY = y + bufferedImage.getHeight() / 2;
//                break;
//            case DOWN:
//                tankCenterX = x + bufferedImage.getWidth() / 2;
//                tankCenterY = y + bufferedImage.getHeight();
//                break;
//        }
//        Bullet bullet = new Bullet(tankCenterX, tankCenterY, this.dir,this.group, this.tankFrame);
//        tankFrame.bullets.add(bullet);
//        //队友不能打队友
//        if (this.group == Group.GOOD)
//            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
////        //获取坦克中心位置
////        tankCenterX = this.x + bufferedImage.getWidth() / 2;
////        tankCenterY = this.y + bufferedImage.getHeight() / 2;
//    }

    private int explodeX,explodeY;
    public void die(){
        this.live = false;
        explodeX = this.x + bufferedImage.getWidth() / 2;
        explodeY = this.y + bufferedImage.getHeight() / 2;
        tankFrame.getExplodes().add(new Explode(explodeX,explodeY,tankFrame));
    }






    public Tank(int x, int y, Direction dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.getFireStrategy();
    }
    //获取开枪的模式
    private void getFireStrategy(){
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

    private void updateRectangel(){
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();
    }

    public Tank(int x, int y, Direction dir, Group group, TankFrame tankFrame,Boolean moving) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.moving =moving;
        this.getFireStrategy();
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public Tank() {
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    @Override
    public String toString() {
        return "Tank{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", moving=" + moving +
                ", tankFrame=" + tankFrame +
                ", bufferedImage=" + bufferedImage +
                ", live=" + live +
                ", tankCenterX=" + tankCenterX +
                ", tankCenterY=" + tankCenterY +
                '}';
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
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

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
