package com.study.notes.java.design.behavioralPattern.chainOfResponsibilityPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public abstract class Manager {

    protected Manager superior;

    public void setSuperior(Manager superior) {
        this.superior = superior;
    }

    abstract void requestApplications(Request request);
}
