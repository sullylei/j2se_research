package com.sully.teststringBuilder;

/**
 * Created by lei.s on 2017/3/16.
 */
public class StringTest {
    public static void main(String[] args) {
        String str1 = "hello ";
        String str2 = "java";
        String str3 = str1 + str2 + "!";
        String str4 = new StringBuilder().append(str1).append(str2).append("!").toString();
    }
}