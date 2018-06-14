package com.study.notes.java.design.behavioralPattern.mediatorPattern;

/**
 * Created by xiangke on 2016/6/1.
 */
public class ConcreteColleague2 extends Colleague {
    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.send(message, this);
    }


    public void Notify(String message) {
        System.out.printf("同事2得到消息：%s\n", message);
    }

}
