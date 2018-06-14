package com.study.notes.java.design.behavioralPattern.visitorPattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class ConcreteElementA implements Element {

    public void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }

    public void operationA(){}
}
