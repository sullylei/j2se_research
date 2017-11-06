package com.sully.testmethod;

/**
 * Creator: lei.s
 * Create Date: 2017年11月06日
 * 类功能描述：值传递还是引用传递
 */
public class ReferencePkValue {
    public static void main(String[] args) {
        ReferencePkValue t = new ReferencePkValue();
        int a=99;
        t.test1(a);//这里传递的参数a就是按值传递
        System.out.println(a);

        MyObj obj=new MyObj();
        t.test2(obj);//这里传递的参数obj就是引用传递
        System.out.println(obj.b);
    }

    public void test1(int a){
        a=a++;
        System.out.println(a);
    }

    public void test2(MyObj obj){
        obj.b=100;
        System.out.println(obj.b);
    }

}
class MyObj{
    public int b=99;
}