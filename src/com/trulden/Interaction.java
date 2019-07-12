package com.trulden;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

import static com.trulden.Util.dateFormat;

// Interaction with one or several persons
public class Interaction implements Serializable {

    private static int numberOfInteractions = 0;

    private HashSet<String> persons;
    private String type;
    private Date date;
    private String comment;

    private int id;

    public Interaction(){
        persons = new HashSet<>();
        type = "";
        date = new Date();
        comment = "";

        id = numberOfInteractions++;
    }

    Interaction(HashSet<String> persons, String type, Date date, String comment){
        this.persons = persons;
        this.type = type;
        this.date = date;
        this.comment = comment;

        id = numberOfInteractions++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interaction)) return false;
        Interaction that = (Interaction) o;

        if(persons.size() != that.persons.size())
            return false;

        for(String person : persons){
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
    public String toString() {
        return
                dateFormat.format(date) +
                " • [" + id + "]" +
                " • " + type +
                " with " + persons +
                " • " + comment;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public HashSet<String> getPersons() {
        return persons;
    }

    public String getComment() {
        return comment;
    }

    public void setPersons(HashSet<String> persons) {
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

    public static int getNumberOfInteractions() {
        return numberOfInteractions;
    }

    public static void setNumberOfInteractions(int numberOfInteractions) {
        Interaction.numberOfInteractions = numberOfInteractions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
