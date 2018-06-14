package com.study.notes.java.design.behavioralPattern.statePattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class AfternoonState implements States {

    public void writeProgram(Work w) {
        if (w.getHour() < 17) {
            System.out.printf("当前时间： %f点 下午状态不错， 继续努力\n", w.getHour());
        } else {
            w.setState(new EveningState());
            w.writeProgram();
        }
    }
}
