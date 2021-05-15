package com.chinaliyq.abstractfactory.core.son;

import com.chinaliyq.abstractfactory.core.Collider;
import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.factory.GameObject;
import com.chinaliyq.util.Group;

/**
 * @Author: liyq
 * @Description: 子弹与子弹
 * @Date: 2021/5/14 - 13:54
 * @Version: 1.0
 **/
public class BulletAndBulletCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseBullet && o2 instanceof BaseBullet){
            BaseBullet bullet1 = (BaseBullet) o1;
            BaseBullet bullet2 = (BaseBullet) o2;
            if (bullet1.rectangle.intersects(bullet2.rectangle) && bullet1.group == bullet2.group && bullet1.ID != bullet2.ID){
                bullet1.die();
                bullet2.die();
                return true;
            } else if (bullet1.rectangle.intersects(bullet2.rectangle) && bullet1.group != bullet2.group){
                bullet1.die();
                bullet2.die();
                return true;
            }
        }
        return false;
    }
}
