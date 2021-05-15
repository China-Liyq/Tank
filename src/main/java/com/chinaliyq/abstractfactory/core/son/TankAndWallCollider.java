package com.chinaliyq.abstractfactory.core.son;

import com.chinaliyq.abstractfactory.core.Collider;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.factory.BaseWall;
import com.chinaliyq.abstractfactory.factory.GameObject;
import com.chinaliyq.util.Group;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/14 - 20:52
 * @Version: 1.0
 **/
public class TankAndWallCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseTank && o2 instanceof BaseWall){
            BaseTank tank = (BaseTank) o1;
            BaseWall baseWall = (BaseWall) o2;
           if (tank.rectangle.intersects(baseWall.rectangle)){
                tank.back();
                return true;
            }
        }else if(o2 instanceof BaseTank && o1 instanceof BaseWall){
            this.collider(o2,o1);
        }
        return false;
    }
}
