package com.study.notes.java.design.creationalPattern.factoryPattern.simpleFactoryPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public class OperationDiv extends Operation {

    protected double getResult() {

        double result = 0;
        try {
            result = getNumberA() / getNumberB();
        }
        catch(Throwable t) {
            System.out.println(t.getStackTrace());
        }

        return result;
    }
}
