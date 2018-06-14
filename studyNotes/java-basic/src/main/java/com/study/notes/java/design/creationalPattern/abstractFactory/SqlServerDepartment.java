package com.study.notes.java.design.creationalPattern.abstractFactory;

/**
 * Created by xiangke on 2016/5/25.
 */
public class SqlServerDepartment implements IDepartment {

    public void Insert() {
        System.out.println("在SQL server 给Department增加一条记录！");
    }


    public void getDepartMent() {
        System.out.println("在SQL server得到一条记录！");
    }
}
