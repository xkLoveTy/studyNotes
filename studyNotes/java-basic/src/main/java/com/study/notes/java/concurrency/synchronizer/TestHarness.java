package com.study.notes.java.concurrency.synchronizer;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xiangke on 2016/6/8.
 */
public class TestHarness {

    public static void main(String[] args) {
        long cnt = timetasks(59, () -> {
            int i = 0;
            i++;
        });
        long seconds = (cnt % (1000 * 60)) / 1000;
        System.out.println("cnt : " + seconds + " seconds");
    }


    public static long timetasks(int nThreads, final Runnable task) {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        long start = System.nanoTime();
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }

        try {
            startGate.countDown();
            endGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        return end -start;
    }
}
