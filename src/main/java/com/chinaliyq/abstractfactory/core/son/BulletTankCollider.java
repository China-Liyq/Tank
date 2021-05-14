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
public class BulletTankCollider extends Collider {
    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseBullet && o2 instanceof BaseTank){
            BaseBullet bullet = (BaseBullet) o1;
            BaseTank tank = (BaseTank) o2;
//            bullet.collideWith(tank);
            if (bullet.group == tank.getGroup())return;
            if (bullet.rectangle.intersects(tank.rectangle) && bullet.live == true && tank.live == true){
                bullet.die();
                tank.die();
                countPlayerScore(tank);
            }
        }else if (o1 instanceof BaseTank && o2 instanceof BaseBullet){
            this.collider(o2,o1);
        }
    }

    private void countPlayerScore(BaseTank tank) {
        if (tank.ID == 1 && tank.group == Group.GOOD){
            tank.gameModel.player_one.score += 1;
        }
        else if (tank.ID == 2 && tank.group == Group.GOOD){
            tank.gameModel.player_two.score += 1;
        }
    }
}
