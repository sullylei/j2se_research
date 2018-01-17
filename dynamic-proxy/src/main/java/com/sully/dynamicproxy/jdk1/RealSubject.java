package com.sully.dynamicproxy.jdk1;

/**
 * Creator: lei.s
 * Create Date: 2018年01月17日-10:14
 *
 * @Description:
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println( "call doSomething()" );
    }
}
