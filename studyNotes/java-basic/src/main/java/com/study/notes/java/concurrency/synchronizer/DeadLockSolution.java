package com.study.notes.java.concurrency.synchronizer;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiangke on 2016/6/13.
 */
public class DeadLockSolution {
    public static String obj1 = "A";
    public static String obj2 = "B";
    public static Semaphore a1 = new Semaphore(1);
    public static Semaphore a2 = new Semaphore(1);

    public static void main(String[] args) {
        new Thread(new LockA()).start();
        new Thread(new LockB()).start();
    }

    private static class LockA implements Runnable {
        public void run() {
            try {
                System.out.println(new Date().toString() + " LockA 开始执行");
                while (true) {
                    if (a1.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockA 锁住 obj1");
                        if (a2.tryAcquire(1, TimeUnit.SECONDS)) {
                            System.out.println(new Date().toString() + " LockA 锁住 obj2");
                            Thread.sleep(60 * 1000);
                        } else {
                            System.out.println(new Date().toString() + "LockA 锁 obj2 失败");
                        }
                    } else {
                        System.out.println(new Date().toString() + "LockA 锁 obj1 失败");
                    }
                    a1.release();
                    a2.release();
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static class LockB implements Runnable {
        public void run() {
            try {
                System.out.println(new Date().toString() + " LockB 开始执行");
                while (true) {
                    if (a2.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockB 锁住 obj2");
                        if (a1.tryAcquire(1, TimeUnit.SECONDS)) {
                            System.out.println(new Date().toString() + " LockB 锁住 obj1");
                            Thread.sleep(60 * 1000);
                        }else{
                            System.out.println(new Date().toString() + "LockB 锁 obj1 失败");
                        }
                    }else{
                        System.out.println(new Date().toString() + "LockB 锁 obj2 失败");
                    }
                    a1.release(); // 释放
                    a2.release();
                    Thread.sleep(10 * 1000); // 这里只是为了演示，所以tryAcquire只用1秒，
                    // 而且B要给A让出能执行的时间，否则两个永远是死锁
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
