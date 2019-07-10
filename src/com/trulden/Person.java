package com.trulden;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;

    Person(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
