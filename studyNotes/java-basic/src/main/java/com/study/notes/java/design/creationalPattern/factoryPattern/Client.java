package com.study.notes.java.design.creationalPattern.factoryPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public class Client {
    public static void main(String[] args) {
        OperationFactory factory = new OperationAddFactory();
        Operation operation = factory.createOperation();
        operation.setNumberA(3.0);
        operation.setNumberB(4.0);
        double result = operation.getResult();

        System.out.println(result);
    }
}
