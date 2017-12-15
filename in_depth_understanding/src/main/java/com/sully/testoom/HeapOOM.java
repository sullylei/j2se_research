package com.sully.testoom;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator: lei.s
 * Create Date: 2017年11月07日
 * 类功能描述：
 */
public class HeapOOM {
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true)
            list.add(new OOMObject());
    }
}

class OOMObject{
    public static String str = new String("233333333333sfdfdfdfdfdfdf");
}
