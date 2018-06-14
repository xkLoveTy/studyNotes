package com.study.notes.java.design.structuralPattern.flyweightPattern;

/**
 * Created by null on 29/5/16.
 */
public class ConcreteWebSite implements WebSite {
    protected String name;

    public ConcreteWebSite(String name) {
        this.name = name;
    }

    public void use(User user) {
        System.out.println("网站分类: " + name + "用户： "+ user.name);
    }
}
