package com.study.notes.java.design.creationalPattern.factoryPattern.simpleFactoryPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public abstract class Operation {
    private double numberA;
    private double numberB;


    public double getNumberA() {
        return numberA;
    }

    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }

    public double getNumberB() {
        return numberB;
    }

    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }

    abstract protected  double getResult();
}
