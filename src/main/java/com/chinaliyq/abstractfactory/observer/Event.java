package com.chinaliyq.abstractfactory.observer;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/16 - 22:52
 * @Version: 1.0
 **/
abstract class Event<T> {
    T source;
    public T getSource() {
        return source;
    }
}
