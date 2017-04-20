package com.sully.join1;

/**
 * Creator: lei.s
 * Create Date: 2017年04月20日
 * 类功能描述：
 */
public class JoinTest {
    public static void main(String[] args) {
        Thread t = new Thread(new RunnableImpl());
        new ThreadTest(t).start();
        t.start();
        try {
            t.join();
            System.out.println("joinFinish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}