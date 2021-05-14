package com.chinaliyq.view;

import com.chinaliyq.abstractfactory.bean.RectBullet;
import com.chinaliyq.abstractfactory.bean.RectExplode;
import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.entity.Bullet;
import com.chinaliyq.entity.Explode;
import com.chinaliyq.entity.Tank;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/8 - 15:18
 * @Version: 1.0
 **/
public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;
    public List<Bullet> bullets =new ArrayList();
    public List<Tank> tanks =new ArrayList();
    public List<Explode> explodes =new ArrayList();

    public List<RectTank> rectTanks =new ArrayList();
    public List<RectBullet> rectBullets =new ArrayList();
    public List<RectExplode> rectExplodes =new ArrayList();
    public Tank myTank = new Tank(450,300, Direction.UP, Group.GOOD,this);
//    Explode explode =new Explode(100,100,this);

    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("TANK 1.0");
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    Image offScreenImage = null;

    /**
     * 将所有物件都一起画出来
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (null == offScreenImage){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(color);
        //将画笔交给物件画物件
        paint(gOffScreen);
        //显示图片
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量：" +bullets.size(),12,60);
        g.drawString("坦克的数量：" +tanks.size(),12,85);
        g.drawString("爆炸的数量：" +explodes.size(),12,110);
        g.setColor(color);

        //画坦克
        myTank.paint(g);
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //碰撞逻辑
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

//        //画子弹
//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();){
//            Bullet bullet = it.next();
//            if (!bullet.isLive()) it.remove();
//            bullet.paint(g);
//        }
//不能删除 会导致bug  Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
//        for(Bullet bullet:bullets){
//            bullet.paint(g);
//        }
    }

    class MyKeyListener extends KeyAdapter{
        //方向确定移动
        boolean bLeft = false;
        boolean bUp = false;
        boolean bRight = false;
        boolean bDown = false;

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
            case KeyEvent.VK_LEFT:
                bLeft = true;
                break;
            case KeyEvent.VK_UP:
                bUp = true;
                break;
            case KeyEvent.VK_RIGHT:
                bRight = true;
                break;
            case KeyEvent.VK_DOWN:
                bDown = true;
                break;
            default:
                break;
            }
            setMainTankDir();
//            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bLeft = false;
                    break;
                case KeyEvent.VK_UP:
                    bUp = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bDown = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;

                default:
                    break;
            }
            setMainTankDir();
        }
        /**
         * 改变方向
         */
        private void setMainTankDir() {
            //坦克移动,没有按键不移动
            if (!bDown && !bRight && !bUp && !bLeft) myTank.setMoving(false);
            else{
                myTank.setMoving(true);
                if (bLeft) myTank.setDir(Direction.LEFT);
                if (bUp)  myTank.setDir(Direction.UP);
                if (bRight) myTank.setDir(Direction.RIGHT);
                if (bDown) myTank.setDir(Direction.DOWN);
            }

        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }

    public List<Explode> getExplodes() {
        return explodes;
    }

    public void setExplodes(List<Explode> explodes) {
        this.explodes = explodes;
    }

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }

    public int getGameWidth() {
        return GAME_WIDTH;
    }

    public int getGameHeight() {
        return GAME_HEIGHT;
    }

    public Image getOffScreenImage() {
        return offScreenImage;
    }

    public void setOffScreenImage(Image offScreenImage) {
        this.offScreenImage = offScreenImage;
    }
}
