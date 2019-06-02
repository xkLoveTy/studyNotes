package com.study.notes.java.concurrency.myThreadPool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName BasicThreadPool
 * @Description 线程池
 * @Author xiangke
 * @Date 2019/6/2 12:37
 * @Version 1.0
 **/
public class BasicThreadPool extends Thread implements ThreadPool {

    /**
     * 初始化线程数量
     **/
    private final int initSize;

    /**
     * 线程池最大线程数量
     **/
    private final int maxSize;

    /**
     * 线程池核心线程数量
     **/
    private final int coreSize;

    /**
     * 当前活跃的线程数量
     **/
    private int activeCount;

    /**
     * 创建线程所需要的工厂
     **/
    private final ThreadFactory threadFactory;

    /**
     * 任务队列
     **/
    private final RunnableQueue runnableQueue;

    /**
     * 线程池是否已被shutdown
     **/
    private final boolean isShutDown = false;

    /**
     * 工作线程队列
     */
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    /**
     * 拒绝策略
     */
    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    /**
     * 线程工厂
     */
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int coreSize, int maxSize, int queueSize) {
        this(initSize, coreSize, maxSize, DEFAULT_THREAD_FACTORY, queueSize,
                DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int coreSize, int maxSize,
                           ThreadFactory threadFactory, int queueSize,
                           DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init() {
        start();

        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    private void newThread() {

        //创建任务线程并启动
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);

        threadQueue.offer(threadTask);
        this.activeCount++;

        thread.start();
    }


    @Override
    public void execute(Runnable runnable) {
        if (this.isShutDown) {
            throw new IllegalArgumentException("The thread pool is destory");
        }

        //提交任务只是简单地往任务队列中插入Runnable
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutDown) {
                return;
            }

            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if (isShutDown) {
            throw new IllegalArgumentException("The thread pool is destory");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutDown) {
            throw new IllegalArgumentException("The thread pool is destory");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutDown) {
            throw new IllegalArgumentException("The thread pool is destory");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutDown) {
            throw new IllegalArgumentException("The thread pool is destory");
        }
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    private void removeThread() {
        //从线程池中移除线程
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    private static class ThreadTask {
        InternalTask internalTask;
        Thread thread;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

        private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndIncrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);


        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndIncrement());
        }
    }
}
