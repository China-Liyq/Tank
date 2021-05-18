package com.chinaliyq.designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/17 - 9:49
 * @Version: 1.0
 **/
public class Main {
    public static void main(String[] args) {
        BranchNode root = new BranchNode("root");
        BranchNode chapter1 = new BranchNode("chapter1");
        BranchNode chapter2 = new BranchNode("chapter2");
        Node c11 = new LeafNode("c11");
        Node c12 = new LeafNode("c12");
        BranchNode section21 = new BranchNode("section21");
        Node c211 = new LeafNode("c211");
        Node c212 = new LeafNode("c212");
        root.add(chapter1);
        root.add(chapter2);
        chapter1.add(c11);
        chapter1.add(c12);
        chapter2.add(section21);
        section21.add(c211);
        section21.add(c212);
        tree(root);
    }

    static void tree(Node b){
        b.p();
        if (b instanceof BranchNode){
            List<Node> nodes = ((BranchNode)b).nodes;
            for (Node n : nodes){
                tree(n);
            }
        }
    }

}


abstract class Node{
    public abstract void p();
}

class LeafNode extends Node{
    String content;

    public LeafNode(String content) {
        this.content = content;
    }

    @Override
    public void p() {
        System.out.println(content);
    }
}
class BranchNode extends Node{
    List<Node> nodes = new ArrayList<>();
    String name;

    public BranchNode(String name) {
        this.name = name;
    }

    public void add(Node n){
        nodes.add(n);
    }
    @Override
    public void p() {
        System.out.println(name);
    }
}