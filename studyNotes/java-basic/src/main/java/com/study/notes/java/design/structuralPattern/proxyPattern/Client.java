package com.study.notes.java.design.structuralPattern.proxyPattern;

/**
 * Created by null on 29/5/16.
 */
public class Client {
    public static void main(String[] args) {
        SchoolGirl jiaojiao = new SchoolGirl();
        jiaojiao.setName("李娇娇");

        Proxy daili = new Proxy(jiaojiao);
        daili.giveDolls();
        daili.giveFlowers();
        daili.giveChocolate();

        ProxyS p = new ProxyS();
        p.request();

    }

}
