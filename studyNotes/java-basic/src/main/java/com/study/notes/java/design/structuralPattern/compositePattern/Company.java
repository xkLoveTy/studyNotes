package com.study.notes.java.design.structuralPattern.compositePattern;

/**
 * Created by null on 28/5/16.
 */
public abstract class Company {
    protected String name;

    public Company(String name) {
        this.name = name;
    }

    public abstract void add(Company c);
    public abstract void remove(Company c);
    public abstract void display(int depth);
    public abstract void lineOfDuty();

}
