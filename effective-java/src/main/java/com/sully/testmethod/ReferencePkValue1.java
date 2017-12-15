package com.sully.testmethod;

/**
 * Creator: lei.s
 * Create Date: 2017年11月06日
 * 类功能描述：
 */
public class ReferencePkValue1 {
    public static void main(String[] args){
        ReferencePkValue1 pk=new ReferencePkValue1();
        //String类似基本类型，值传递，不会改变实际参数的值
        String test1="Hello";
        pk.change(test1);
        System.out.println(test1);

        //StringBuffer和StringBuilder等是引用传递
        StringBuffer test2=new StringBuffer("Hello");
        pk.change(test2);

        System.out.println(test2.toString());
    }

    public void change(String str){
        str=str+"world";
    }

    public void change(StringBuffer str){
        str.append("world");
    }
}
