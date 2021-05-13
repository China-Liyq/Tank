package com.chinaliyq.abstractfactory.factory.son;

import com.chinaliyq.abstractfactory.bean.DefualtBullet;
import com.chinaliyq.abstractfactory.bean.DefualtExplode;
import com.chinaliyq.abstractfactory.bean.RectBullet;
import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.abstractfactory.factory.BaseExplode;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.factory.GameFactory;
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
    public BaseTank createTank(int x, int y, Direction dir, Group group, GameFrame frame, int id) {
        return new RectTank(x,y,dir,group,frame,id);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction dir, Group group, GameFrame gameFrame, int id) {
        return new DefualtBullet(x,y,dir,group,gameFrame,id);
    }

    @Override
    public BaseExplode createExplode(int x, int y, GameFrame gameFrame) {
        return new DefualtExplode(x,y,gameFrame);
    }
}
