package com.study.notes.java.design.behavioralPattern.statePattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class Work {
    private States current;

    private double hour;
    private boolean finish;

    public Work() {
        this.current = new ForenoonState();
    }


    public void setState(States s) {
        this.current = s;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    public boolean getFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public void writeProgram() {
        current.writeProgram(this);
    }

}
