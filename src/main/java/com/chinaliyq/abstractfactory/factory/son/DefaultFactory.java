package com.chinaliyq.abstractfactory.factory.son;

import com.chinaliyq.abstractfactory.bean.*;
import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.factory.*;
import com.chinaliyq.abstractfactory.view.GameFrame;
import com.chinaliyq.entity.Tank;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.view.TankFrame;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:46
 * @Version: 1.0
 **/
public class DefaultFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Direction dir, Group group, int id) {
        return new RectTank(x,y,dir,group,id);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction dir, Group group, int id) {
        return new DefualtBullet(x,y,dir,group,id);
    }

    @Override
    public BaseExplode createExplode(int x, int y) {
        return new DefualtExplode(x,y);
    }


    @Override
    public BaseWall createWall(int x, int y,int w, int h) {
        return new DefualtWall(x,y,w,h);
    }
}
