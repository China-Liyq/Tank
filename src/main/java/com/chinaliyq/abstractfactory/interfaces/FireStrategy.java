package com.chinaliyq.abstractfactory.interfaces;

import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.entity.Tank;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/12 - 13:41
 * @Version: 1.0
 **/
public interface FireStrategy {
    GameModel gameModel = GameModel.getInstance();
    void fire(Tank t);

    void factoryfire(RectTank rectTank);

}
