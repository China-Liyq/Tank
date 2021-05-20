package com.chinaliyq.abstractfactory.controller;

import com.chinaliyq.abstractfactory.core.ColliderChain;
import com.chinaliyq.abstractfactory.factory.*;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.PropertyMgr;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/13 - 18:35
 * @Version: 1.0
 **/
public class GameModel implements Serializable {
    public static final int GAME_WIDTH = Integer.parseInt((String) PropertyMgr.getValue("gomeWidth"));
    public static final int GAME_HEIGHT = Integer.parseInt((String)PropertyMgr.getValue("gameHeight"));
    private static final String defaultFactory = (String) PropertyMgr.getValue("defualtFactory");
    //还是需要改动，可以后续再装配
    private static final String specialFactory = (String) PropertyMgr.getValue("specialFactory");

    private static final GameModel GAME_MODEL =new GameModel();

    private List<GameObject> gameObjects = new ArrayList<>();
    //    private java.util.List<BaseTank> tanks =new ArrayList();
    //    private java.util.List<BaseBullet> bullets =new ArrayList();
    //    private List<BaseExplode> explodes =new ArrayList();
//    public Collider bulletTankCollider = new BulletTankCollider();
//    public Collider tankAndTankCollider = new TankAndTankCollider();
    private ColliderChain colliderChain = new ColliderChain();
    public GameFactory gameFactory;
    private Random random = new Random();

    public int countTanks = 0;
    public int countBullets = 0;
    public int countExplodes = 0;
    public int countWalls = 0;
    //玩家
    public BaseTank player_one;
    public BaseTank player_two;
    //静态加载
    static {
        GAME_MODEL.init();
    }
    public void paint(Graphics g){
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
        for (int i = 0; i < gameObjects.size() - 1; i++) {
            for (int j = i+1; j < gameObjects.size(); j++) {
                GameObject gameObject1 = gameObjects.get(i);
                GameObject gameObject2 = gameObjects.get(j);
                colliderChain.collider(gameObject1,gameObject2);
            }
        }
        this.countBaseObjetct();
        this.createBadTank();
/**
         //画物件
//        player_one.paint(g);
//        player_two.paint(g);
    for (int i = 0; i < tanks.size(); i++) {
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
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawString("坦克的数量：" + countTanks,12,60);
        g.drawString("子弹的数量：" + countBullets,12,85);
        g.drawString("爆炸的数量：" + countExplodes,12,110);
        g.drawString("土墙的数量：" + countWalls,12,135);
        g.setColor(Color.red);
        g.drawString("玩家1杀敌数：" + player_one.score,12,630);
        g.drawString("玩家2杀敌数：" + player_two.score,12,660);
        g.setColor(Color.PINK);
        String instruction = "玩家1移动：左下右上ASDW，射击J，切换子弹K,复活U; " +
                "玩家2移动：方向键左下右上，射击NUM0，切换子弹NUM1，复活NUM7;\n"+"保存1，加载2";
        g.drawString(instruction,200,700);
        g.setColor(color);
    }

    public void init(){
        //加载工厂来可更改爆炸
        loadDefaultFactoy();
        //创建敌人
        this.badTanks();
        //创建玩家
        this.goodTanks();

        this.addWall(20);
    }

    //创建敌方坦克
    public void createBadTank(){
        if (countTanks > 0 && countTanks < 5 && GAME_MODEL != null){
            int randNum = random.nextInt(6) + 1;
            for (int i = 0; i < randNum; i++) {
                BaseTank tank = gameFactory.createTank(100 + 80 * i , 50, Direction.DOWN, Group.BAD, 0);
                tank.moving = true;
                gameObjects.add(tank);
            }
        }
    }
    //默认
    public void loadDefaultFactoy(){
        //加载工厂
        try {
            gameFactory =(GameFactory) Class.forName(defaultFactory).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //特殊--对接工厂来创建的对象
    public void loadSpecialFactoy(){
        //加载工厂
        try {
            gameFactory =(GameFactory) Class.forName(specialFactory).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void badTanks(){
        int badTankCount = Integer.parseInt((String) PropertyMgr.getValue("initTankCount"));
        for (int i = 0; i < badTankCount; i++) {
            BaseTank tank = gameFactory.createTank(50 + 80 * i, 200, Direction.DOWN, Group.BAD,0);
            tank.moving = true;
            gameObjects.add(tank);
        }
    }
    public void goodTanks(){
        //创建玩家
        player_one = gameFactory.createTank(300,500, Direction.UP, Group.GOOD,1);
        player_two = gameFactory.createTank(600,500, Direction.UP, Group.GOOD,2);
        gameObjects.add(player_one);
        gameObjects.add(player_two);
    }
    public void addWall(int count){
        int width = 50, height = 50 , border = 8;
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(GAME_WIDTH - 16-50) + 8;
            int y = random.nextInt(GAME_HEIGHT - 16) + 8;
            BaseWall wall = gameFactory.createWall(x, y, 50, 50);
            gameObjects.add(wall);
        }
    }

    public void countBaseObjetct(){
        countTanks = 0 ;
        countBullets = 0;
        countExplodes = 0;
        countWalls = 0;
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject gameObject = gameObjects.get(i);
            if (gameObject instanceof BaseTank && ((BaseTank) gameObject).ID == 0){
                countTanks++;
            }else if (gameObject instanceof BaseBullet){
                countBullets++;
            }else if (gameObject instanceof BaseExplode){
                countExplodes++;
            }else if (gameObject instanceof BaseWall){
                countWalls++;
            }
        }
    }

    public void save(){
        File file = new File("src/main/resources/data/tank.data");
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                ) {

            objectOutputStream.writeObject(player_one);
            objectOutputStream.writeObject(player_two);
            objectOutputStream.writeObject(gameObjects);
            System.out.println("保存成功!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load() {
        File file = new File("src/main/resources/data/tank.data");
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        try(
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            player_one = (BaseTank) objectInputStream.readObject();
            player_two = (BaseTank) objectInputStream.readObject();
            gameObjects = (List) objectInputStream.readObject();
            System.out.println("读取成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static GameModel getInstance(){
        return GAME_MODEL;
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
