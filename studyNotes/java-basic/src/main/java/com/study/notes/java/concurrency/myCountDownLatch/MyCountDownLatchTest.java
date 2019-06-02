package com.study.notes.java.concurrency.myCountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyCountDownLatchTest
 * @Description TODO
 * @Author xiangke
 * @Date 2019/6/2 23:54
 * @Version 1.0
 **/
public class MyCountDownLatchTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyCountDownLatchTest.class) ;


    public static void main(String[] args) throws InterruptedException {
        MyCountDownLatch myCountDownLatch = new MyCountDownLatch(3);
        myCountDownLatch.setNotify(() ->
                        System.out.println("三个线程完成了任务"));

        Thread t1= new Thread(() -> {
            try {
                //TimeUnit.SECONDS.sleep(5);
                System.out.println("t1...");
                myCountDownLatch.countDown();
            } catch (Exception e) {
            }
        });
        Thread t2= new Thread(() -> {
            try {
                //TimeUnit.SECONDS.sleep(3);
                System.out.println("t2...");
                myCountDownLatch.countDown();
            } catch (Exception e) {
            }
        });
        Thread t3= new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("t3...");
                LOGGER.info("t3...");
                myCountDownLatch.countDown();
            } catch (Exception e) {
            }
        });

        t1.start();
        t2.start();
        t3.start();

        myCountDownLatch.await();
        System.out.println("======================");
        LOGGER.info("======================");
    }
}
