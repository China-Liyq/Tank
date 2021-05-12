package com.chinaliyq.abstractfactory.factory;

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
    public BaseTank createTank(int x, int y, Direction direction, Group group, Frame tankFrame) {
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, Frame tankFrame) {
        return null;
    }

    @Override
    public BaseExplode createExplode(int x, int y, Frame tankFrame) {
        return null;
    }
}
