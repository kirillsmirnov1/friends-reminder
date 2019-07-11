package com.trulden;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Person implements Serializable {

    private String name;
    private HashMap<String, Integer> lastInteractions;

    public Person(){
        name = "";
        lastInteractions = new HashMap<>();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Person that = (Person)obj;

        if(!name.equals(that.getName()))
            return false;

        for(Map.Entry<String, Integer> entry : lastInteractions.entrySet()){
            if(!that.getLastInteractions().containsKey(entry.getKey())
            || !that.getLastInteractions().get(entry.getKey()).equals(entry.getValue()))
                return false;
        }

        return true;
    }

    Person(String name){
        this.name = name;
        lastInteractions = new HashMap<>();
    }

    @Override
    public String toString(){
//        StringBuilder str = new StringBuilder(name + "\n");
//
//        for(Map.Entry<String, Integer> entry : lastInteractions.entrySet()){
//            str.append(entry.getKey() + " " + Util.daysPassed(Main.interactions.get(entry.getValue()).getDate()) + " days ago\n"); // TODO олучать доступ к массиву взаимодействий в Main
//        }
//
//        return str.toString();

        return name;
    }

    public void addInteraction(Interaction interaction){
        lastInteractions.put(interaction.getType(), interaction.getId());
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getLastInteractions() {
        return lastInteractions;
    }

    public void setLastInteractions(HashMap<String, Integer> lastInteractions) {
        this.lastInteractions = lastInteractions;
    }
}
