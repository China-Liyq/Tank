package com.chinaliyq.abstractfactory.factory;

import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.view.TankFrame;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:11
 * @Version: 1.0
 **/
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Direction direction, Group grou, Frame frame);
    public abstract BaseBullet createBullet(int x, int y, Frame frame);
    public abstract BaseExplode createExplode(int x, int y, Frame frame);
}
