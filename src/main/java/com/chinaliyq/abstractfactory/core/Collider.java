package com.chinaliyq.abstractfactory.core;

import com.chinaliyq.abstractfactory.controller.GameModel;
import com.chinaliyq.abstractfactory.factory.GameObject;

import java.io.Serializable;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/14 - 13:51
 * @Version: 1.0
 **/
public interface Collider extends Serializable {
    GameModel gameModel = GameModel.getInstance();

    boolean collider(GameObject o1,GameObject o2);

}
