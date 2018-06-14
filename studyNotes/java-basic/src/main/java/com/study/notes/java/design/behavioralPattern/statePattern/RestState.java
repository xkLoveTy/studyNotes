package com.study.notes.java.design.behavioralPattern.statePattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class RestState implements States {

    public void writeProgram(Work w) {
        System.out.printf("当前时间： %f 点下班回家了\n", w.getHour());
    }
}
