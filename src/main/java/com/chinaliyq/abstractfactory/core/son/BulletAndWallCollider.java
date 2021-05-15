package com.chinaliyq.abstractfactory.core.son;

import com.chinaliyq.abstractfactory.core.Collider;
import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.factory.BaseWall;
import com.chinaliyq.abstractfactory.factory.GameObject;
import com.chinaliyq.util.Group;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/14 - 13:54
 * @Version: 1.0
 **/
public class BulletAndWallCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseBullet && o2 instanceof BaseWall){
            BaseBullet bullet = (BaseBullet) o1;
            BaseWall wall = (BaseWall) o2;
            if (bullet.rectangle.intersects(wall.rectangle)){
                bullet.die();
                wall.die();
                return true;
            }
        }else if (o1 instanceof BaseWall && o2 instanceof BaseBullet){
            this.collider(o2,o1);
        }
        return false;
    }
}
