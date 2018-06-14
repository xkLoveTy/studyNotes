package com.study.notes.java.design.creationalPattern.factoryPattern.simpleFactoryPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public class OperatitonMul extends Operation {

    protected double getResult() {
        return getNumberA() * getNumberB();
    }
}
