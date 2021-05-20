package com.chinaliyq.designpatterns.Iterator;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/18 - 14:15
 * @Version: 1.0
 **/
public class MyArrayList {
    public static void main(String[] args) {
        ArrayList_ arrayList_ = new ArrayList_();
        for (int i = 0; i < 15; i++) {
            arrayList_.add(i);
        }
        for (int i = 0; i < arrayList_.size(); i++) {
            System.out.println(arrayList_.objects[i]);
        }
    }

}

class ArrayList_ implements Collection_{
    Object[] objects = new Object[10];
    private int size = 0;
    @Override
    public void add(Object o ){
        if(size ==objects.length){
            Object[] newObjects = new Object[objects.length*2];
            System.arraycopy(objects,0,newObjects,0,objects.length);
            objects = newObjects;
        }
        objects[size] = o;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator_ iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator_{
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            if (currentIndex >= size) return false;
            return true;
        }

        @Override
        public Object next() {
            Object object = objects[currentIndex];
            currentIndex++;
            return object;
        }
    }


}