package com.study.notes.java.concurrency.excutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiangke on 2016/6/16.
 */
public class RunnableTest {

    public static void main(String[] args) {
        final int THREAD_COUNT = 3;
        final int LOOP_COUNT = 3;
        final int TASK_COUNT = 5;

        ExecutorService exec = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 1; i <= TASK_COUNT; i++) {
            RunnableTask task = new RunnableTask(i, LOOP_COUNT);
            exec.submit(task);
        }

        exec.shutdown();

    }

    static class RunnableTask implements Runnable {
        private int taskId;
        private int loopCounter;

        public RunnableTask(int taskId, int loopCounter) {
            this.taskId = taskId;
            this.loopCounter = loopCounter;
        }

        public void run() {
            for  (int i = 1; i <= loopCounter; i++) {
                try {
                    System.out.println("Task #" + this.taskId + "  - Iteration #" + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Task #" + this.taskId
                            + "  has  been  interrupted.");
                    break;
                }
            }
        }
    }
}
