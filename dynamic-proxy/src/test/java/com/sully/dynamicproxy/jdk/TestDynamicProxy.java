package com.sully.dynamicproxy.jdk;

import com.sully.dynamicproxy.UserService;
import com.sully.dynamicproxy.UserServiceImpl;
import com.sully.dynamicproxy.jdk.TransactionInterceptor;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Creator: lei.s
 * Create Date: 2017年12月29日-18:24
 *
 * @Description:
 */
public class TestDynamicProxy {

    @Test
    public void testJDK() {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        UserService userService = new UserServiceImpl();
        transactionInterceptor.setTarget(userService);
        UserService userServiceProxy =
                (UserService) Proxy.newProxyInstance(
                        userService.getClass().getClassLoader(),
                        userService.getClass().getInterfaces(),
                        transactionInterceptor);
        userServiceProxy.addUser();
    }

}