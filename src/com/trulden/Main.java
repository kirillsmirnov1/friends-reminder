package com.trulden;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    private static ArrayList<Person> persons;

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
                    exit(0);
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

    private static void listFriends() {
        if(persons.size() > 0) {
            System.out.println("\nYour friends:");
            for (Person person : persons) {

                System.out.println(" " + person.getName());
            }
        } else {
            System.out.println("\nYou have no friends");
        }
    }

    private static void addFriend() {
        System.out.print("Enter friend's name: ");
        String name = new Scanner(System.in).nextLine();
        persons.add(new Person(name));
    }

    private static void init() {
        persons = new ArrayList<>();
    }
}
