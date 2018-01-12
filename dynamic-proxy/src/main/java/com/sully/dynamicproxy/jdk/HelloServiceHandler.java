package com.sully.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Creator: lei.s
 * Create Date: 2018��01��10��-12:11
 *
 * @Description:
 */
public class HelloServiceHandler implements InvocationHandler {


    private Object target;
    /**
     * ��ί�ж��󲢷���һ��������ռλ��
     * @param target ��ʵ����
     * @return  �������ռλ��
     */
    public  Object bind(Object target, Class[] interfaces) {
        this.target = target;
        //ȡ�ô������
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    /**
     * ͬ�����������÷������Ƚ����������.
     * @param proxy --�������
     * @param method -- ����,�����÷���.
     * @param args -- �����Ĳ���
     */
    public Object invoke(Object proxy , Method method, Object[] args) throws Throwable {
        System.err.println("############����JDK��̬����################");
        Object result = null;
        //���䷽��ǰ����
        System.err.println("��׼��˵hello��");
        //����ִ�з���  �൱�ڵ���target.sayHelllo;
        result=method.invoke(target, args);
        //���䷽�������.
        System.err.println("��˵��hello��");
        return result;
    }

}
