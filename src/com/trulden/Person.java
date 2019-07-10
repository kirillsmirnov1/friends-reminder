package com.trulden;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Person implements Serializable {

    private String name;
    private HashMap<String, Interaction> lastInteractions;

    Person(String name){
        this.name = name;
        lastInteractions = new HashMap<>();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder(name + "\n");

        for(Map.Entry<String, Interaction> entry : lastInteractions.entrySet()){
            str.append(entry.getKey() + " " + Util.daysPassed(entry.getValue().getDate()) + " days ago\n");
        }

        return str.toString();
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
