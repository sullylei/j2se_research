package com.sully.join;

/**
 * Creator: lei.s
 * Create Date: 2017年04月20日
 * 类功能描述：
 */
public class JoinTest1 {
    public static void main(String[] args) {
        Thread t = new Thread(new RunnableImpl());
        t.start();
        try {
            t.join(1000);
            System.out.println("joinFinish");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
