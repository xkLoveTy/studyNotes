package com.study.notes.java.design.behavioralPattern.commandPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public abstract class Commands {
    protected Receiver receiver;

    public Commands(Receiver receiver) {
        this.receiver = receiver;
    }

    abstract void execute();

}
