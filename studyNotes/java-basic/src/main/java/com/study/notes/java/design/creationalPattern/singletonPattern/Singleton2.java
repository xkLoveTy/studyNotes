package com.study.notes.java.design.creationalPattern.singletonPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public class Singleton2 {
    private static Singleton2 instance = new Singleton2();

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return instance;
    }

}
