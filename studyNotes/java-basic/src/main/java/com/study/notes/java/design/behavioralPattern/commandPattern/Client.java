package com.study.notes.java.design.behavioralPattern.commandPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public class Client {
    public static void main(String[] args) {
        Receiver r = new Receiver();
        Commands c  = new ConcreteCommand(r);
        Invoker i = new Invoker();

        i.setCommand(c);
        i.executeCommand();

        System.out.println();

        Barbecuer boy = new Barbecuer();
        Command bakeMuttonCommand1 = new BakeMuttonCommand();
        Command bakeMuttonCommand2 = new BakeMuttonCommand();
        Command bakeChickenWingCommand = new BakeChickenWingCommand();

        bakeMuttonCommand1.setBarbecuer(boy);
        bakeMuttonCommand2.setBarbecuer(boy);
        bakeChickenWingCommand.setBarbecuer(boy);

        Waiter girl = new Waiter();
        girl.setOrder(bakeMuttonCommand1);
        girl.setOrder(bakeMuttonCommand2);
        girl.setOrder(bakeChickenWingCommand);

        girl.Notify();

        girl.cancelOrder(bakeChickenWingCommand);
        girl.Notify();

    }
}
