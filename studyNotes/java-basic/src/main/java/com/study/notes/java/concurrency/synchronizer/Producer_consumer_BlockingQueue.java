package com.study.notes.java.concurrency.synchronizer;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by xiangke on 2016/6/15.
 */
public class Producer_consumer_BlockingQueue {
    public static void main(String[] args) {
        Storage storage = new Storage();

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

        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();

        new Thread(p1).start();
        new Thread(p2).start();
        new Thread(p3).start();
        new Thread(p4).start();
        new Thread(p5).start();

    }


    static class Storage {
        private final int MAX_SIZE = 100;
        private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(100);

        public void produce(int num) {
            if (list.size() == MAX_SIZE) {
                System.out.println("【库存量】:" + MAX_SIZE + "/t暂时不能执行生产任务!");
            }

            for (int i = 0; i <= num; i++) {
                try {
                    list.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("【现仓储量为】:" + list.size());
            }
        }

        public void Consume(int num) {
            // 如果仓库存储量不足
            if (list.size() == 0)
            {
                System.out.println("【库存量】:0/t暂时不能执行生产任务!");
            }


            for (int i = 1; i <= num; i++) {
                try {
                    // 消费产品，自动阻塞
                    list.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("【现仓储量为】:" + list.size());
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
