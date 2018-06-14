package com.study.notes.java.design.behavioralPattern.mementoPattern;

/**
 * Created by null on 1/6/16.
 */
public class GameRole {
    private int vit;
    private int atk;
    private int def;

    public RoleStateMemento saveState() {
        return new RoleStateMemento(vit, atk, def);
    }

    public void recoveryState(RoleStateMemento memento) {
        this.vit = memento.getVit();
        this.atk = memento.getAtk();
        this.def = memento.getDef();
    }

    public void stateDisplay() {
        System.out.println("角色当前状态: ");
        System.out.printf("体力： %d", this.vit);
        System.out.printf("攻击力： %d", this.atk);
        System.out.printf("防御： %d\n", this.def);
    }

    public void getInitState() {
        this.atk = 100;
        this.vit = 100;
        this.def = 100;
    }

    public void fight() {
        this.atk = 0;
        this.vit = 0;
        this.def = 0;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
