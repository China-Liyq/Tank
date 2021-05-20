package com.chinaliyq.designpatterns.Iterator;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/18 - 15:03
 * @Version: 1.0
 **/
public interface Collection_ {
    void add(Object o);
    int size();
    Iterator_ iterator();
}
