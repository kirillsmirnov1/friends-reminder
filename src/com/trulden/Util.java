package com.trulden;

import java.io.*;
import java.util.TreeSet;

public class Util {

    // Reading object from file
    public static void serialize(Object object, String fileName){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(fileName))){

            objectOutputStream.writeObject(object);

        } catch (Exception e){
            System.out.println("Serialization error");
            e.printStackTrace();
        }
    }

    // Writing object to file
    public static Object deserialize(String fileName) {

        try(ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(fileName))){

            return objectInputStream.readObject();

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
}
