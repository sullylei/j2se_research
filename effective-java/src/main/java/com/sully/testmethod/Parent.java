package com.sully.testmethod;

/**
 * Creator: lei.s
 * Create Date: 2017年10月20日
 * 类功能描述：
 */
public class Parent {

    public Parent(){
        System.out.println("parent");
    }
    static {
        System.out.println("parent static method");
    }

    {
        System.out.println("parent method");
    }
}
