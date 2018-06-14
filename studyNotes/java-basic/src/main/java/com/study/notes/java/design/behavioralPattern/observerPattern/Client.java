package com.study.notes.java.design.behavioralPattern.observerPattern;

/**
 * Created by null on 1/6/16.
 */
public class Client {
    public static void main(String[] args) {
        Boss humansan = new Boss();

        StockObserver tongshi1 = new StockObserver("hhh", humansan);
        NBAObserver tongshi2 = new NBAObserver("eee", humansan);


        humansan.attach(tongshi1);
        humansan.attach(tongshi2);

        humansan.detach(tongshi1);

        humansan.Notify();

    }
}
