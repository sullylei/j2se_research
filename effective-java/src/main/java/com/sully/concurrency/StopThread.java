package com.sully.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Creator: lei.s
 * Create Date: 2017年12月14日-16:33
 *
 * @Description:
 */
public class StopThread {
    private static volatile boolean stopRequested;
    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested){
                    i++;
                }
            }
        });
        backgroundThread.start();
        TimeUnit.MINUTES.sleep(1);
        stopRequested = true;

    }
}
