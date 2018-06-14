package com.study.notes.java.design.behavioralPattern.visitorPattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class Client {
    public static void main(String[] args) {
        ObjectStructure o = new ObjectStructure();
        o.attach(new ConcreteElementA());
        o.attach(new ConcreteElementB());

        ConcerteVistor1 vistor1 = new ConcerteVistor1();
        ConcerteVistor2 vistor2 = new ConcerteVistor2();

        o.accept(vistor1);
        o.accept(vistor2);
    }
}
