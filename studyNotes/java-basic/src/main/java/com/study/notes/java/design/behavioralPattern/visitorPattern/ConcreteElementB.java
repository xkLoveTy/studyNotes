package com.study.notes.java.design.behavioralPattern.visitorPattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class ConcreteElementB implements Element {

    public void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }

    public void operationB(){}
}
