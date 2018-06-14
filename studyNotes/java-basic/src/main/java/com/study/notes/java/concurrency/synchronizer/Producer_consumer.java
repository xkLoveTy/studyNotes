package com.study.notes.java.concurrency.synchronizer;



import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiangke on 2016/6/15.
 */
public class Producer_consumer {
    public static void main(String[] args) {
        Storage storage = new Storage();

        ExecutorService executor = Executors.newFixedThreadPool(8);

        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);
        Producer p5 = new Producer(storage);

        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);




        p1.setNum(10);
        p2.setNum(20);
        p3.setNum(30);
        p4.setNum(40);
        p5.setNum(50);

        c1.setNum(30);
        c2.setNum(40);
        c3.setNum(80);

        executor.submit(c1);
        executor.submit(c2);
        executor.submit(c3);

        executor.submit(p1);
        executor.submit(p2);
        executor.submit(p3);
        executor.submit(p4);
        executor.submit(p5);

        executor.shutdown();

    }


    static class Storage {
        private final int MAX_SIZE = 100;
        private LinkedList<Object> list = new LinkedList<Object>();

        public void produce(int num) {
            synchronized (list) {
                while (list.size() + num > MAX_SIZE) {
                    System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:"
                            + list.size() + "/t暂时不能执行生产任务!");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 1; i <= num; i++) {
                    list.add(new Object());
                }
                System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());

                list.notifyAll();
            }
        }

        public void Consume(int num) {
            synchronized (list) {
                while (list.size() < num) {
                    System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:"
                            + list.size() + "/t暂时不能执行生产任务!");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 1; i <= num; i++) {
                    list.remove();
                }
                System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + list.size());

                list.notifyAll();
            }
        }
    }

    static class Producer implements Runnable {
        private int num;
        private Storage storage;

        public Producer(Storage storage) {
            this.storage = storage;
        }

        public void run() {
            storage.produce(num);

        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    static class Consumer implements Runnable {
        private int num;
        private Storage storage;

        public Consumer(Storage storage) {
            this.storage = storage;
        }

        public void run() {
            storage.Consume(num);
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

}
