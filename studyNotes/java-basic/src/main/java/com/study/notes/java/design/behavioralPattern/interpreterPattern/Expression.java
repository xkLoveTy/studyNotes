package com.study.notes.java.design.behavioralPattern.interpreterPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public abstract class Expression {
    public void interpret(PlayContext context) {
        if (context.getPlayText().length() == 0) {
            return;
        } else {
            char playKey = context.getPlayText().substring(0, 1).charAt(0);
            context.setPlayText(context.getPlayText().substring(2));

            double playValue = Double.parseDouble(context.getPlayText()
                    .substring(0, context.getPlayText().indexOf(" ")));

            context.setPlayText(context.getPlayText().
                    substring(context.getPlayText().indexOf(" ") + 1));

            execute(playKey, playValue);

        }
    }

    public abstract void execute(char key, double value);
}
