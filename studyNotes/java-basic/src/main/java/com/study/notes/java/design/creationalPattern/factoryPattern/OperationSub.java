package com.study.notes.java.design.creationalPattern.factoryPattern;


/**
 * Created by xiangke on 2016/5/25.
 */
public class OperationSub extends Operation {

    public double getResult() {
        return getNumberA() - getNumberB();
    }
}
