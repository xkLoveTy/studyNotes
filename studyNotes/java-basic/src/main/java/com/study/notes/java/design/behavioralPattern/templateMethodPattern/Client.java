package com.study.notes.java.design.behavioralPattern.templateMethodPattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class Client {
    public static void main(String[] args) {
        AbstractClass a;

        a= new ConcreteClassA();
        a.templateMethod();

        a = new ConcreteClassB();
        a.templateMethod();
    }
}
