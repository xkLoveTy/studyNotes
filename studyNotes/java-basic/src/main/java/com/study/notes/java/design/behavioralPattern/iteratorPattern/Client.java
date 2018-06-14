package com.study.notes.java.design.behavioralPattern.iteratorPattern;

/**
 * Created by xiangke on 2016/6/1.
 */
public class Client {
    public static void main(String[] args) {
        ConcreteAggregate a = new ConcreteAggregate();
        a.setItem(0, "大鸟");
        a.setItem(1, "小菜");
        a.setItem(2, "行李");
        a.setItem(3, "老外");
        a.setItem(4, "小偷");

//        Iterator i = new ConcreteIterator(a);//正序

        Iterator i = new ConcreteIteratorDesc(a);//逆序

        while( !i.isDone() ) {
            System.out.printf("%s 请买票\n",i.currentItem());
            i.Next();
        }

    }
}
