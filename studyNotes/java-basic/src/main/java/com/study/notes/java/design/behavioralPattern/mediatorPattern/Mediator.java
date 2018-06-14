package com.study.notes.java.design.behavioralPattern.mediatorPattern;

/**
 * Created by xiangke on 2016/6/1.
 */
public interface Mediator {
    public void send(String message, Colleague colleage);
}
