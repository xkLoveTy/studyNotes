package com.study.notes.java.aop;

import java.lang.reflect.Method;

/**
 * Created by xiangke on 2016/7/6.
 */
public interface IOperation {
    void before(Method method);
    void after(Method method);
}
