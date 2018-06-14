package com.study.notes.java.design.behavioralPattern.interpreterPattern;

/**
 * Created by xiangke on 2016/6/1.
 */
public class TerminalExpression implements AbstractExpression {

    public void interpret(Context context) {
        System.out.println("终端解释器");
    }
}
