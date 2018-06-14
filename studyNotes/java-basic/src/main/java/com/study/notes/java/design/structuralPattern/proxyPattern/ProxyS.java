package com.study.notes.java.design.structuralPattern.proxyPattern;

/**
 * Created by null on 29/5/16.
 */
public class ProxyS implements Subject {
    RealSubject realSubject;

    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        realSubject.request();
    }
}
