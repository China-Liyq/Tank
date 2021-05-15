package com.chinaliyq.abstractfactory.core.son;

import com.chinaliyq.abstractfactory.factory.GameObject;
import com.chinaliyq.abstractfactory.core.Collider;
import com.chinaliyq.abstractfactory.factory.BaseBullet;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.util.Group;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/14 - 13:54
 * @Version: 1.0
 **/
public class BulletAndTankCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseBullet && o2 instanceof BaseTank){
            BaseBullet bullet = (BaseBullet) o1;
            BaseTank tank = (BaseTank) o2;
            if (bullet.rectangle.intersects(tank.rectangle) && bullet.group == tank.group){
                return true;
            }
            if (bullet.rectangle.intersects(tank.rectangle) && bullet.group != tank.group){
                bullet.die();
                tank.die();
                countPlayerScore(bullet);
                return true;
            }
        }else if (o1 instanceof BaseTank && o2 instanceof BaseBullet){
            this.collider(o2,o1);
        }
        return false;
    }

    private void countPlayerScore(BaseBullet bullet) {
        if (bullet.ID == 1 && bullet.group == Group.GOOD){
            gameModel.player_one.score += 1;
        } else if (bullet.ID == 2 && bullet.group == Group.GOOD){
            gameModel.player_two.score += 1;
        }
    }
}
