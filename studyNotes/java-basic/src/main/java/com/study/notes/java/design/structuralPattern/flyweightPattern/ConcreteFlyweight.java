package com.study.notes.java.design.structuralPattern.flyweightPattern;

/**
 * Created by null on 29/5/16.
 */
public class ConcreteFlyweight implements Flyweight {
    public void operation(int extrinsicstate) {
        System.out.println("具体Flyweight：" + extrinsicstate);
    }
}
