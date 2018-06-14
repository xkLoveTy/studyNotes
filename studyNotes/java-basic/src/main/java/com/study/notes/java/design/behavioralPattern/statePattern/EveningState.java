package com.study.notes.java.design.behavioralPattern.statePattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class EveningState implements States {

    public void writeProgram(Work w) {
        if (w.getFinish()) {
            w.setState(new RestState());
            w.writeProgram();
        } else {
            if (w.getHour() < 21) {
                System.out.printf("当前时间： %f 点加班额， 疲惫至极\n", w.getHour());
            } else {
                w.setState(new SleepingState());
                w.writeProgram();
            }
        }
    }
}
