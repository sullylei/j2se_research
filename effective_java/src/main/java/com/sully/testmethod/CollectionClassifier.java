package com.sully.testmethod;

import java.util.*;

/**
 * Creator: lei.s
 * Create Date: 2017年10月20日
 * 类功能描述：
 */
public class CollectionClassifier {

    public static String classifier(Set<?> s){
        return "set";
    }

    public static String classifier(List<?> s){
        return "list";
    }

    public static String classifier(Collection<?> s){
        return "Collection";
    }


    public static void main(String[] args) {
        Collection<?>[] collections = {new HashSet<String>()
        ,new ArrayList<String>(),new HashMap<String,String>().values()};

        for(Collection<?> c:collections){
            System.out.println(classifier(c));
        }
    }
}
