package com.sully.dynamicproxy;

/**
 * Creator: lei.s
 * Create Date: 2018年01月10日-16:44
 *
 * @Description:
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String str) {
        System.out.println(str);
    }
}
