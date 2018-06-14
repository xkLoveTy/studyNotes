package com.study.notes.java.design.creationalPattern.builderPattern;

/**
 * Created by xiangke on 2016/5/25.
 */
public class PersonDirector {
    private PersonBuilder p;

    public PersonDirector(PersonBuilder p) {
        this.p = p;
    }

    public void createPerson() {
        p.BuildHead();
        p.BuildBody();
        p.BuildArmLeft();
        p.BuildArmRight();
        p.BuildLegLeft();
        p.BuildLegRight();
    }
}
