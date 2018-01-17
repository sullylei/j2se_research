package com.sully.dynamicproxy.jdk;

import com.sully.dynamicproxy.HelloService;
import com.sully.dynamicproxy.HelloServiceImpl;
import com.sully.dynamicproxy.UserService;
import com.sully.dynamicproxy.UserServiceImpl;
import com.sully.dynamicproxy.jdk1.ProxyHandler;
import com.sully.dynamicproxy.jdk1.RealSubject;
import com.sully.dynamicproxy.jdk1.Subject;
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

    @Test
    public  void testHelloServiceProxy(){
        HelloServiceHandler handler = new HelloServiceHandler();
        HelloService service = new HelloServiceImpl();
        //绑定代理对象。
        HelloService proxy = (HelloService) handler.bind(service, new Class[] {service.getClass()});
        proxy.sayHello("hello");

    }

    @Test
    public void testSubjectProxy(){
        ProxyHandler proxy = new ProxyHandler();
        HelloService subject = (HelloService) proxy.bind(new HelloServiceImpl());
        subject.sayHello("test");
    }
}