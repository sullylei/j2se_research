package com.sully.domain.lombok;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by lei.s on 2017/3/28.
 */
public class Test {
    public static void main(String[] args){
        Person person = new Person("001", "wity_lv", "wity_lv@sample.com");
        System.out.println(person.toString());

        Person person2 = new Person("001", "wity_lv", "wity_lv@sample.com");
        System.out.println(person.equals(person2));


        int size = 2;
        int key1=3,key2=5,key3=7;
        System.out.println(key1%size);
        System.out.println(key2%size);
        System.out.println(key3%size);

        System.out.println(4&12);

        vectorTest();
        List<String> arrayList = Collections.synchronizedList(new ArrayList<String>());

        System.out.println(125<<1);
    }

    public static void vectorTest(){
        Vector<String> vector = new Vector<String>();
        for(int i=0;i<10;i++){
            vector.add(i+"");
        }
        System.out.println(vector);
    }
}
