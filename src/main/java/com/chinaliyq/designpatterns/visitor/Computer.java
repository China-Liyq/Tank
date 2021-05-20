package com.chinaliyq.designpatterns.visitor;

/**
 * @Author: liyq
 * @Description: Tank
 * @Date: 2021/5/18 - 16:29
 * @Version: 1.0
 **/
public class Computer {
    final Cpu cpu = new Cpu();
    final Memory memory = new Memory();
    final KeyBoard keyBoard = new KeyBoard();
    public static void main(String[] args) {
        final PersonVisitor personVisitor = new PersonVisitor();
        new Computer().accept(personVisitor);
        System.out.println(personVisitor.totalPrice);

    }

    public void accept(Visitor v){
        this.cpu.accept(v);
        this.memory.accept(v);
        this.keyBoard.accept(v);
    }
}

abstract class ComputerPart{
    abstract void accept(Visitor v);

    abstract double getPrice();
}
class Cpu extends ComputerPart {

    @Override
    void accept(Visitor v) {
        v.visitCpu(this);
    }
    @Override
    double getPrice() {
        return 400;
    }
}
class Memory extends ComputerPart{

    @Override
    void accept(Visitor v) {
        v.visitMemory(this);
    }

    @Override
    double getPrice() {
        return 1000;
    }
}
class KeyBoard extends ComputerPart{

    @Override
    void accept(Visitor v) {
        v.visitKeyBoard(this);
    }

    @Override
    double getPrice() {
        return 600;
    }
}

interface Visitor{
    void visitCpu(ComputerPart c);
    void visitMemory(ComputerPart c);
    void visitKeyBoard(ComputerPart c);
}

class PersonVisitor implements Visitor{
    double totalPrice = 0.0;
    @Override
    public void visitCpu(ComputerPart c) {
        totalPrice += c.getPrice()+0;
    }

    @Override
    public void visitMemory(ComputerPart c) {
        totalPrice += c.getPrice()+0;
    }

    @Override
    public void visitKeyBoard(ComputerPart c) {
        totalPrice += c.getPrice()+0;
    }
}