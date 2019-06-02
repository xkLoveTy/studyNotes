package com.study.notes.java.concurrency.myThreadPool;

/**
 * @ClassName ThreadFactory
 * @Description 提供创建线程的接口 来个性化地定制Thread
 * @Author xiangke
 * @Date 2019/6/2 12:22
 * @Version 1.0
 **/
@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
