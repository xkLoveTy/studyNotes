package com.study.notes.java.aop.CGLIB;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xiangke on 2016/7/6.
 */
public class CglibProxy implements MethodInterceptor {

    private Object delegate;
    private Object operation;

    public Object bind(Object delegate, Object operation) {
        this.delegate = delegate;
        this.operation = operation;

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.delegate.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        Class clazz = this.operation.getClass();
        Method start = clazz.getDeclaredMethod("before", new Class[]{Method.class});
        start.invoke(this.operation, new Object[] {method});

        Object result = methodProxy.invokeSuper(proxy, args);


        Method end = clazz.getDeclaredMethod("after", new Class[]{Method.class});
        end.invoke(this.operation, new Object[] {method});


        return result;
    }
}
