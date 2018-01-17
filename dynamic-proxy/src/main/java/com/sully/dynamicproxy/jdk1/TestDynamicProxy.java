package com.sully.dynamicproxy.jdk1;

/**
 * Creator: lei.s
 * Create Date: 2018年01月17日-10:28
 *
 * @Description:
 */
public class TestDynamicProxy {
    public static void main(String[] args) {
        ProxyHandler proxy = new ProxyHandler();
        //绑定该类实现的所有接口
        Subject sub = (Subject) proxy.bind(new RealSubject());
        sub.doSomething();
    }
}
