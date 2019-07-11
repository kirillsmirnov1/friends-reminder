package com.trulden;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

// Interaction with one or several persons
public class Interaction implements Serializable {
    private HashSet<Person> persons;
    private String type;
    private Date date;
    private String comment;

    public Interaction(){
        persons = new HashSet<>();
        type = "";
        date = new Date();
        comment = "";
    }

    Interaction(HashSet<Person> persons, String type, Date date, String comment){
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

    public HashSet<Person> getPersons() {
        return persons;
    }

    public String getComment() {
        return comment;
    }

    public void setPersons(HashSet<Person> persons) {
        this.persons = persons;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interaction)) return false;
        Interaction that = (Interaction) o;

        if(persons.size() != that.persons.size())
            return false;

        for(Person person : persons){
            if(!that.persons.contains(person))
                return false;
        }

        return type.equals(that.type) &&
                date.equals(that.date) &&
                comment.equals(that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, type, date, comment);
    }

    @Override
    // FIXME it probably won't return readable string
    public String toString() {
        return "Interaction{" +
                "persons=" + persons +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                '}';
    }
}
