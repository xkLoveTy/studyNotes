package com.study.notes.java.concurrency.excutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by xiangke on 2016/6/16.
 */
public class CallableTest {

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(5);

        CallableTask task = new CallableTask(1);
        Future<Integer> future = exec.submit(task);


        Integer result = future.get();

        System.out.println("Task's total  sleep time: " + result + "  seconds");
        exec.shutdown();

    }


    static class CallableTask implements Callable<Integer> {
        private int taskId;

        public CallableTask(int taskId) {
            this.taskId = taskId;
        }


        public Integer call() throws InterruptedException {
            int total = taskId;

            try {
                System.out.println("Task #" + this.taskId);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.out.println("Task #" + this.taskId
                        + "  has  been  interupted.");
                throw e;
            }

            total += taskId;
            return total;
        }
    }
}
