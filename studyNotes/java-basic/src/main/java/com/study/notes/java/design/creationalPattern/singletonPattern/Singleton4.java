package com.study.notes.java.design.creationalPattern.singletonPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public class Singleton4 {
    private volatile static Singleton4 instance;

    private Singleton4() {}

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }

        return instance;
    }

}
