package com.study.notes.java.design.creationalPattern.factoryPattern;


/**
 * Created by xiangke on 2016/5/25.
 */
public class OperationDivFactory implements OperationFactory {


    public Operation createOperation() {
        return new OperationDiv();
    }
}
