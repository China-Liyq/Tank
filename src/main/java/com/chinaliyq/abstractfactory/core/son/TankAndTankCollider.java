package com.chinaliyq.abstractfactory.core.son;

import com.chinaliyq.abstractfactory.core.Collider;
import com.chinaliyq.abstractfactory.factory.BaseTank;
import com.chinaliyq.abstractfactory.factory.GameObject;
import com.chinaliyq.util.Group;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/14 - 20:52
 * @Version: 1.0
 **/
public class TankAndTankCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseTank && o2 instanceof BaseTank){
            BaseTank tank1 = (BaseTank) o1;
            BaseTank tank2 = (BaseTank) o2;
            if (tank1.rectangle.intersects(tank2.rectangle) && tank1.group == tank2.group){
                tank1.back();
                tank2.back();
                return true;
            }else if (tank1.rectangle.intersects(tank2.rectangle) && tank1.group != tank2.group){
                countPlayerScore(tank1);
                countPlayerScore(tank2);
                tank1.die();
                tank2.die();
                return true;
            }
        }
        return false;
    }
    private void countPlayerScore(BaseTank tank) {
        if (tank.ID == 1 && tank.group == Group.GOOD && tank.live == true){
            gameModel.player_one.score += 1;
        }
        else if (tank.ID == 2 && tank.group == Group.GOOD && tank.live == true){
            gameModel.player_two.score += 1;
        }
    }
}
