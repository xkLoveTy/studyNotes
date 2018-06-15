package com.study.notes.java.aop.JDKDynamicProxy;

import com.study.notes.java.aop.IService;
import com.study.notes.java.aop.LoggerOperation;
import com.study.notes.java.aop.Service;

/**
 * Created by xiangke on 2016/7/6.
 */
public class Test {
    public static void main(String[] args) {
        /*IService service = (IService) new DynamicProxy().bind(new Service());
        service.save();*/
        IService service = (IService) new OperationDynamicProxy().bind(new Service(), new LoggerOperation());
        service.save();
    }
}
