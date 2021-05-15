package com.chinaliyq.abstractfactory.factory;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.view.GameFrame;
import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:11
 * @Version: 1.0
 **/
public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Direction dir, Group group, int id);

    public abstract BaseBullet createBullet(int x, int y, Direction dir, Group group,int id);

    public abstract BaseExplode createExplode(int x, int y);

    public abstract BaseWall createWall(int x, int y,int w, int h);
}
