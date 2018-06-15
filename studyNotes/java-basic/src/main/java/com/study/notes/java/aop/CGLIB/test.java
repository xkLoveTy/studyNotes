package com.study.notes.java.aop.CGLIB;

import com.study.notes.java.aop.LoggerOperation;

/**
 * Created by xiangke on 2016/7/6.
 */
public class test {
    public static void main(String[] args) {
        ServiceNotInterface serviceNotInterface = (ServiceNotInterface) new CglibProxy().bind(new ServiceNotInterface(), new LoggerOperation());
        serviceNotInterface.save();
    }
}
