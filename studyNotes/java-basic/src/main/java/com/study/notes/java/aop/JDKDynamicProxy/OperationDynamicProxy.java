package com.study.notes.java.aop.JDKDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiangke on 2016/7/6.
 */
public class OperationDynamicProxy implements InvocationHandler {

    private Object delegate;
    private Object operation;

    public OperationDynamicProxy() {}

    public Object bind (Object delegate, Object operation) {
        this.delegate = delegate;
        this.operation = operation;

        return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(),
                this.delegate.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        // 反射得到操作者的实例
        Class clazz = this.operation.getClass();
        // 反射得到操作者的before方法
        Method start = clazz.getDeclaredMethod("before", new Class[] { Method.class });
        // 反射执行before方法
        start.invoke(this.operation, new Object[] { method });
        // 执行要处理对象的原本方法
        result = method.invoke(this.delegate, args);
        // 反射得到操作者的end方法
        Method end = clazz.getDeclaredMethod("after", new Class[] { Method.class });
        // 反射执行end方法
        end.invoke(this.operation, new Object[] { method });
        return result;
    }

}
