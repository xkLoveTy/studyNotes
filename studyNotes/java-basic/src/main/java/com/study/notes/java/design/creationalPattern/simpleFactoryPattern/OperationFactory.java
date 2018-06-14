package com.study.notes.java.design.creationalPattern.simpleFactoryPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public class OperationFactory {
    public static Operation createOperate(String oper) {
        Operation operation = null;

        if ("+".equals(oper)) {
            operation = new OperationAdd();
        } else if ("-".equals(oper)) {
            operation = new OperationSub();
        } else if ("*".equals(oper)) {
            operation = new OperatitonMul();
        } else {
            operation = new OperationDiv();
        }

        return operation;
    }
}
