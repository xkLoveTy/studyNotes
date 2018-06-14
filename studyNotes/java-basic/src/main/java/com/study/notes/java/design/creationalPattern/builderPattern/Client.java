package com.study.notes.java.design.creationalPattern.builderPattern;


import com.sun.prism.Graphics;

/**
 * Created by xiangke on 2016/5/25.
 */
public class Client {
    public static void main(String[] args) {
        Graphics g = null;
        Pen p = null;
        PersonBuilder pb = new PersonThinBuilder(g, p);
        PersonDirector pd = new PersonDirector(pb);
        pd.createPerson();

    }
}
