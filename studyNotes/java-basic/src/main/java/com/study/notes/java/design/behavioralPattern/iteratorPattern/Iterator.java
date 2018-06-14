package com.study.notes.java.design.behavioralPattern.iteratorPattern;

/**
 * Created by xiangke on 2016/6/1.
 */
public interface Iterator {
    Object first();
    Object Next();
    boolean isDone();
    Object currentItem();
}
