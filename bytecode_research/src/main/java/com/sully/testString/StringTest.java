package com.sully.testString;

/**
 * Created by lei.s on 2017/3/17.
 */
public class StringTest {
    public static void main(String[] args) {
        String a = "java";
        String b = "java";
        String c = "ja" + "va";
        System.out.println(a==b);
        System.out.println(a==c);

        String s1 = new StringBuilder().append("String").append("Test").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s2.intern() == s2);
    }
}
