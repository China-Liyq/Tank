package com.chinaliyq.abstractfactory.observer;

import com.chinaliyq.abstractfactory.factory.BaseTank;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/16 - 22:14
 * @Version: 1.0
 **/
public class TankFireEvent extends Event<BaseTank> {

    public TankFireEvent(BaseTank source) {
        this.source = source;
    }

    public BaseTank getSource() {
        return source;
    }
}
