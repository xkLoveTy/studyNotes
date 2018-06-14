package com.study.notes.java.design.structuralPattern.compositePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by null on 29/5/16.
 */
public class ConcreteCompany extends Company {
    private List<Company> children = new ArrayList<Company>();

    public ConcreteCompany(String name) {
        super(name);
    }


    public void add(Company c) {
        children.add(c);
    }


    public void remove(Company c) {
        children.remove(c);
    }


    public void display(int depth) {
        System.out.printf("-" + depth + name + "\n");
        for (Company c : children) {
            c.display(depth + 2);
        }
    }


    public void lineOfDuty() {
        for (Company c : children) {
            c.lineOfDuty();
        }
    }
}
