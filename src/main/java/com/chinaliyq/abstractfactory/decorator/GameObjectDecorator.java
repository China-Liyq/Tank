package com.chinaliyq.abstractfactory.decorator;

import com.chinaliyq.abstractfactory.factory.GameObject;

import java.awt.*;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/15 - 21:54
 * @Version: 1.0
 **/
public abstract class GameObjectDecorator extends GameObject {
    GameObject gameObject;

    public GameObjectDecorator(GameObject gameObject) {
        this.gameObject = gameObject;
    }

}
