package com.study.notes.java.design.behavioralPattern.interpreterPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangke on 2016/5/30.
 */
public class Client {
    public static void main(String[] args) {

        Context context = new Context();
        List<AbstractExpression> list = new ArrayList<AbstractExpression>();
        list.add(new TerminalExpression());
        list.add(new NonTerminalExpression());

        for (AbstractExpression ae : list) {
            ae.interpret(context);
        }

        /*PlayContext context = new PlayContext();
        System.out.println("上海滩：");

        context.setPlayText("O 2 E 0.5 A 3 E 0.5 G 0.5 D 3");

        Expression expression = null;

        try {
            while (context.getPlayText().length() > 0) {
                char str = context.getPlayText().substring(0, 1).charAt(0);
                switch (str) {
                    case 'O' :
                        expression = new Scale();
                        break;
                    case 'C' :
                    case 'D' :
                    case 'E' :
                    case 'F' :
                    case 'G' :
                    case 'A' :
                    case 'B' :
                    case 'P' :
                        expression = new Note();
                        break;
                }

                expression.interpret(context);
            }
        }
        catch (Exception e) {

        }*/
    }
}
