package com.study.notes.java.design.behavioralPattern.statePattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class ForenoonState implements States {

    public void writeProgram(Work w) {
        if (w.getHour() < 12) {
            System.out.printf("当前时间： %f点 上午工作，精神百倍\n", w.getHour());
        } else {
            w.setState(new NoonState());
            w.writeProgram();
        }
    }
}
