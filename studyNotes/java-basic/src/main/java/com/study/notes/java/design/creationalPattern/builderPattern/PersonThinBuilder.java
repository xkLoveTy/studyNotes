package com.study.notes.java.design.creationalPattern.builderPattern;

import com.sun.prism.Graphics;

/**
 * Created by xiangke on 2016/5/25.
 */
public class PersonThinBuilder extends PersonBuilder {
    public PersonThinBuilder(Graphics g, Pen p) {
        super(g, p);
    }


    public void BuildHead() {
        g.drawEllipse(50, 20, 30, 30);
    }


    public void BuildBody() {
        g.drawEllipse(50, 20, 30, 30);
    }


    public void BuildArmLeft() {
        g.drawEllipse(50, 20, 30, 30);
    }


    public void BuildArmRight() {
        g.drawEllipse(50, 20, 30, 30);
    }


    public void BuildLegLeft() {
        g.drawEllipse(50, 20, 30, 30);
    }


    public void BuildLegRight() {
        g.drawEllipse(50, 20, 30, 30);
    }
}
