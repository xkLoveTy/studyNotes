package com.study.notes.java.design.behavioralPattern.mementoPattern;

/**
 * Created by null on 1/6/16.
 */
public class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento CreateMemento() {
        return new Memento(state);
    }

    public void setMemento(Memento memento) {
        this.state = memento.getState();
    }

    public void show() {
        System.out.printf("State= %s\n", state);
    }

}
