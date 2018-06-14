package com.study.notes.java.design.creationalPattern.simpleFactoryPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public class Client {
    public static void main(String[] args) {
        OperationFactory factory = new OperationFactory();

        Operation oper = factory.createOperate("+");
        oper.setNumberA(2.0);
        oper.setNumberB(3.0);
        double result = oper.getResult();

        System.out.println(result);
    }
}
