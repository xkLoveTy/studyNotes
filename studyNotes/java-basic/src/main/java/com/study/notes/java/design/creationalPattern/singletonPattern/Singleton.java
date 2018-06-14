package com.study.notes.java.design.creationalPattern.singletonPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public class Singleton {
    private static Singleton instance;

    private Singleton() {};

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

}
