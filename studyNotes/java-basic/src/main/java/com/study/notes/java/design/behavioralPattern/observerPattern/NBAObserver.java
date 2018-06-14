package com.study.notes.java.design.behavioralPattern.observerPattern;

/**
 * Created by null on 1/6/16.
 */
public class NBAObserver extends Observer {
    public NBAObserver(String name, Subject subject) {
        super(name, subject);
    }


    public void update() {
        System.out.printf("%s 关闭Nba直播，继续工作", name);
    }
}
