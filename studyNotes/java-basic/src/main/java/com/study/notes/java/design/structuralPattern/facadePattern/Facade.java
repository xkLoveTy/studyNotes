package com.study.notes.java.design.structuralPattern.facadePattern;

/**
 * Created by null on 29/5/16.
 */
public class Facade {
    SubSystemOne   one;
    SubSystemTwo   two;
    SubSystemThree three;

    public Facade() {
        one = new SubSystemOne();
        two = new SubSystemTwo();
        three = new SubSystemThree();
    }

    public void method() {
        one.methodOne();
        two.methodTwo();
        three.methodThree();
    }

}
