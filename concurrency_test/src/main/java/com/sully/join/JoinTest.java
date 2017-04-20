package com.sully.join;

/**
 * Creator: lei.s
 * Create Date: 2017年04月20日
 * 类功能描述：http://uule.iteye.com/blog/1101994
 */
public class JoinTest implements Runnable{
    private static int count =0;
    @Override
    public void run() {
        for (int i=0;i<5;i++){
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JoinTest joinTest = new JoinTest();
        Thread t = new Thread(joinTest);
        t.start();
//        t.join();
        for (int i =0;i<200;i++){
            System.out.println(i);
        }
        System.out.println("i循环完毕");
        System.out.println(count);
    }
}
