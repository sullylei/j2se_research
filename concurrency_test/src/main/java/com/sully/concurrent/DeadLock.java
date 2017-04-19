package com.sully.concurrent;

/**
 * Creator: lei.s
 * Create Date: 2017年04月19日
 * 类功能描述：
 */
public class DeadLock {
    static String str1 = "obj1";
    static String str2 = "obj2";

    public static void main(String[] args) {

        new Thread(new Run1()).start();
//        new Thread(new Run2()).start();
    }
    static class Run1 implements Runnable{

        @Override
        public void run() {
            while (true) {
                synchronized (DeadLock.str1) {
                    System.out.println("进入 Run1运行,lock了str1");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Run1的sleep结束，尝试锁住str2");
                    synchronized (DeadLock.str2) {
                        System.out.println("进入 Run1运行,lock str2");
                    }
                }
            }
        }

    }


    static class Run2 implements Runnable{

        @Override
        public void run() {
            synchronized (DeadLock.str2){
                System.out.println("进入Run2的，锁住了str2");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Run2的sleep结束，尝试锁住str1");
                synchronized (DeadLock.str1){
                    System.out.println("进入Run2的，锁住了str1");
                }
            }
        }
    }
}
