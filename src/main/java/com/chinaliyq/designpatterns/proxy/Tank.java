package com.chinaliyq.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/17 - 11:38
 * @Version: 1.0
 **/
public class Tank implements Moveable {

    @Override
    public void move() {
        System.out.println("Tank moving cccccc....");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tank tank = new Tank();
        //静态代理
//        new TankLogProxy(new TankTimeProxy(tank)).move();

        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");
        Moveable m = (Moveable) Proxy.newProxyInstance(Tank.class.getClassLoader(),
                new Class[]{Moveable.class},
                new MyLogHandler(tank));
        m.move();
    }

}
//动态代理
class MyLogHandler implements InvocationHandler{
    Moveable m;

    public MyLogHandler(Moveable m) {
        this.m = m;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName()+"---start...");
        final Object o = method.invoke(m, args);
        System.out.println(method.getName()+"---end...");
        return o;
    }
}



//静态代理
class TankTimeProxy implements Moveable{
    Moveable m;

    public TankTimeProxy(Moveable m) {
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("TankTimeProxy");
        long start = System.currentTimeMillis();
        m.move();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
class TankLogProxy implements Moveable{
    Moveable m;

    public TankLogProxy(Moveable m) {
        this.m = m;
    }
    @Override
    public void move() {
        System.out.println("start moving...");
        m.move();
        long end = System.currentTimeMillis();
        System.out.println("stopped...!");
    }
}

interface Moveable{
    void move();
}