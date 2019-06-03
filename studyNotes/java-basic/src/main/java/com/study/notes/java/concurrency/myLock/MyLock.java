package com.study.notes.java.concurrency.myLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName MyLock
 * @Description TODO
 * @Author xiangke
 * @Date 2019/6/3 22:08
 * @Version 1.0
 **/
public class MyLock implements Lock {

    //声明一个判断锁的布尔值
    private volatile boolean isLocked = false;

    Thread lockBy = null;

    int lockCount = 0;

    @Override
    public synchronized void lock() {

        Thread currentThread = Thread.currentThread();

        while (isLocked && currentThread != lockBy) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isLocked = true;
        lockBy = currentThread;

        lockCount++;

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {

        if (lockBy == Thread.currentThread()) {
            lockCount--;

            if (lockCount == 0) {
                notify();
                isLocked = false;
            }
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
