package com.study.notes.java.design.behavioralPattern.mementoPattern;

/**
 * Created by null on 1/6/16.
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
