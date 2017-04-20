package com.sully.join1;

/**
 * Creator: lei.s
 * Create Date: 2017年04月20日
 * 类功能描述：http://uule.iteye.com/blog/1101994
 */
class ThreadTest extends Thread {

    Thread thread;

    public ThreadTest(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        synchronized (thread) {
            System.out.println("getObjectLock");
            try {
                Thread.sleep(9000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("ReleaseObjectLock");
        }
    }
}
