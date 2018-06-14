package com.study.notes.java.design.structuralPattern.decoratorPattern;

/**
 * Created by null on 29/5/16.
 */
public class Finery extends Person {

    protected  Person component;

    public void decorate(Person component) {
        this.component = component;
    }

    public void show() {
        if (component != null) {
            component.show();
        }
    }
}
