package com.chinaliyq.abstractfactory.controller;

import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.abstractfactory.factory.BaseExplode;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.factory.GameFactory;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.PropertyMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/13 - 18:35
 * @Version: 1.0
 **/
public class GameModel {
    public static final int GAME_WIDTH = Integer.parseInt((String) PropertyMgr.getValue("gomeWidth"));
    public static final int GAME_HEIGHT = Integer.parseInt((String)PropertyMgr.getValue("gameHeight"));
    private static final String defaultFactory = (String) PropertyMgr.getValue("defaultFactory");
    private static final String specialFactory = (String) PropertyMgr.getValue("specialFactory");

    private java.util.List<BaseTank> tanks =new ArrayList();
    private java.util.List<BaseBullet> bullets =new ArrayList();
    private List<BaseExplode> explodes =new ArrayList();
    //玩家
    public BaseTank player_one;
    public BaseTank player_two;
    public GameFactory gameFactory;

    public void paint(Graphics g){
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

    public GameModel(){
        //更改爆炸
        this.loadFactoy(defaultFactory);
//        this.loadFactoy(specialFactory);

        this.badTanks();
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
    public void badTanks(){
        int badTankCount = Integer.parseInt((String) PropertyMgr.getValue("initTankCount"));

        for (int i = 0; i < badTankCount; i++) {
            BaseTank tank = gameFactory.createTank(50 + 80 * i, 200, Direction.DOWN, Group.BAD, this,0);
            tank.moving = true;
            tanks.add(tank);
        }
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

    public List<BaseExplode> getExplodes() {
        return explodes;
    }

    public void setExplodes(List<BaseExplode> explodes) {
        this.explodes = explodes;
    }

    public GameFactory getGameFactory() {
        return gameFactory;
    }

    public void setGameFactory(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }
}
