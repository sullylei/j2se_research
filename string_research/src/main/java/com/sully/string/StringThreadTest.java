package com.sully.string;

import org.junit.Test;

/**
 * Creator: lei.s
 * Create Date: 2017年04月14日
 * 类功能描述：
 */
public class StringThreadTest {
    private static final int THREAD_COUNT = 5;

    @Test
    public void testStringThread() {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new StringThread("192.168.1.1"));
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].start();
        }

        for (;;);
    }
}
