package com.study.notes.java.design.structuralPattern.decoratorPattern;

/**
 * Created by null on 29/5/16.
 */
public class ConcreteDecoratorA extends Decorator {
    private String addedState;


    public void operation() {
        super.operation();
        addedState = "New State";
        System.out.println("具体装饰对象A的操作");
    }


}
