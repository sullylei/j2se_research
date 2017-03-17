package com.sully.teststringBuilder;

/**
 * Created by lei.s on 2017/3/16.
 */
public class Test {
    public static void main(String[] args) {
        run1();
        run2();
    }

    public static void run1() {
        long start = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < 10000; i++) {
            result += i;
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void run2() {
        long start = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            builder.append(i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}

