package com.chinaliyq.abstractfactory.controller;

import com.chinaliyq.abstractfactory.core.son.TankAndTankCollider;
import com.chinaliyq.abstractfactory.factory.GameObject;
import com.chinaliyq.abstractfactory.core.Collider;
import com.chinaliyq.abstractfactory.core.son.BulletTankCollider;
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

//    private java.util.List<BaseTank> tanks =new ArrayList();
//    private java.util.List<BaseBullet> bullets =new ArrayList();
//    private List<BaseExplode> explodes =new ArrayList();
    private List<GameObject> gameObjects = new ArrayList<>();
    public Collider bulletTankCollider = new BulletTankCollider();
    public Collider tankAndTankCollider = new TankAndTankCollider();
    public GameFactory gameFactory;

    public int countTanks = 0;
    public int countBullets = 0;
    public int countExplodes = 0;
    //玩家
    public BaseTank player_one;
    public BaseTank player_two;

    public void paint(Graphics g){
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawString("坦克的数量：" + countTanks,12,85);
        g.drawString("子弹的数量：" + countBullets,12,60);
        g.drawString("爆炸的数量：" + countExplodes,12,110);
        g.setColor(Color.red);
        g.drawString("玩家1杀敌数：" + player_one.score,12,630);
        g.drawString("玩家2杀敌数：" + player_two.score,12,660);
        g.setColor(color);
        //画物件
//        player_one.paint(g);
//        player_two.paint(g);
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
        for (int i = 0; i < gameObjects.size() - 1; i++) {
            for (int j = i+1; j < gameObjects.size(); j++) {
                GameObject gameObject1 = gameObjects.get(i);
                GameObject gameObject2 = gameObjects.get(j);
                bulletTankCollider.collider(gameObject1,gameObject2);
                tankAndTankCollider.collider(gameObject1,gameObject2);
            }
        }
        this.countBaseObjetct();

/**       for (int i = 0; i < tanks.size(); i++) {
//            tanks.get(i).paint(g);
//        }
//        for (int i = 0; i < bullets.size(); i++) {
//            bullets.get(i).paint(g);
//        }
//        for (int i = 0; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }

//        //碰撞逻辑
//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++) {
//                bullets.get(i).collideWith(tanks.get(j));
//            }
//        }
 */
    }

    public GameModel(){
        //加载工厂来更改爆炸
//        this.loadFactoy(defaultFactory);
        this.loadFactoy(specialFactory);
        //创建敌人
        this.badTanks();
        //创建玩家
        this.goodTanks();
    }

    public void loadFactoy(String s){
        //加载工厂
        try {
            gameFactory =(GameFactory) Class.forName(s).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void badTanks(){
        int badTankCount = Integer.parseInt((String) PropertyMgr.getValue("initTankCount"));

        for (int i = 0; i < badTankCount; i++) {
            BaseTank tank = gameFactory.createTank(50 + 80 * i, 200, Direction.DOWN, Group.BAD, this,0);
            tank.moving = true;
            gameObjects.add(tank);
        }
    }
    public void goodTanks(){
        //创建玩家
        player_one = gameFactory.createTank(300,500, Direction.UP, Group.GOOD,this,1);
        player_two = gameFactory.createTank(600,500, Direction.UP, Group.GOOD,this,2);
        gameObjects.add(player_one);
        gameObjects.add(player_two);
    }
    public void countBaseObjetct(){
        countTanks = 0 ;
        countBullets = 0;
        countExplodes = 0;
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject gameObject = gameObjects.get(i);
            if (gameObject instanceof BaseTank){
                countTanks++;
            }else if (gameObject instanceof BaseBullet){
                countBullets++;
            }
            if (gameObject instanceof BaseExplode){
                countExplodes++;
            }
        }
    }

    public GameFactory getGameFactory() {
        return gameFactory;
    }

    public void setGameFactory(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
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
}
