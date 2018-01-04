package com.sully.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Creator: lei.s
 * Create Date: 2017��12��29��-18:21
 * @Description:ʹ��jdk�Դ��Ķ�̬������Ҫ�˽�InvocationHandler�ӿں�Proxy�࣬
 * ���Ƕ�����java.lang.reflect���£�InvocationHandler���ܣ�InvocationHandler�Ǵ���ʵ���ĵ��ô������ʵ�ֵĽӿڡ�
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