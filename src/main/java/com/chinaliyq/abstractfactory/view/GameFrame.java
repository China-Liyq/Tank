package com.chinaliyq.abstractfactory.view;

import com.chinaliyq.abstractfactory.bean.RectBullet;
import com.chinaliyq.abstractfactory.bean.RectExplode;
import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.abstractfactory.factory.BaseExplode;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.factory.GameFactory;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.PropertyMgr;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/8 - 15:18
 * @Version: 1.0
 **/
public class GameFrame extends Frame {
    private static final int GAME_WIDTH = Integer.parseInt((String) PropertyMgr.getValue("gomeWidth"));
    private static final int GAME_HEIGHT = Integer.parseInt((String)PropertyMgr.getValue("gameHeight"));

    private static final String defaultFactory = (String) PropertyMgr.getValue("defaultFactory");
    private static final String specialFactory = (String) PropertyMgr.getValue("specialFactory");

    private List<BaseTank> tanks =new ArrayList();
    private List<BaseBullet> bullets =new ArrayList();
    private List<BaseExplode> explodes =new ArrayList();

    private GameFactory gameFactory;

    //玩家
    private BaseTank player_one;
    private BaseTank player_two;

    public GameFrame(){
        //更改爆炸
        this.loadFactoy(defaultFactory);
//        this.loadFactoy(specialFactory);

        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("TANK 2.0");
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    public void loadFactoy(String s){
        //加载工厂
        try {
            gameFactory =(GameFactory) Class.forName(s).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建玩家
        player_one = gameFactory.createTank(300,500, Direction.UP, Group.GOOD,this,1);
        player_two = gameFactory.createTank(600,500, Direction.UP, Group.GOOD,this,2);
    }


    private Image offScreenImage = null;
     //将所有物件都一起画出来
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
        g.drawString("坦克的数量：" + tanks.size(),12,85);
        g.drawString("子弹的数量：" + bullets.size(),12,60);
        g.drawString("爆炸的数量：" + explodes.size(),12,110);
        g.drawString("玩家1杀敌数：" + player_one.score,12,630);
        g.drawString("玩家2杀敌数：" + player_two.score,12,660);
        g.setColor(color);
        //画物件
        player_one.paint(g);
        player_two.paint(g);
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
            //玩家1
            case KeyEvent.VK_A:
                player_one.bLeft = true;
                break;
            case KeyEvent.VK_W:
                player_one.bUp = true;
                break;
            case KeyEvent.VK_S:
                player_one.bDown = true;
                break;
            case KeyEvent.VK_D:
                player_one.bRight = true;
                break;
            //玩家2
            case KeyEvent.VK_LEFT:
                player_two.bLeft = true;
                break;
            case KeyEvent.VK_UP:
                player_two.bUp = true;
                break;
            case KeyEvent.VK_RIGHT:
                player_two.bRight = true;
                break;
            case KeyEvent.VK_DOWN:
                player_two.bDown = true;
                break;
            case KeyEvent.VK_NUMPAD0:
                player_two.fire();
                break;
            default:
                break;
            }
            setPlayOneTankDir();
            setPlayTwoTankDir();
//            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                //玩家1
                case KeyEvent.VK_A:
                    player_one.bLeft = false;
                    break;
                case KeyEvent.VK_W:
                    player_one.bUp = false;
                    break;
                case KeyEvent.VK_D:
                    player_one.bRight = false;
                    break;
                case KeyEvent.VK_S:
                    player_one.bDown = false;
                    break;
                case KeyEvent.VK_J:
                    player_one.fire();
                    break;
                //玩家2
                case KeyEvent.VK_LEFT:
                    player_two.bLeft = false;
                    break;
                case KeyEvent.VK_UP:
                    player_two.bUp = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    player_two.bRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                    player_two.bDown = false;
                    break;
                case KeyEvent.VK_NUMPAD0:
                    player_two.fire();
                    break;
                default:
                    break;
            }
            setPlayOneTankDir();
            setPlayTwoTankDir();
        }
        /**
         * 玩家1改变方向
         */
        private void setPlayOneTankDir() {
            //坦克移动,没有按键不移动
            if (!player_one.bDown && !player_one.bRight && !player_one.bUp && !player_one.bLeft) player_one.moving = false;
            else{
                player_one.moving=(true);
                if (player_one.bLeft) player_one.dir=(Direction.LEFT);
                if (player_one.bUp)  player_one.dir=(Direction.UP);
                if (player_one.bRight) player_one.dir=(Direction.RIGHT);
                if (player_one.bDown) player_one.dir=(Direction.DOWN);
            }
        }
        /**
         * 玩家2改变方向
         */
        private void setPlayTwoTankDir() {
            //坦克移动,没有按键不移动
            if (!player_two.bDown && !player_two.bRight && !player_two.bUp && !player_two.bLeft) player_two.moving=(false);
            else{
                player_two.moving=(true);
                if (player_two.bLeft) player_two.dir=(Direction.LEFT);
                if (player_two.bUp)  player_two.dir=(Direction.UP);
                if (player_two.bRight) player_two.dir=(Direction.RIGHT);
                if (player_two.bDown) player_two.dir=(Direction.DOWN);
            }
        }
    }



    public List<BaseExplode> getExplodes() {
        return explodes;
    }

    public void setExplodes(List<BaseExplode> explodes) {
        this.explodes = explodes;
    }

    public void setPlayer_one(RectTank player_one) {
        this.player_one = player_one;
    }

    public static String getDefaultFactory() {
        return defaultFactory;
    }

    public static String getSpecialFactory() {
        return specialFactory;
    }

    public List<BaseTank> getTanks() {
        return tanks;
    }

    public void setTanks(List<BaseTank> tanks) {
        this.tanks = tanks;
    }

    public List<BaseBullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<BaseBullet> bullets) {
        this.bullets = bullets;
    }

    public GameFactory getGameFactory() {
        return gameFactory;
    }

    public void setGameFactory(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public BaseTank getPlayer_one() {
        return player_one;
    }

    public void setPlayer_one(BaseTank player_one) {
        this.player_one = player_one;
    }

    public BaseTank getPlayer_two() {
        return player_two;
    }

    public void setPlayer_two(BaseTank player_two) {
        this.player_two = player_two;
    }

    public void setPlayer_two(RectTank player_two) {
        this.player_two = player_two;
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
