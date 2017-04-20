package com.sully.join1;

/**
 * Creator: lei.s
 * Create Date: 2017年04月20日
 * 类功能描述：
 */
class RunnableImpl implements Runnable {

    public void run() {
        try {
            System.out.println("Begin sleep");
            Thread.sleep(2000);
            System.out.println("End sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}