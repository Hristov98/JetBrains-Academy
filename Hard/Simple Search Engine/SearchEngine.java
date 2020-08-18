package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class SearchEngine {
    private Scanner input;
    private ArrayList<String> peopleData;

    public SearchEngine() {
        input = new Scanner(System.in);
    }

    public void processCommandLineArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--data")) {
                try {
                    File dataFile = new File(args[i + 1]);
                    Scanner data = new Scanner(dataFile);
                    peopleData = enterData(data);
                    data.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getUserChoice() {
        int userChoice = input.nextInt();
        input.nextLine();

        return userChoice;
    }

    private ArrayList<String> enterData(Scanner dataFile) {
        ArrayList<String> peopleData = new ArrayList<>();
        while (dataFile.hasNextLine()) {
            peopleData.add(dataFile.nextLine());
        }

        return peopleData;
    }

    public void printMenu() {
        System.out.println("'1. Search information.\n" +
                "2. Print all data.\n" +
                "0. Exit.\n");
    }

    public void search() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategy = input.nextLine().toUpperCase();

        System.out.println("Enter a name or email to search all suitable people.");
        String query = input.nextLine();

        searchForQueryByStrategy(query, strategy);
    }

    private void searchForQueryByStrategy(String query, String strategy) {
        switch (strategy) {
            case "ALL": {
                searchForQueryALL(query);
                break;
            }
            case "ANY": {
                searchForQueryANY(query);
                break;
            }
            case "NONE": {
                searchForQueryNONE(query);
                break;
            }
            default: {
                System.out.println("Incorrect strategy selected.");
            }
        }
    }

    private void searchForQueryALL(String query) {
        ArrayList<String> peopleList = new ArrayList<>(peopleData);

        String[] keywords = query.split(" ");
        for (String person : peopleData) {
            for (String keyword : keywords) {
                if (!person.toLowerCase().contains(keyword.toLowerCase())) {
                    peopleList.remove(person);
                }
            }
        }

        if (peopleList.size() != 0) {
            for (String person : peopleList) {
                System.out.println(person);
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

    private void searchForQueryANY(String query) {
        LinkedHashSet<String> peopleSet = new LinkedHashSet<>();

        String[] keywords = query.split(" ");
        System.out.println(new ArrayList<>(Arrays.asList(keywords)));
        for (String person : peopleData) {
            for (String keyword : keywords) {
                if (person.toLowerCase().contains(keyword.toLowerCase())) {
                    peopleSet.add(person);
                    break;
                }
            }
        }

        if (peopleSet.size() != 0) {
            for (String person : peopleSet) {
                System.out.println(person);
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

    private void searchForQueryNONE(String query) {
        ArrayList<String> peopleList = new ArrayList<>(peopleData);

        String[] keywords = query.split(" ");
        for (String person : peopleData) {
            for (String keyword : keywords) {
                if (person.toLowerCase().contains(keyword.toLowerCase())) {
                    peopleList.remove(person);
                }
            }
        }

        if (peopleList.size() != 0) {
            for (String person : peopleList) {
                System.out.println(person);
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

    public void printAll() {
        System.out.println("=== List of people ===");
        for (String personData : peopleData) {
            System.out.println(personData);
        }
    }
}
