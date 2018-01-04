package com.sully.dynamicproxy.jdk.cglib;

import com.sully.dynamicproxy.UserServiceImpl;
import com.sully.dynamicproxy.cglib.UserServiceCallBack;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * Creator: lei.s
 * Create Date: 2018年01月03日-11:19
 *
 * @Description:cglib动态代理
 */
public class TestDynamicProxy {

    @Test
    public void testCGLIB() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new UserServiceCallBack());
        UserServiceImpl proxy = (UserServiceImpl)enhancer.create();
        proxy.addUser();
    }

}