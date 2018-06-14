package com.study.notes.java.design.behavioralPattern.observerPattern;

/**
 * Created by null on 1/6/16.
 */
public abstract class Observer {
    protected  String name;
    protected  Subject subject;

    public Observer(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public abstract void update();

}
