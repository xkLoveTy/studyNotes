package com.study.notes.java.aop;

/**
 * Created by xiangke on 2016/7/6.
 */
public class Service implements IService {
    @Override
    public int save() {
        System.out.println("*****save*****");
        return 0;
    }
}
