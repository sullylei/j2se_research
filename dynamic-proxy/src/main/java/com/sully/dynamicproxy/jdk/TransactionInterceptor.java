package com.sully.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Creator: lei.s
 * Create Date: 2017年12月29日-18:21
 * @Description:使用jdk自带的动态代理需要了解InvocationHandler接口和Proxy类，
 * 他们都是在java.lang.reflect包下，InvocationHandler介绍：InvocationHandler是代理实例的调用处理程序实现的接口。
 */
public class TransactionInterceptor implements InvocationHandler {

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start Transaction");
        method.invoke(target, args);
        System.out.println("end Transaction");
        return null;
    }

}