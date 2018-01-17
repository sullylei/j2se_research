package com.sully.dynamicproxy.jdk1;

/**
 * Creator: lei.s
 * Create Date: 2018年01月17日-10:22
 *
 * @Description:
 */
public class TestProxy {
    public static void main(String[] args) {
        Subject sub = new SubjectProxy();
        sub.doSomething();
    }
}
