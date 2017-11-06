package com.sully.effective.java;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

/**
 * Creator: lei.s
 * Create Date: 2017年11月03日
 * 类功能描述：
 */
public class StopThreadA {
    private static boolean stopRequested;
    public static void main(String[] args) throws Exception{
        Thread backGroundThread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int i =0;
                        while (!stopRequested)
                            i++;
                    }
                }
        );
        backGroundThread.start();;
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
