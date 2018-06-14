package com.study.notes.java.design.behavioralPattern.mediatorPattern;

/**
 * Created by xiangke on 2016/6/1.
 */
public class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.send(message, this);
    }


    public void Notify(String message) {
        System.out.printf("同事1得到消息：%s\n", message);
    }
}
