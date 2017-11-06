package com.sully.testmethod;

/**
 * Creator: lei.s
 * Create Date: 2017年10月27日
 * 类功能描述：
 */
public class ReflectTest {
    public static void main(String[] args) {
        //translate the class name into a class Object
        Class<?> cl = null;
        try{
            cl = Class.forName(args[0]);
        }catch (Exception e){
            System.err.println("Class not Found .");
            System.exit(1);
        }
        //

    }
}
