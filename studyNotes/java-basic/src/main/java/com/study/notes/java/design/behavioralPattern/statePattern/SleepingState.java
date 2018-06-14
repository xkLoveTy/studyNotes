package com.study.notes.java.design.behavioralPattern.statePattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class SleepingState implements States {

    public void writeProgram(Work w) {
        System.out.printf("当前时间： %f 点不行了， 睡着了。\n", w.getHour());
    }
}
