package com.study.notes.java.design.behavioralPattern.commandPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public abstract class Command {
    protected Barbecuer receiver;

    public void setBarbecuer(Barbecuer receiver) {
        this.receiver = receiver;
    }

    abstract void excuteCommand();
}
