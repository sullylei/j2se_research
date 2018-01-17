package com.sully.dynamicproxy.jdk1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Creator: lei.s
 * Create Date: 2018年01月17日-10:25
 *
 * @Description:
 */
public class ProxyHandler implements InvocationHandler {
    private Object target;

    //绑定委托对象，并返回代理类
    public Object bind(Object target)
    {
        this.target = target;
        //绑定该类实现的所有接口，取得代理类
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("before");
        //这里就可以进行所谓的AOP编程了
        //在调用具体函数方法前，执行功能处理
        result = method.invoke(target,args);
        //在调用具体函数方法后，执行功能处理
        System.out.println("after");
        return result;
    }
}
