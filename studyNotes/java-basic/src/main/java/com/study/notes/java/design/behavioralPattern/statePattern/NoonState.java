package com.study.notes.java.design.behavioralPattern.statePattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class NoonState implements States {

    public void writeProgram(Work w) {
        if (w.getHour() < 13) {
            System.out.printf("当前时间： %f点 饿了， 午饭： 犯困， 午休。\n", w.getHour());
        } else {
            w.setState(new AfternoonState());
            w.writeProgram();
        }
    }
}
