package com.study.notes.java.concurrency.myThreadPool;

import java.util.LinkedList;

/**
 * @ClassName LinkedRunnableQueue
 * @Description 任务队列
 * @Author xiangke
 * @Date 2019/6/2 12:55
 * @Version 1.0
 **/
public class LinkedRunnableQueue implements RunnableQueue {

    /**
     * 任务队列中最大容量
     */
    private final int limit;

    /**
     * 队列满时，拒绝策略
     */
    private final DenyPolicy denyPolicy;

    /**
     * 存放任务的队列
     */
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    /**
     * 线程池
     */
    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {

            //任务队列已经满了,执行拒绝策略
            if (runnableList.size() >= limit) {
                System.out.println("discard");
                denyPolicy.reject(runnable, threadPool);
                return;
            }

            //将任务加入队尾,并且唤醒阻塞中的线程
            runnableList.add(runnable);
            runnableList.notifyAll();
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList) {

            //如果任务队列中没有可执行的任务,则当前线程被挂起
            //进入runnableList关联的monitor,waitset中唤等待唤醒（新的任务加入）
            while (runnableList.isEmpty()) {
                runnableList.wait();
            }
        }

        return runnableList.removeFirst();
    }

    @Override
    public int size() {
        synchronized (runnableList) {
            return runnableList.size();
        }
    }
}
