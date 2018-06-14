package com.study.notes.java.design.behavioralPattern.mementoPattern;

/**
 * Created by null on 1/6/16.
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
