package com.study.notes.java.design.structuralPattern.adapterPattern;

/**
 * Created by null on 28/5/16.
 */
public class Client {
    public static void main(String[] args) {
        Target target = new Adaptor();
        target.request();
    }
}
