package com.study.notes.java.aop;

import java.lang.reflect.Method;

/**
 * Created by xiangke on 2016/7/6.
 */
public class LoggerOperation implements IOperation {
    @Override
    public void before(Method method) {
        System.out.println("Before: " + method.getName());
    }

    @Override
    public void after(Method method) {
        System.out.println("After: " + method.getName());
    }
}
