package com.chinaliyq.abstractfactory.controller;

import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.abstractfactory.view.GameFrame;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.util.PropertyMgr;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 20:10
 * @Version: 1.0
 **/
public class MainController {
    private GameFrame gameFrame = new GameFrame();

    public void start(){
        int badTankCount = Integer.parseInt((String) PropertyMgr.getValue("initTankCount"));

        for (int i = 0; i < badTankCount; i++) {
            RectTank badTank = new RectTank(50 + 80 * i, 200, Direction.DOWN, Group.BAD, gameFrame, true);
            gameFrame.getTanks().add(badTank);
        }
        while (true){
            try {
                Thread.sleep(25);
                gameFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
