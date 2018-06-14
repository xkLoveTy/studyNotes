package com.study.notes.java.design.creationalPattern.abstractFactory;

/**
 * Created by xiangke on 2016/5/25.
 */
public class AccessDepartment implements IDepartment {

    public void Insert() {
        System.out.println("在Access中 给Department增加一条记录！");
    }


    public void getDepartMent() {
        System.out.println("在Access中 得到一条记录！");
    }
}
