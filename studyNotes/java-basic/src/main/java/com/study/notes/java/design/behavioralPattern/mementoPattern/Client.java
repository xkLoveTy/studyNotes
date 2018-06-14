package com.study.notes.java.design.behavioralPattern.mementoPattern;

/**
 * Created by null on 1/6/16.
 */
public class Client {
    public static void main(String[] args) {
        GameRole lixiaoyao = new GameRole();
        lixiaoyao.getInitState();
        lixiaoyao.stateDisplay();

        RoleStateCaretaker stateAdmin = new RoleStateCaretaker();
        stateAdmin.setMemento(lixiaoyao.saveState());

        lixiaoyao.fight();
        lixiaoyao.stateDisplay();

        lixiaoyao.recoveryState(stateAdmin.getMemento());
        lixiaoyao.stateDisplay();

        /*Originator o = new Originator();
        o.setState("On");
        o.show();

        Caretaker c = new Caretaker();
        c.setMemento(o.CreateMemento());

        o.setState("off");
        o.show();

        o.setMemento(c.getMemento());
        o.show();*/


    }
}
