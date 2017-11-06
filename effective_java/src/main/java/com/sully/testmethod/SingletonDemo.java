package com.sully.testmethod;

/**
 * Creator: lei.s
 * Create Date: 2017年10月27日
 * 类功能描述：
 */
public class SingletonDemo {
    private SingletonDemo(){}

    private static class Holder{
        public final static SingletonDemo demo = new SingletonDemo();
    }

    public static SingletonDemo getInstance(){
        return  Holder.demo;
    }
}
