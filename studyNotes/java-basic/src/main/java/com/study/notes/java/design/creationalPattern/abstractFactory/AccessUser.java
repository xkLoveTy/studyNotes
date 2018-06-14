package com.study.notes.java.design.creationalPattern.abstractFactory;


/**
 * Created by xiangke on 2016/5/25.
 */
public class AccessUser implements IUser {

    public void Insert() {
        System.out.println("在Access 给User增加一条记录！");
    }


    public void getUser() {
        System.out.println("在Access 得到一条记录！");
    }
}
