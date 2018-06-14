package com.study.notes.java.concurrency.synchronizer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiangke on 2016/8/5.
 */
public class TestCAS {
    private SimuteCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (!value.compareAndSet(v, v + 1));

        return v;
    }

    public static void main(String[] args) {
        final TestCAS cas = new TestCAS();

        ExecutorService exec = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            exec.execute(() -> cas.increment());
        }

    }

}
