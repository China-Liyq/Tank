package com.chinaliyq.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyq
 * @Description: 观察者
 * @Date: 2021/5/16 - 21:31
 * @Version: 1.0
 **/
public class Child {
    private boolean cry = false;
    private List<Observer> observers = new ArrayList<>();
    private List<String> strings;
    {
        observers.add(new Dad());
    }
    public boolean isCry(){
        return cry;
    }
    public void wakeUp(){
        cry = true;

        WakeUpEvent event = new WakeUpEvent(System.currentTimeMillis(), "bed", this);
        for (Observer o: observers) {
            o.actionOnWakeUp(event);
        }

    }
}

class WakeUpEvent {
    long timetamp;
    String loc;
    Child source;

    public WakeUpEvent(long timetamp, String loc, Child source) {
        this.timetamp = timetamp;
        this.loc = loc;
        this.source = source;
    }
}
interface Observer{
    void actionOnWakeUp(WakeUpEvent event);
}

class Dad implements Observer{
    public void feed(){
        System.out.println("dad feeding...");
    }
    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        feed();
    }
}
class Main{
    public static void main(String[] args) {
        new Child().wakeUp();
    }

}