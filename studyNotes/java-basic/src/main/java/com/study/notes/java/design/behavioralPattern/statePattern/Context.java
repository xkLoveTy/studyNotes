package com.study.notes.java.design.behavioralPattern.statePattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        System.out.printf("当前状态： %s\n", state.getClass().getName());
    }

    public void request() {
        state.handle(this);
    }
}
