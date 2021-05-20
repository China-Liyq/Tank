package com.chinaliyq.designpatterns.Iterator;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/18 - 15:07
 * @Version: 1.0
 **/
public class Main {
    public static void main(String[] args) {
        Collection_ arrayList_ = new ArrayList_();
        Collection_ linkedList_ = new LinkedList_();
        for (int i = 0; i < 10; i++) {
            arrayList_.add(i);
            linkedList_.add(i);
        }
        System.out.println(arrayList_.size());
        System.out.println(linkedList_.size());
    }
}
