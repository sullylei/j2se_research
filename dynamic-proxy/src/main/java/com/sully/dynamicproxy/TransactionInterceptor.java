package com.sully.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Creator: lei.s
 * Create Date: 2017Äê12ÔÂ29ÈÕ-18:21
 *
 * @Description:
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