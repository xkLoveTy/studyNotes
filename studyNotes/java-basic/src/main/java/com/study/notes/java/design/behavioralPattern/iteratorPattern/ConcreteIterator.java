package com.study.notes.java.design.behavioralPattern.iteratorPattern;

/**
 * Created by xiangke on 2016/6/1.
 */
public class ConcreteIterator implements Iterator{

    private ConcreteAggregate aggregate;
    private int current = 0;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }


    public Object first() {
        return aggregate.getItem(0);
    }


    public Object Next() {
        Object ret = null;
        current++;

        if (current < aggregate.count()) {
            ret = aggregate.getItem(current);
        }
        return ret;
    }


    public boolean isDone() {
        return current >= aggregate.count() ? true : false;
    }


    public Object currentItem() {
        return aggregate.getItem(current);
    }
}
