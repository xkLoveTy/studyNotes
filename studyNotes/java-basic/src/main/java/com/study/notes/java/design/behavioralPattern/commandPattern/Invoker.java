package com.study.notes.java.design.behavioralPattern.commandPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public class Invoker {
    private Commands command;

    public void setCommand(Commands command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }

}
