package com.sully.testmethod;

/**
 * Creator: lei.s
 * Create Date: 2017年11月06日
 * 类功能描述：
 */
public class UserTest {
    public void set(User user){
        user.setName("hello world");
    }
    public static void main(String[] args) {
        UserTest userTest = new UserTest();
        User user = new User();
        userTest.set(user);
        System.out.println(user.getName());
    }

}
