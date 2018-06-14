package com.study.notes.java.design.creationalPattern.builderPattern;

import com.sun.prism.Graphics;

/**
 * Created by xiangke on 2016/5/25.
 */
abstract class PersonBuilder {

    protected Graphics g;
    protected Pen p;

    public PersonBuilder(Graphics g, Pen p) {
        this.g = g;
        this.p = p;
    }

    public abstract void BuildHead();
    public abstract void BuildBody();
    public abstract void BuildArmLeft();
    public abstract void BuildArmRight();
    public abstract void BuildLegLeft();
    public abstract void BuildLegRight();
}
