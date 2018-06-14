package com.study.notes.java.design.behavioralPattern.mementoPattern;

/**
 * Created by null on 1/6/16.
 */
public class RoleStateCaretaker {
    private RoleStateMemento memento;

    public RoleStateMemento getMemento() {
        return memento;
    }

    public void setMemento(RoleStateMemento memento) {
        this.memento = memento;
    }
}
