package com.study.notes.java.design.behavioralPattern.commandPattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xiangke on 2016/5/30.
 */
public class Waiter {

    private List<Command> orders = new ArrayList<Command>();

    public void setOrder(Command command) {
        orders.add(command);
    }

    public void cancelOrder(Command command) {
        orders.remove(command);
        System.out.println("取消订单： " + "时间 ： " + new Date().toString());
    }

    public void Notify() {
        for (Command cmd : orders) {
            cmd.excuteCommand();
        }
    }
}
