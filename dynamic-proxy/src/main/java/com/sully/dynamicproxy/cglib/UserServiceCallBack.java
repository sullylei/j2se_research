package com.sully.dynamicproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Creator: lei.s
 * Create Date: 2018年01月03日-11:17
 *
 * @Description:cglib动态代理
 */
public class UserServiceCallBack implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("start Transaction by cglib");
        methodProxy.invokeSuper(o, args);
        System.out.println("end Transaction by cglib");
        return null;
    }

}
