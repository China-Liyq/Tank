package com.chinaliyq.abstractfactory.factory.son;

import com.chinaliyq.abstractfactory.bean.DefualtWall;
import com.chinaliyq.abstractfactory.bean.RectBullet;
import com.chinaliyq.abstractfactory.bean.RectExplode;
import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.factory.*;
import com.chinaliyq.abstractfactory.view.GameFrame;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/13 - 9:12
 * @Version: 1.0
 **/
public class SpecialFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Direction dir, Group group, int id) {
        return new RectTank(x,y,dir,group,id);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction dir, Group group, int id) {
        return new RectBullet(x,y,dir,group,id);
    }

    @Override
    public BaseExplode createExplode(int x, int y ) {
        return new RectExplode(x,y);
    }

    @Override
    public BaseWall createWall(int x, int y, int w, int h) {
        return new DefualtWall(x,y,w,h);
    }
}
