package com.sully.string;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * Creator: lei.s
 * Create Date: 2017年09月11日
 * 类功能描述：
 */
public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0;

    public InstrumentedHashSet(){}

    public InstrumentedHashSet(int initCap,float loadFactor){
        super(initCap,loadFactor);
    }
    @Override
    public boolean add(E e){
        addCount++;
        return super.add(e);
    }
    @Override
    public boolean addAll(Collection<? extends E> c){
        addCount = addCount+c.size();
        return super.addAll(c);
    }

    public int getAddCount(){
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedHashSet instrumentedHashSet = new InstrumentedHashSet();
        instrumentedHashSet.addAll(Arrays.asList("sdfsdf","23sdf","sdfsdf"));
        System.out.println(instrumentedHashSet.getAddCount());
    }
}
