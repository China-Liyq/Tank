package com.chinaliyq.abstractfactory.core;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.factory.GameObject;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/14 - 13:51
 * @Version: 1.0
 **/
public interface Collider {
    GameModel gameModel = GameModel.getInstance();

    boolean collider(GameObject o1,GameObject o2);

}
