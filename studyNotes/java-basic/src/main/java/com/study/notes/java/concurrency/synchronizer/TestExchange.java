package com.study.notes.java.concurrency.synchronizer;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

/**
 * Created by xiangke on 2016/6/15.
 */
public class TestExchange {
    public static void main(String[] args) {
        Exchanger<ArrayList<Integer>> exchanger = new Exchanger<ArrayList<Integer>>();
        ExchangerProducer producer = new ExchangerProducer(exchanger);
        ExchangerConsumer consumer = new ExchangerConsumer(exchanger);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class ExchangerProducer implements Runnable {
        private Exchanger<ArrayList<Integer>> exchanger;
        private ArrayList<Integer> buffer = new ArrayList<Integer>();

        public ExchangerProducer(Exchanger<ArrayList<Integer>> exchanger) {
            this.exchanger = exchanger;
        }

        public void run() {
            try {
                System.out.println("Producer...");
                Thread.sleep(1000);
                fillBuffer();
                System.out.println("Producer has produced and waiting:" + buffer);
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void fillBuffer() {
            for (int i = 0; i <= 3; i++) {
                buffer.add(i);
            }
        }
    }

    static class ExchangerConsumer implements Runnable {
        private Exchanger<ArrayList<Integer>> exchanger;
        private ArrayList<Integer> buffer = new ArrayList<Integer>();

        public ExchangerConsumer(Exchanger<ArrayList<Integer>> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Consumer...");
                buffer = exchanger.exchange(buffer);
                System.out.println("Consumer  has received:" + buffer);
                Thread.sleep(1000);
                System.out.println("eating: " + buffer);
                buffer.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
