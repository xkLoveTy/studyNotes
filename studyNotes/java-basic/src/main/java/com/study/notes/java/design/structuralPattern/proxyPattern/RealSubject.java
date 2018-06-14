package com.study.notes.java.design.structuralPattern.proxyPattern;

/**
 * Created by null on 29/5/16.
 */
public class RealSubject implements Subject {
    public void request() {
        System.out.println("真实的请求");
    }
}
