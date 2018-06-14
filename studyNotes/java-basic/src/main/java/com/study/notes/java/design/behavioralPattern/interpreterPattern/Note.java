package com.study.notes.java.design.behavioralPattern.interpreterPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public class Note extends Expression {

    public void execute(char key, double value) {
        String note = "";
        switch (key) {
            case 'C':
                note = "1";
                break;
            case 'D':
                note = "2";
                break;
            case 'E':
                note = "3";
                break;
            case 'F':
                note = "4";
                break;
            case 'G':
                note = "5";
                break;
            case 'A':
                note = "6";
                break;
            case 'B':
                note = "7";
                break;
        }
    }
}
