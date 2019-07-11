package com.trulden;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    private static HashMap<String, Person>  persons;
    private static TreeSet<String>          interactionTypes;
    private static ArrayList<Interaction>   interactions;

    private static final String personsFileName          = "persons.out";
    private static final String interactionTypesFileName = "interactionTypes.out";
    private static final String interactionsFileName     = "interactions.out";

    static Scanner inScanner = new Scanner(System.in);

    public static void main(String[] args) {

        init();
        mainCycle();

    }

    private static void mainCycle() {
        while(true){
            System.out.println(
                    "\nEnter" +
                    "\n 0 to exit" +
                    "\n 1 to add friend" +
                    "\n 2 to list friends" +
                    "\n 3 to add interaction\n");
            switch(Integer.parseInt(inScanner.nextLine())){
                case 0:
                    exit();
                case 1:
                    addFriend();
                    break;
                case 2:
                    listFriends();
                    break;
                case 3:
                    addInteraction();
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private static void addInteraction() {

        HashSet<Person> names = new HashSet<>();
        String type;
        Date date = new Date();
        String comment;

        listFriends();

        // Friends names

        System.out.println("\nTo whom you wish to add interaction?\nEnter names divided by comma");

        String[] enteredNames = inScanner.nextLine().split(", ");

        for(String name : enteredNames) {
            checkAndAddName(name, names);
        }

        // Type

        type = readType();

        // Date

        System.out.print("Enter date of interaction in «dd MM yyyy» format: ");

        try {
            date = new SimpleDateFormat("dd MM yyyy").parse(inScanner.nextLine());
        } catch (ParseException e){
            e.printStackTrace();
            // TODO нормальный отлов
        }

        System.out.println("Tell me about your interaction\n");

        comment = inScanner.nextLine();

        Interaction interaction = new Interaction(names, type, date, comment);

        interactions.add(interaction);

        for(Person person : names){
            person.addInteraction(interaction);
        }

        System.out.println("Thanks! Interaction saved.");
    }

    private static String readType() {
        System.out.println("Enter type of meeting. \nYou can choose one of the following:\n");
        listInteractions();
        return inScanner.nextLine(); // TODO input of new type
    }

    private static void listInteractions() {
        for(String interactionType : interactionTypes){
            System.out.println(" " + interactionType);
        }
    }

    private static void checkAndAddName(String name, HashSet<Person> names) {
        if (persons.containsKey(name)) {
            names.add(persons.get(name));
        } else {
            System.out.println("You don't have friend named «" + name + "»" +
                    "\nEnter 1 to create new friend, 2 to change entered name, 3 to forget about this misunderstanding");
            switch(Integer.parseInt(inScanner.nextLine())){
                case 1:
                    addFriend(name);
                    names.add(persons.get(name));
                    System.out.println("«" + name + "» created");
                    break;
                case 2:
                    System.out.print("Enter name instead of «" + name + "»: " );
                    checkAndAddName(inScanner.nextLine(), names);
                    break;
                case 3:
                    System.out.println("«" + name + "» is forgotten");
                    break;
                default:
                    System.out.println("Wrong input, mate");
                    checkAndAddName(name, names);
            }
        }
    }

    private static void exit() {

        Util.serialize(persons, personsFileName);
        Util.serialize(interactionTypes, interactionTypesFileName);
        Util.serialize(interactions, interactionsFileName);

        System.exit(0);
    }

    private static void listFriends() {
        if(persons.size() > 0) {
            System.out.println("\nYour friends:");
            for (Map.Entry<String, Person> entry : persons.entrySet()) {
                System.out.print("\n " + entry.getValue().toString());
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

        interactionTypes = (new File(interactionTypesFileName).exists())
                ? (TreeSet<String>) Util.deserialize(interactionTypesFileName)
                : Util.getDefaultInteractions();

        interactions = (new File (interactionsFileName).exists())
                ? (ArrayList<Interaction>) Util.deserialize(interactionsFileName)
                : new ArrayList<>();
    }
}
