package com.study.notes.java.design.behavioralPattern.commandPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public class BakeChickenWingCommand extends Command {

    void excuteCommand() {
        receiver.bakeChickenWing();
    }
}
