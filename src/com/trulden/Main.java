package com.trulden;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    private static HashMap<String, Person> persons;
    private static TreeSet<String> interactions;

    private static final String personsFileName      = "persons.out";
    private static final String interactionsFileName = "interactions.out";

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

        Util.serialize(persons, personsFileName);

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
        persons = (new File(personsFileName).exists())
                ? (HashMap<String, Person>) Util.deserialize(personsFileName)
                : new HashMap<>();
    }
}
