package com.sully.domain.lombok;

/**
 * Created by lei.s on 2017/3/28.
 */
public class Test {
    public static void main(String[] args){
        Person person = new Person("001", "wity_lv", "wity_lv@sample.com");
        System.out.println(person.toString());

        Person person2 = new Person("001", "wity_lv", "wity_lv@sample.com");
        System.out.println(person.equals(person2));
    }
}
