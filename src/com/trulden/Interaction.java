package com.trulden;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeSet;

// Interaction with one or several persons
public class Interaction implements Serializable {
    private final TreeSet<Person> persons;
    private final String type;
    private final Date date;
    private final String comment;

    Interaction(TreeSet<Person> persons, String type, Date date, String comment){
        this.persons = persons;
        this.type = type;
        this.date = date;
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }
}
