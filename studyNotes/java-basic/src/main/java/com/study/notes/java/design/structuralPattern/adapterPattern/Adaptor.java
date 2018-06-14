package com.study.notes.java.design.structuralPattern.adapterPattern;

/**
 * Created by null on 28/5/16.
 */
public class Adaptor extends Target {
    private Adaptee adaptee = new Adaptee();

    public void request() {
        adaptee.specificRequest();
    }

}
