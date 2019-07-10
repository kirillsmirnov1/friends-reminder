package com.trulden;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static HashMap<String, Person> persons;

    public static void main(String[] args) {

        init();
        mainCycle();

    }

    private static void mainCycle() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("\nEnter\n 0 to exit\n 1 to add friend\n 2 to list friends\n");
            switch(Integer.parseInt(scanner.nextLine())){
                case 0:
                    exit();
                case 1:
                    addFriend();
                    break;
                case 2:
                    listFriends();
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private static void exit() {

        // Persons serialization
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("persons.out"))){

            objectOutputStream.writeInt(persons.size());

            for(Map.Entry<String, Person> entry : persons.entrySet()){
                objectOutputStream.writeObject(entry.getValue());
            }

        } catch (Exception e){
            System.out.println("Serialization error");
            e.printStackTrace();
        }

        System.exit(0);
    }

    private static void listFriends() {
        if(persons.size() > 0) {
            System.out.println("\nYour friends:");
            for (Map.Entry<String, Person> entry : persons.entrySet()) {
                System.out.println(" " + entry.getValue().getName());
            }
        } else {
            System.out.println("\nYou have no friends");
        }
    }

    private static void addFriend() {
        System.out.print("Enter friend's name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println(addFriend(name));
    }

    private static String addFriend(String name){
        if(persons.containsKey(name)){
            return ("You already have friend named «" + name + "»");
        } else {
            persons.put(name, new Person(name));
            return ("«" + name + "» added to friends");
        }
    }

    private static void init() {
        persons = new HashMap<>();

        // Deserialization
        try(ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("persons.out"))){

            int size = objectInputStream.readInt();

            for(int i = 0; i < size; ++i){
                Person person = (Person) objectInputStream.readObject();
                persons.put(person.getName(), person);
            }

        } catch (Exception e){
            if(e.getClass() == FileNotFoundException.class) {
                System.out.println("File «persons.out» not found");
            } else {
                System.out.println("Error in deserialization");
                e.printStackTrace();
            }
        }
    }
}
