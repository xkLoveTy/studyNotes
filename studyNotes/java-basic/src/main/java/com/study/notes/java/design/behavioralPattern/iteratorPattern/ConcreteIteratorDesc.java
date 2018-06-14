package com.study.notes.java.design.behavioralPattern.iteratorPattern;

/**
 * Created by xiangke on 2016/6/1.
 */
public class ConcreteIteratorDesc implements  Iterator {
    private ConcreteAggregate aggregate;

    private int current = 0;

    public ConcreteIteratorDesc(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
        current = aggregate.count() - 1;
    }


    public Object first() {
        return aggregate.getItem(aggregate.count() - 1);
    }


    public Object Next() {
        Object ret = null;
        current--;
        if (current >= 0) {
            ret = aggregate.getItem(current);
        }

        return ret;
    }


    public boolean isDone() {
        return current < 0 ? true : false;
    }


    public Object currentItem() {
        return aggregate.getItem(current);
    }
}
