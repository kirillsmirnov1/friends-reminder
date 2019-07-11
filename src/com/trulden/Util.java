package com.trulden;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Date;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Util {

    // Writing object to file
    public static void serialize(Object object, String fileName){
        try(XMLEncoder e = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(fileName)))){

            e.writeObject(object);
            e.flush();
        } catch (Exception e){
            System.out.println("Serialization error");
            e.printStackTrace();
        }
    }

    // Reading object from file
    public static Object deserialize(String fileName) {

        try(XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream(fileName)))){

            Object o = d.readObject();

            d.close();

            return o;

        } catch (Exception e){
            if(e.getClass() == FileNotFoundException.class) {
                System.out.println("File «" + fileName + "» not found");
            } else {
                System.out.println("Error in deserialization");
                e.printStackTrace();
            }
        }

        return null;
    }

    public static TreeSet<String> getDefaultInteractions() {
        TreeSet<String> defaultInteractions = new TreeSet<>();

        defaultInteractions.add("Встреча");
        defaultInteractions.add("Переписка");

        return defaultInteractions;
    }

    public static int daysPassed(Date date) {
        return (int) TimeUnit.DAYS.convert(new Date().getTime() - date.getTime(), TimeUnit.MILLISECONDS);
    }
}
