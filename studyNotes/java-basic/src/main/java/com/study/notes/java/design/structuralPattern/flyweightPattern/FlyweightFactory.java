package com.study.notes.java.design.structuralPattern.flyweightPattern;

import java.util.HashMap;

/**
 * Created by null on 29/5/16.
 */
public class FlyweightFactory {

    private HashMap flyweights = new HashMap();

    public FlyweightFactory() {
        flyweights.put("X", new ConcreteFlyweight());
        flyweights.put("Y", new ConcreteFlyweight());
        flyweights.put("Z", new ConcreteFlyweight());
    }

    public Flyweight getFlyweight(String key) {
        if (flyweights.containsKey(key)) {
            flyweights.put(key, new ConcreteFlyweight());
        }
        return (Flyweight) flyweights.get(key);
    }
}
