package com.chinaliyq.abstractfactory.view;

import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.abstractfactory.factory.BaseExplode;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.factory.GameFactory;
import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.PropertyMgr;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/8 - 15:18
 * @Version: 1.0
 **/
public class GameFrame extends Frame {
    public GameModel gameModel =new GameModel();

    public GameFrame(){
        setSize(gameModel.GAME_WIDTH,gameModel.GAME_HEIGHT);
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

    private Image offScreenImage = null;
     //将所有物件都一起画出来
    @Override
    public void update(Graphics g) {
        if (null == offScreenImage){
            offScreenImage = this.createImage(gameModel.GAME_WIDTH,gameModel.GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,gameModel.GAME_WIDTH,gameModel.GAME_HEIGHT);
        gOffScreen.setColor(color);
        //将画笔交给物件画物件
        paint(gOffScreen);
        //显示图片
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        gameModel.paint(g);
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
                gameModel.player_one.bLeft = true;
                break;
            case KeyEvent.VK_W:
                gameModel.player_one.bUp = true;
                break;
            case KeyEvent.VK_S:
                gameModel.player_one.bDown = true;
                break;
            case KeyEvent.VK_D:
                gameModel.player_one.bRight = true;
                break;
            //玩家2
            case KeyEvent.VK_LEFT:
                gameModel.player_two.bLeft = true;
                break;
            case KeyEvent.VK_UP:
                gameModel.player_two.bUp = true;
                break;
            case KeyEvent.VK_RIGHT:
                gameModel.player_two.bRight = true;
                break;
            case KeyEvent.VK_DOWN:
                gameModel.player_two.bDown = true;
                break;
            case KeyEvent.VK_NUMPAD0:
                gameModel.player_two.fire();
                break;
            default:
                break;
            }
            setPlayerTankDir(gameModel.player_one);
            setPlayerTankDir(gameModel.player_two);
//            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                //玩家1
                case KeyEvent.VK_A:
                    gameModel.player_one.bLeft = false;
                    break;
                case KeyEvent.VK_W:
                    gameModel.player_one.bUp = false;
                    break;
                case KeyEvent.VK_D:
                    gameModel.player_one.bRight = false;
                    break;
                case KeyEvent.VK_S:
                    gameModel.player_one.bDown = false;
                    break;
                case KeyEvent.VK_J:
                   this.playerFire(gameModel.player_one);
                    break;
                //玩家2
                case KeyEvent.VK_LEFT:
                    gameModel.player_two.bLeft = false;
                    break;
                case KeyEvent.VK_UP:
                    gameModel.player_two.bUp = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    gameModel.player_two.bRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                    gameModel.player_two.bDown = false;
                    break;
                case KeyEvent.VK_NUMPAD0:
                    this.playerFire(gameModel.player_two);
                    break;
                default:
                    break;
            }
            setPlayerTankDir(gameModel.player_one);
            setPlayerTankDir(gameModel.player_two);
        }

        /**
         * 玩家改变方向
         */
        private void setPlayerTankDir(BaseTank tank) {
            //坦克移动,没有按键不移动
            if (!tank.bDown && !tank.bRight && !tank.bUp && !tank.bLeft) tank.moving = false;
            else {
                tank.moving=(true);
                if (tank.bLeft) tank.dir=Direction.LEFT;
                if (tank.bUp)  tank.dir=Direction.UP;
                if (tank.bRight) tank.dir=Direction.RIGHT;
                if (tank.bDown) tank.dir=Direction.DOWN;
            }
        }

        private void playerFire(BaseTank tank){
            tank.fire();
        }
    }
}
