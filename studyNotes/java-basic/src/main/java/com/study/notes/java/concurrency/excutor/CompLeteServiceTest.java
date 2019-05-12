package com.study.notes.java.concurrency.excutor;

import java.util.concurrent.*;

/**
 * Created by xiangke on 2016/6/16.
 */
public class CompLeteServiceTest {

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(3);

        // Completed task returns an object of the TaskResult class
        ExecutorCompletionService<MyResult> completionService = new ExecutorCompletionService<MyResult>(exec);


        for (int i = 1; i <=5; i++) {
            SleepingTask task = new SleepingTask(i, 3);
            completionService.submit(task);
        }

        for (int i = 1; i <= 5; i++) {
            Future<MyResult> future = completionService.take();
            MyResult result = future.get();
            System.out.println("Completed a  task - " + result);
        }

        exec.shutdown();
    }


    static class MyResult {
        private int taskId;
        private int result;

        public MyResult(int taskId, int result) {
            this.taskId = taskId;
            this.result = result;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "Task  Name: Task  #" + taskId + ", Task  Result:" + result
                    + "  seconds";
        }
    }

    static class SleepingTask implements Callable<MyResult> {
        private int taskId;
        private int loopCounter;

        public SleepingTask(int taskId, int loopCounter) {
            this.taskId = taskId;
            this.loopCounter = loopCounter;
        }

        public MyResult call() throws Exception {
            int totalSleepTime = 0;
            for (int i = 1; i <= loopCounter; i++) {
                try {
                    Thread.sleep(1500);
                    totalSleepTime += 100;
                } catch (Exception e) {
                    System.out.println("Task #" + this.taskId
                            + "  has  been  interupted.");
                    throw e;
                }
            }
            return new MyResult(taskId, totalSleepTime);
        }
    }
}
