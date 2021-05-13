package com.chinaliyq.abstractfactory.interfaces;

import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.entity.Tank;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 13:41
 * @Version: 1.0
 **/
public interface FireStrategy {
    void fire(Tank t);

    void factoryfire(RectTank rectTank);

}
