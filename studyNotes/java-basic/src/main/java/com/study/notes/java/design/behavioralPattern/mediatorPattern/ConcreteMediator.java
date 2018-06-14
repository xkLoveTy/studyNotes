package com.study.notes.java.design.behavioralPattern.mediatorPattern;

/**
 * Created by xiangke on 2016/6/1.
 */
public class ConcreteMediator implements Mediator {

    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    public ConcreteColleague1 getColleague1() {
        return colleague1;
    }

    public void setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public ConcreteColleague2 getColleague2() {
        return colleague2;
    }

    public void setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
    }


    public void send(String message, Colleague colleage) {
        if (colleage instanceof ConcreteColleague1) {
            colleague1.Notify(message);
        } else {
            colleague2.Notify(message);
        }
    }
}
