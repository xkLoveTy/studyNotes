package com.study.notes.java.design.behavioralPattern.visitorPattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public interface Visitor {
    public void visitConcreteElementA(ConcreteElementA concreteElementA);
    public void visitConcreteElementB(ConcreteElementB concreteElementB);
}
