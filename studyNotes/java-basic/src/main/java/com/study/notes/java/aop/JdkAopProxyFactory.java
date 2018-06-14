package com.study.notes.java.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class JdkAopProxyFactory<T> implements InvocationHandler {
	private T target;
	private List<MethodInterceptor> interceptors;

	public JdkAopProxyFactory(T target, List<MethodInterceptor> interceptors) {
		this.target = target;
		this.interceptors = interceptors;
	}

	public JdkAopProxyFactory(T target, MethodInterceptor... interceptors) {
		this.target = target;
		this.interceptors = new LinkedList<MethodInterceptor>();
		for (MethodInterceptor mi : interceptors) {
			this.interceptors.add(mi);
		}
	}

	@SuppressWarnings("unchecked")
	public T createProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		MethodInvocation mi = new MethodInvocation(target, method, args, interceptors);
		return mi.proceed();
	}

	public static void main(String[] args) {
		Hello target = new Hello();
		JdkAopProxyFactory<Welcome> factory = new JdkAopProxyFactory<Welcome>(target, new MethodBeforeInterceptor(new CountingBeforeAdvice()),
				new MethodBeforeInterceptor(new AfterAdvice()));
		Welcome proxy = factory.createProxy();
		proxy.say();
		proxy.say();
	}
}

interface Welcome {
	void say();
}

class Hello implements Welcome {
	public void say() {
		System.out.println("你好");
	}
}

interface MethodBeforeAdvice {
	void advice(Method method, Object[] args, Object obj);
}

class CountingBeforeAdvice implements MethodBeforeAdvice {
	private ConcurrentHashMap<Method, AtomicLong> counter = new ConcurrentHashMap<Method, AtomicLong>();

	public void advice(Method method, Object[] args, Object obj) {
		AtomicLong cnt = counter.putIfAbsent(method, new AtomicLong(1));
		if (cnt != null) {
			long time = cnt.incrementAndGet();
			System.out.println("访问" + time + "次");
		} else {
			System.out.println("访问1次");
		}
	}
}

class AfterAdvice implements MethodBeforeAdvice {
	@Override
	public void advice(Method method, Object[] args, Object obj) {
		System.out.println("After...");
	}
}

interface MethodInterceptor {
	Object invoke(MethodInvocation mi) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException;
}

class MethodBeforeInterceptor implements MethodInterceptor {
	private MethodBeforeAdvice advice;

	public MethodBeforeInterceptor(MethodBeforeAdvice advice) {
		this.advice = advice;
	}

	public Object invoke(MethodInvocation mi) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		advice.advice(mi.getMethod(), mi.getArgs(), mi.getTarget());
		return mi.proceed();
	}
}

class MethodInvocation {
	private List<MethodInterceptor> interceptors;
	private int currentInterceptorIndex = 0;
	private Object target;
	private Method method;
	private Object[] args;

	public MethodInvocation(Object target, Method method, Object[] args, List<MethodInterceptor> interceptors) {
		this.target = target;
		this.method = method;
		this.args = args;
		this.interceptors = interceptors;
	}

	public Object proceed() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (currentInterceptorIndex == interceptors.size()) {
			return method.invoke(target, args);
		} else {
			return interceptors.get(currentInterceptorIndex++).invoke(this);
		}
	}

	public Method getMethod() {
		return method;
	}

	public Object[] getArgs() {
		return args;
	}

	public Object getTarget() {
		return target;
	}
}