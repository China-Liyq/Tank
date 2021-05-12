package com.chinaliyq.abstractfactory;

import com.chinaliyq.util.Direction;
import com.chinaliyq.util.Group;
import com.chinaliyq.view.TankFrame;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 17:11
 * @Version: 1.0
 **/
public abstract class GameFactory {
    public abstract AbstractTank createTank(int x, int y, Direction direction, Group grou, TankFrame tankFrame);
    public abstract BaseBullet createBullet(int x, int y, TankFrame tankFrame);
    public abstract AbstractExplode createExplode(int x, int y, TankFrame tankFrame);
}
