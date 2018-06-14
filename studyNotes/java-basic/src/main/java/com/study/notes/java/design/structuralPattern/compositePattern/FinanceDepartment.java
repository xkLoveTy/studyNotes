package com.study.notes.java.design.structuralPattern.compositePattern;

/**
 * Created by null on 29/5/16.
 */
public class FinanceDepartment extends Company {
    public FinanceDepartment(String name) {
        super(name);
    }


    public void add(Company c) {

    }


    public void remove(Company c) {

    }


    public void display(int depth) {
        System.out.printf("-" + depth + name + "\n");
    }


    public void lineOfDuty() {
        System.out.printf("%s 公司财务收支管理\n", name);
    }
}
