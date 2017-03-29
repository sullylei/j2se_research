package com.sully.domain;

import lombok.Data;
import org.apache.log4j.Logger;


/**
 * Created by lei.s on 2017/3/28.
 */
public class Person {
    private String id;
    private String name;
    private String identity;
    private Logger log = Logger.getLogger(Person.class);

    public Person(){

    }
    public Person(String id, String name, String identity) {
        this.id = id;
        this.name = name;
        this.identity = identity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
