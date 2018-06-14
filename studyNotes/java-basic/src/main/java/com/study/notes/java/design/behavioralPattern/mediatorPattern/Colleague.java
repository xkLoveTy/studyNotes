package com.study.notes.java.design.behavioralPattern.mediatorPattern;

/**
 * Created by xiangke on 2016/6/1.
 */
public abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    abstract void Notify(String message);

}
