package com.sully.testmethod;

/**
 * Creator: lei.s
 * Create Date: 2017年10月20日
 * 类功能描述：
 */
public class Son extends Parent {

    public Son(){
        System.out.println("son");
    }

    static{
        System.out.println("son static method");
    }

    {
        System.out.println("son mehtod");
    }
    public static void main(String[] args) {
        new Son();
    }
}
