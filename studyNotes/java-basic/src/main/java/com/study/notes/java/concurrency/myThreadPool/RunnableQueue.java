package com.study.notes.java.concurrency.myThreadPool;

/**
 * @ClassName RunnableQueue
 * @Description 任务队列
 * 主要用于缓存提交到线程池中的任务
 * @Author xiangke
 * @Date 2019/6/2 12:23
 * @Version 1.0
 **/
public interface RunnableQueue {
    //当有新任务进来时首先会offer到队列中
    void offer(Runnable runnable);

    //工作线程通过take方法获取Runnable
    Runnable take() throws InterruptedException;

    //获取任务队列中任务的数量
    int size();
}
