package com.study.notes.java.concurrency.excutor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiangke on 2016/6/16.
 */
public class ScheduledTaskTest {

    public static void main(String[] args) {
        ScheduledExecutorService sexc = Executors.newScheduledThreadPool(3);

        ScheduledTask task1 = new ScheduledTask(1);
        ScheduledTask task2 = new ScheduledTask(2);
// Task #1 will run after 2 seconds
        sexc.schedule(task1, 2, TimeUnit.SECONDS);
// Task #2 runs after 5 seconds delay and keep running every 10 seconds
        sexc.scheduleAtFixedRate(task2, 5, 10, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ScheduledTask implements Runnable {
        private int taskId;

        public ScheduledTask(int taskId) {
            this.taskId = taskId;
        }

        public void run() {
            System.out.println("Task #" + this.taskId + "  ran  at "
                    + new Date().toString());
        }
    }
}
