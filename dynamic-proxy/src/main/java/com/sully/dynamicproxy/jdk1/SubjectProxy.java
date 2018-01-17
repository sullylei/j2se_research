package com.sully.dynamicproxy.jdk1;

/**
 * Creator: lei.s
 * Create Date: 2018年01月17日-10:22
 *
 * @Description:
 */
public class SubjectProxy implements Subject
{
    Subject subimpl = new RealSubject();
    public void doSomething()
    {
        subimpl.doSomething();
    }
}
