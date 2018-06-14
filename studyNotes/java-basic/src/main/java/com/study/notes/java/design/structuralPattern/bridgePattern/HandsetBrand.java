package com.study.notes.java.design.structuralPattern.bridgePattern;

/**
 * Created by null on 28/5/16.
 */
public abstract class HandsetBrand {
    protected HandSetSoft soft;

    public void setHandsetSoft(HandSetSoft soft) {
        this.soft = soft;
    }

    public abstract void run();
}
