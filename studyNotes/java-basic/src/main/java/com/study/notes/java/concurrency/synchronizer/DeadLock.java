package com.study.notes.java.concurrency.synchronizer;

import java.util.Date;

/**
 * Created by xiangke on 2016/6/13.
 */
public class DeadLock {
    public static String obj1 = "A";
    public static String obj2 = "B";

    public static void main(String[] args) {
        new Thread(new LockA()).start();
        new Thread(new LockB()).start();
    }

    private static class LockA implements Runnable {
        public void run() {
            try {
                System.out.println(new Date().toString() + "LockA 开始执行");

                while (true) {
                    synchronized (obj1) {
                        System.out.println(new Date().toString() + "LockA 锁住A");
                        Thread.sleep(3000);
                        synchronized (obj2) {
                            System.out.println(new Date().toString() + "LockA 锁住B");
                            Thread.sleep(10 * 1000);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class LockB implements Runnable {
        public void run() {
            try {
                System.out.println(new Date().toString() + "LockB 开始执行");

                while (true) {
                    synchronized (obj2) {
                        System.out.println(new Date().toString() + "LockB 锁住B");
                            Thread.sleep(3000);
                        synchronized (obj1) {
                            System.out.println(new Date().toString() + "LockB 锁住A");
                            Thread.sleep(10 * 1000);
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
