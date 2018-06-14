package com.study.notes.java.design.structuralPattern.decoratorPattern;

/**
 * Created by null on 29/5/16.
 */
public class ConcreteDecoratorB extends Decorator {


    public void operation() {
        super.operation();
        addBehavior();
        System.out.println("装饰对象B的操作");
    }

    private void addBehavior() {}
}
