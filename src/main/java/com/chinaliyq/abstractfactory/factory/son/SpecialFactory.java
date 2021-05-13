package com.chinaliyq.abstractfactory.factory.son;

import com.chinaliyq.abstractfactory.bean.RectBullet;
import com.chinaliyq.abstractfactory.bean.RectExplode;
import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.abstractfactory.factory.BaseExplode;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.factory.GameFactory;
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
    public BaseTank createTank(int x, int y, Direction dir, Group group, GameModel gameModel, int id) {
        return new RectTank(x,y,dir,group,gameModel,id);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction dir, Group group, GameModel gameModel, int id) {
        return new RectBullet(x,y,dir,group,gameModel,id);
    }

    @Override
    public BaseExplode createExplode(int x, int y, GameModel gameModel) {
        return new RectExplode(x,y,gameModel);
    }
}
