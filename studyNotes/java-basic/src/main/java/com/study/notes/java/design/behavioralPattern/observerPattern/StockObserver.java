package com.study.notes.java.design.behavioralPattern.observerPattern;

/**
 * Created by null on 1/6/16.
 */
public class StockObserver extends Observer {
    public StockObserver(String name, Subject subject) {
        super(name, subject);
    }


    public void update() {
        System.out.printf("%s关闭股票行情， 继续工作！", name);
    }
}
