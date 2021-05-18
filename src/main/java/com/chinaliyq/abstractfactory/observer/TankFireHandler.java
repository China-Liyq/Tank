package com.chinaliyq.abstractfactory.observer;

import com.chinaliyq.abstractfactory.factory.BaseTank;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/16 - 22:39
 * @Version: 1.0
 **/
public class TankFireHandler implements TankFireObserver {

    @Override
    public void actionOnFire(TankFireEvent event) {
        BaseTank source = event.getSource();
        source.fire();
    }
}
