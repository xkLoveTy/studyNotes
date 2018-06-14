package com.study.notes.java.design.behavioralPattern.strategyPattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();

        context.setStrategy(new ConcreteStrategyA());
        context.contextInterface();

        context.setStrategy(new ConcreteStrategyB());
        context.contextInterface();

        context.setStrategy(new ConcreteStrategyC());
        context.contextInterface();
    }
}
