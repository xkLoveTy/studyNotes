package com.study.notes.java.aop.JDKDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiangke on 2016/7/6.
 */
public class DynamicProxy implements InvocationHandler {

    private Object delegate;

    public  Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(),
                this.delegate.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("*****Before*****");
        Object result = method.invoke(this.delegate, args);
        System.out.println(result.toString());
        System.out.println("*****After*****");

        return result;
    }
}
