package com.chinaliyq.abstractfactory.core;

import com.chinaliyq.abstractfactory.core.son.*;
import com.chinaliyq.abstractfactory.factory.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: liyq
 * @Description: 所有碰撞
 * @Date: 2021/5/15 - 10:26
 * @Version: 1.0
 **/
public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        this.add(new BulletAndBulletCollider());
        this.add(new BulletAndTankCollider());
        this.add(new BulletAndWallCollider());
        this.add(new TankAndTankCollider());
        this.add(new TankAndWallCollider());
        this.add(new WallAndWallCollider());
    }

    public void add (Collider collider){
        colliders.add(collider);
    }
    @Override
    public boolean collider(GameObject gameObject1, GameObject gameObject2) {
        for (int i = 0; i < colliders.size(); i++) {
            Collider collider = colliders.get(i);
            boolean colliderValue = collider.collider(gameObject1, gameObject2);
            if (colliderValue){
                return true;
            }
        }
        return false;
    }
}
