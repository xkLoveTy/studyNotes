package com.study.notes.java.concurrency.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by xiangke on 2016/6/15.
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);

        /*CyclicBarrier barrier  = new CyclicBarrier(N,new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程"+Thread.currentThread().getName());
            }
        });

        for(int i=0;i<N;i++) {
            if(i<N-1)
                new Thread(new Writer(barrier)).start();
            else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Thread(new Writer(barrier)).start();
            }
        }*/

        for(int i = 0; i < N; i++) {
            new Thread(new Writer(barrier)).start();
        }

        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        for(int i=0;i<N;i++) {
            new Thread(new Writer(barrier)).start();
        }
    }

    static class Writer implements Runnable {
        private CyclicBarrier barrier;
        public Writer(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");

            try {
                Thread.sleep(5000);
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                barrier.await();
                //barrier.await(2000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } /*catch (TimeoutException e) {
                e.printStackTrace();
            }*/

            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }

}
