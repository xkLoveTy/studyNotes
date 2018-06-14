package com.study.notes.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiangke on 2016/6/16.
 */
public class Threadlocaltest {
    public final static ThreadLocal threadLocal = new ThreadLocal();

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            Threadlocaltest.threadLocal.set((int) (Math.random() * 100D));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Threadlocaltest.threadLocal.get());
        }
    }

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(runnable);
        exec.execute(runnable);

        exec.shutdown();
    }

}
