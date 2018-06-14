package com.study.notes.java.design.behavioralPattern.templateMethodPattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public abstract class AbstractClass {
    public abstract void primitiveOperation1();
    public abstract void primitiveOperation2();

    public void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
    }
}
