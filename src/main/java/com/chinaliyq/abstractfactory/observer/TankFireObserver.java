package com.chinaliyq.abstractfactory.observer;

import java.io.Serializable;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/16 - 22:20
 * @Version: 1.0
 **/
public interface TankFireObserver extends Serializable{
    void actionOnFire(TankFireEvent event);
}
