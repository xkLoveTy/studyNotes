package com.study.notes.java.concurrency.synchronizer;

import java.util.concurrent.Semaphore;

/**
 * Created by xiangke on 2016/6/15.
 */
public class TestSemaphore {
    public static void main(String[] args) {
        int N = 8;
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 8; i++) {
            new Thread(new Worker(i, semaphore)).start();
        }
    }

    static class Worker implements Runnable {
        private Semaphore semaphore;
        private int num;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
