package com.sully.join;

/**
 * Creator: lei.s
 * Create Date: 2017年04月20日
 * 类功能描述：
 */
public class RunnableImpl implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Begin sleep");
            Thread.sleep(1000);
            System.out.println("End sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
