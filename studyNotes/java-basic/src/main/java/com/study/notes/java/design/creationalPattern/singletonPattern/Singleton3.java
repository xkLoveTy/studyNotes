package com.study.notes.java.design.creationalPattern.singletonPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public class Singleton3 {
    private Singleton3() {}

    public static class HolderClass {
        private final static Singleton3 instance = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return HolderClass.instance;
    }

}
