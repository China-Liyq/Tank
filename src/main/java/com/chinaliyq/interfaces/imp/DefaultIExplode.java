package com.chinaliyq.interfaces.imp;

import com.chinaliyq.abstractfactory.bean.DefualtExplode;
import com.chinaliyq.abstractfactory.bean.RectTank;
import com.chinaliyq.abstractfactory.factory.BaseExplode;
import com.chinaliyq.interfaces.IExplode;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/13 - 9:39
 * @Version: 1.0
 **/
public class DefaultIExplode implements IExplode {
    private int explodeX,explodeY;
    @Override
    public void explode(RectTank tank) {
        if (null != tank.getBufferedImage()){
            explodeX = tank.getX() + tank.getBufferedImage().getWidth() / 2;
            explodeY = tank.getY() + tank.getBufferedImage().getHeight() / 2;
            BaseExplode explode = tank.getGameFrame().getGameFactory().createExplode(explodeX, explodeY, tank.gameFrame);
            tank.getGameFrame().getExplodes().add(explode);
        }
    }
}
