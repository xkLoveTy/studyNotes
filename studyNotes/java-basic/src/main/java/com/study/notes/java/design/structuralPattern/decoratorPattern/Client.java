package com.study.notes.java.design.structuralPattern.decoratorPattern;

/**
 * Created by null on 29/5/16.
 */
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();

        ConcreteDecoratorA ca = new ConcreteDecoratorA();
        ConcreteDecoratorB cb = new ConcreteDecoratorB();

        ca.setComponent(component);
        cb.setComponent(ca);
        cb.operation();



        Person p = new Person();

        TShirts t = new TShirts();
        BigTrouser b = new BigTrouser();

        t.decorate(p);
        b.decorate(t);
        b.show();
    }
}
