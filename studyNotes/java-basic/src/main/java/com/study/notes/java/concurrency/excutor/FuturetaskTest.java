package com.study.notes.java.concurrency.excutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by xiangke on 2016/6/16.
 */
public class FuturetaskTest {

    public static void main(String[] args) {
        MyCallable callable1 = new MyCallable(1000);
        MyCallable callable2 = new MyCallable(5000);

        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);

        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(futureTask1);
        exec.execute(futureTask2);


        while (true) {
            try {
                if (futureTask1.isDone() && futureTask2.isDone()) {
                    System.out.println("Done");
                    //shut down executor service
                    exec.shutdown();
                    return;
                }
                if(!futureTask1.isDone()){
                    //wait indefinitely for future task to complete
                    System.out.println("FutureTask1 output="+futureTask1.get());
                }
                System.out.println("Waiting for FutureTask2 to complete");
                //String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                String s = futureTask2.get();
                if(s !=null){
                    System.out.println("FutureTask2 output="+s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    static class MyCallable  implements Callable<String>{
        private long waitTime;

        public MyCallable(long timeInMillis) {
            this.waitTime = timeInMillis;
        }

        public String call() throws Exception {
            Thread.sleep(waitTime);
            return Thread.currentThread().getName();
        }
    }
}
