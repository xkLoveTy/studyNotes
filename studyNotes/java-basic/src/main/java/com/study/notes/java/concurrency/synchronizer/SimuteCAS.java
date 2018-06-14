package com.study.notes.java.concurrency.synchronizer;

/**
 * Created by xiangke on 2016/8/5.
 */
public class SimuteCAS {
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectValue) {
            value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectValue, int newValue) {
        return (expectValue == compareAndSwap(expectValue, newValue));
    }
}
