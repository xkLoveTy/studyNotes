package com.study.notes.java.design.behavioralPattern.observerPattern;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by null on 1/6/16.
 */
public class Boss implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void Notify() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
