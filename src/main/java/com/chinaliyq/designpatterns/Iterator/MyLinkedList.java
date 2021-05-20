package com.chinaliyq.designpatterns.Iterator;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/18 - 14:31
 * @Version: 1.0
 **/
public class MyLinkedList {
    public static void main(String[] args) {
        LinkedList_ linkedList_ = new LinkedList_();
        for (int i = 1; i < 6; i++) {
            linkedList_.add(i);
        }
        System.out.println(linkedList_.size());
        int count = 0;
//        boolean b = linkedList_.iterator().hasNext();
//        System.out.println(b);
        for (int i = 0; i < linkedList_.size(); i++) {
//            boolean next = linkedList_.iterator().hasNext();
            System.out.println(linkedList_.iterator().next());
        }
        System.out.println("--------");
        while (linkedList_.iterator().hasNext()){
//            System.out.print(++count+"\t");
            System.out.println(linkedList_.iterator().next());
        }
    }

}

class LinkedList_ implements Collection_{
    Node head = null;
    Node tail = null;
    private int size = 0;
    @Override
    public void add(Object o){
        Node node = new Node(o);
        node.next = null;
        if (head == null){
            head = node;
            tail = node;
        }
        tail.next = node;
        tail =node;
        size++;

    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator_ iterator() {
        return new LinkedListIterator();
    }


    private class Node{
        private Object o;
        Node next;

        public Node(Object o) {
            this.o = o;
        }
    }

    private class LinkedListIterator implements Iterator_{
        Object temp;
        @Override
        public boolean hasNext() {
            if (head != null) {
               return true;
            } else if (head == null) {
                return false;
            }
            return false;
        }

        @Override
        public Object next() {
            if (head!=null){
                temp = head.o;
                head = head.next;
                return temp;
            }
            return null;
        }
    }
}