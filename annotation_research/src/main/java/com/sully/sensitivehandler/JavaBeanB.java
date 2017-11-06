package com.sully.sensitivehandler;

import java.util.Map;
import java.util.Set;

/**
 * Creator: lei.s
 * Create Date: 2017年08月17日
 * 类功能描述：
 */
public class JavaBeanB {
    @SensitiveInfo(type= SensitiveInfoUtils.SensitiveType.CHINESE_NAME)
    private String name = "B先生";

    private JavaBeanA a;

    private Set<JavaBeanA> list;

    private Map<String,JavaBeanA> map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JavaBeanA getA() {
        return a;
    }

    public void setA(JavaBeanA a) {
        this.a = a;
    }

    public Set<JavaBeanA> getList() {
        return list;
    }

    public void setList(Set<JavaBeanA> list) {
        this.list = list;
    }

    public Map<String, JavaBeanA> getMap() {
        return map;
    }

    public void setMap(Map<String, JavaBeanA> map) {
        this.map = map;
    }
}
