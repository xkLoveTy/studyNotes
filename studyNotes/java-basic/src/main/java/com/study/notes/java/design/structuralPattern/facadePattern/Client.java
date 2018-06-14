package com.study.notes.java.design.structuralPattern.facadePattern;

/**
 * Created by null on 29/5/16.
 */
public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.method();
    }
}
