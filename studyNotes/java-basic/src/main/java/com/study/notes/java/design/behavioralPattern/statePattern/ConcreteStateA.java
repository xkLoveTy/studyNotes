package com.study.notes.java.design.behavioralPattern.statePattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class ConcreteStateA implements State {

    public void handle(Context context) {
        context.setState(new ConcreteStateB());
    }
}
