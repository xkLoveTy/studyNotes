package com.study.notes.java.concurrency.myLock;

/**
 * @ClassName MyLockTest2
 * @Description TODO
 * @Author xiangke
 * @Date 2019/6/3 22:18
 * @Version 1.0
 **/
public class MyLockTest {
    public int value = 0;

    MyLock myLock = new MyLock();

    public void a(){
        myLock.lock();
        System.out.println("a");
        b();
        myLock.unlock();
    }

    public void b(){
        myLock.lock();
        System.out.println("b");
        myLock.unlock();
    }

    public static void main(String[] args) {

        MyLockTest task = new MyLockTest();
        new Thread(() -> task.a()).start();

        new Thread(() -> task.a()).start();
    }
}
