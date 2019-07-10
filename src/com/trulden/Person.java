package com.trulden;

import java.io.Serializable;
import java.util.HashMap;

public class Person implements Serializable {

    private String name;
    private HashMap<String, Interaction> lastInteractions;

    Person(String name){
        this.name = name;
        lastInteractions = new HashMap<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addInteraction(Interaction interaction){
        lastInteractions.put(interaction.getType(), interaction);
    }
}
