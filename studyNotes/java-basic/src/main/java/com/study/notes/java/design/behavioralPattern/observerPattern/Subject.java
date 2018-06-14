package com.study.notes.java.design.behavioralPattern.observerPattern;

/**
 * Created by null on 1/6/16.
 */
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void Notify();
}
