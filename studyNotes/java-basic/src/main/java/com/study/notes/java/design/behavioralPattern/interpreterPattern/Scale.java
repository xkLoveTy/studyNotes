package com.study.notes.java.design.behavioralPattern.interpreterPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public class Scale extends Expression {

    public void execute(char key, double value) {
        String scale = "";
        switch ((int) value) {
            case 1 :
                scale = "低音";
                break;
            case 2 :
                scale = "中音";
                break;
            case 3 :
                scale = "高音";
                break;
        }
    }
}
