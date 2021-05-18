package com.chinaliyq.designpatterns.Iterator;

import java.util.Arrays;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/18 - 14:15
 * @Version: 1.0
 **/
public class Main {
    public static void main(String[] args) {
        ArrayList_ arrayList_ = new ArrayList_();
        for (int i = 0; i < 15; i++) {
            arrayList_.add(i);
        }
        System.out.println(arrayList_.objects);
    }

}

class ArrayList_{
    Object[] objects = new Object[10];
    private int index = 0;
    public void add(Object o ){
        if(index==objects.length){
            Object[] newObjects = new Object[objects.length*2];
            System.arraycopy(objects,0,newObjects,0,objects.length);
            objects = newObjects;
        }
        objects[index] = 0;
        index++;
    }

    public int getIndex() {
        return index;
    }

    public Object[] getObjects() {
        return objects;
    }
}