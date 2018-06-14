package com.study.notes.java.design.behavioralPattern.visitorPattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class ConcerteVistor2 implements Visitor {

    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.printf("%s 被 %s 访问\n", concreteElementA.getClass().getName(),
                this.getClass().getName());
    }


    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.printf("%s 被 %s 访问\n", concreteElementB.getClass().getName(),
                this.getClass().getName());
    }
}
