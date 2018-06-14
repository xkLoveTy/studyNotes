package com.study.notes.java.design.behavioralPattern.iteratorPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangke on 2016/6/1.
 */
public class ConcreteAggregate implements  Aggregate{
    private List<Object> items = new ArrayList<Object>();


    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    public int count() {
        return items.size();
    }

    public Object getItem(int index) {
        return items.get(index);
    }

    public void setItem(int index, Object o) {
        items.add(index, o);
    }

}
