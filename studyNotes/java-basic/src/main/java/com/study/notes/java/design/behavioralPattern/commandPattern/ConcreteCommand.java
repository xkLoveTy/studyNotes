package com.study.notes.java.design.behavioralPattern.commandPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public class ConcreteCommand extends Commands {

    public ConcreteCommand(Receiver receiver) {
        super(receiver);
    }


    void execute() {
        receiver.action();
    }
}
