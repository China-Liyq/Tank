package com.chinaliyq.abstractfactory.interfaces;

import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.abstractfactory.controller.GameModel;

import java.io.Serializable;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/13 - 9:35
 * @Version: 1.0
 **/
public interface IExplode extends Serializable {
    GameModel gameModel = GameModel.getInstance();
    void explode(RectTank tank);

}
