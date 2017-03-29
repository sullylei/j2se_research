package com.sully.testString;

import java.net.URL;

/**
 * Created by lei.s on 2017/3/16.
 */
public class Test {
    public static void main(String[] args) {
        String test = "test";
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
    }
}