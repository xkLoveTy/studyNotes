package com.study.notes.java.concurrency.myCountDownLatch;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MyCountDownLatch
 * @Description TODO
 * @Author xiangke
 * @Date 2019/6/2 23:41
 * @Version 1.0
 **/
public class MyCountDownLatch {

    /**
     * 计数器
     */
    private AtomicInteger counter;

    /**
     * 通知对象
     */
    private Object notify;

    private Notify notifyListen;

    public MyCountDownLatch(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("counter < 0");
        }

        counter = new AtomicInteger(number);
        notify = new Object();
    }

    public void setNotify(Notify notify) {
        this.notifyListen = notify;
    }

    public void countDown() {
        if (counter.get() <= 0) {
            return;
        }

        int count = counter.decrementAndGet();
        if (count < 0){
            throw new RuntimeException("concurrent error") ;
        }

        if (count == 0) {
            synchronized (notify) {
                notify.notify();
            }
        }
    }

    public void await() throws InterruptedException {
        synchronized (notify) {
            while (counter.get() > 0) {
                notify.wait();
            }

            if (notifyListen != null) {
                notifyListen.notifyListen();
            }
        }
    }


}
