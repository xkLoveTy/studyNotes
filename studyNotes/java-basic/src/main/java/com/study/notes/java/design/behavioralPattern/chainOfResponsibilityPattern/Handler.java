package com.study.notes.java.design.behavioralPattern.chainOfResponsibilityPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public abstract class Handler {
    protected Handler sucessor;

    public void setSucessor(Handler sucessor) {
        this.sucessor = sucessor;
    }

    abstract void handleRequest(int request);
}
