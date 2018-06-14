package com.study.notes.java.design.structuralPattern.flyweightPattern;

/**
 * Created by null on 29/5/16.
 */
public class UnsharedConcreteFlyweight implements Flyweight {
    public void operation(int extrinsicstate) {
        System.out.println("不共享的具体Flyweight： " + extrinsicstate);
    }
}
