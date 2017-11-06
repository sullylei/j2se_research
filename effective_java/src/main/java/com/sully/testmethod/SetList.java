package com.sully.testmethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Creator: lei.s
 * Create Date: 2017年10月25日
 * 类功能描述：
 */
public class SetList {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for(int i = -3;i<3;i++){
            set.add(i);
            list.add(i);
        }
        for(int i=0;i<3;i++){
            set.remove(i);
//            list.remove(i);
            list.remove((Integer)i);
        }
        System.out.println(set + "######" +list);

        Integer integer1 = new Integer(1);
        Integer integer2 = new Integer(1);

        System.out.println(integer1.equals(integer2));
    }
}
