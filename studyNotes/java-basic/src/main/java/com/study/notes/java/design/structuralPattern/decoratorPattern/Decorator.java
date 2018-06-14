package com.study.notes.java.design.structuralPattern.decoratorPattern;

/**
 * Created by null on 29/5/16.
 */
public class Decorator extends Component {
    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }


    public void operation() {
        if (component != null) {
            component.operation();
        }
    }
}
