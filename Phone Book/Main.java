package phonebook;

import phonebook.searchAlgorithms.BinarySearch;
import phonebook.searchAlgorithms.JumpSearch;
import phonebook.searchAlgorithms.LinearSearch;
import phonebook.sortingAlgorithms.BubbleSort;
import phonebook.sortingAlgorithms.QuickSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File bookFile = new File("directory.txt");
        File findFile = new File("find.txt");
        ArrayList<String> phoneBook = new ArrayList<>();
        ArrayList<String> peopleToFind = new ArrayList<>();

        try {
            Scanner directoryScanner = new Scanner(bookFile);

            while (directoryScanner.hasNextLine()) {
                String phoneEntry = directoryScanner.nextLine();
                String[] entryFields = phoneEntry.split(" ");

                if (entryFields.length == 2) {
                    phoneBook.add(entryFields[1]);
                } else if (entryFields.length == 3) {
                    phoneBook.add(      entryFields[1] + " " + entryFields[2]);
                } else if (entryFields.length == 4) {
                    phoneBook.add(entryFields[1] + " " + entryFields[2] + " " + entryFields[3]);
                }
            }
            directoryScanner.close();

            Scanner findScanner = new Scanner(findFile);

            while (findScanner.hasNextLine()) {
                String findEntry = findScanner.nextLine();

                peopleToFind.add(findEntry);
            }
            findScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long linearTime = linearSearch(phoneBook, peopleToFind);
        System.out.println("\n");
        bubbleSortAndJumpSearch(phoneBook, peopleToFind, linearTime);
        System.out.println("\n");
        quickSortAndBinarySearch(phoneBook, peopleToFind);
        hashTableSearch(phoneBook,peopleToFind);
    }

    private static long linearSearch(ArrayList<String> phoneBook,
                                     ArrayList<String> peopleToFind) {
        System.out.println("Start searching (linear search)...");

        long startTime = System.currentTimeMillis();

        int foundCounter = 0;
        for (String person : peopleToFind) {
            if (LinearSearch.search(phoneBook, person)) {
                foundCounter++;
            }
        }

        long timeTaken = System.currentTimeMillis() - startTime;
        long secondsTaken = timeTaken / 1000;
        long millisecondsTaken = timeTaken % 1000;
        long minutesTaken = secondsTaken / 60;
        secondsTaken %= 60;

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                foundCounter, peopleToFind.size(), minutesTaken, secondsTaken, millisecondsTaken);

        return timeTaken;
    }

    private static void bubbleSortAndJumpSearch(ArrayList<String> phoneBook,
                                                ArrayList<String> peopleToFind,
                                                long linearTime) {

        long sortStartTime = System.currentTimeMillis();
        boolean tookTooLong = BubbleSort.sort(linearTime, phoneBook);
        long sortEndTime = System.currentTimeMillis() - sortStartTime;

        int foundCounter = 0;
        long searchStartTime = System.currentTimeMillis();

        System.out.println("Start searching (bubble sort + jump search)...");
        if (tookTooLong) {
            for (String person : peopleToFind) {
                if (LinearSearch.search(phoneBook, person)) {
                    foundCounter++;
                }
            }
        } else {
            for (String person : peopleToFind) {
                if (JumpSearch.search(phoneBook, person)) {
                    foundCounter++;
                }
            }
        }
        long searchEndTime = System.currentTimeMillis() - searchStartTime;

        printBubbleJumpResult(tookTooLong, foundCounter, sortEndTime, searchEndTime);
    }

    private static void printBubbleJumpResult(boolean tookTooLong, int found, long sortTime, long searchTime) {
        final int SEARCH_SIZE = 500;

        long sortSecondsTaken = sortTime / 1000;
        long sortMillisTaken = sortTime % 1000;
        long sortMinutesTaken = sortSecondsTaken / 60;
        sortSecondsTaken %= 60;

        long searchSecondsTaken = searchTime / 1000;
        long searchMillisTaken = searchTime % 1000;
        long searchMinutesTaken = searchSecondsTaken / 60;
        searchSecondsTaken %= 60;

        long totalMinutes = searchMinutesTaken + sortMinutesTaken;
        long totalSeconds = searchSecondsTaken + sortSecondsTaken;
        long totalMillis = searchMillisTaken + sortMillisTaken;
        if (totalMillis > 1000) {
            totalSeconds++;
            totalMillis %= 1000;
        }
        if (totalSeconds > 60) {
            totalMinutes++;
            totalSeconds %= 60;
        }

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                found, SEARCH_SIZE, totalMinutes, totalSeconds, totalMillis);
        if (tookTooLong) {
            System.out.printf("Sorting time: %d min. %d sec. %d ms. - STOPPED, moved to linear search\n"
                    , sortMinutesTaken, sortSecondsTaken, sortMillisTaken);
        } else {
            System.out.printf("Sorting time: %d min. %d sec. %d ms.\n",
                    sortMinutesTaken, sortSecondsTaken, sortMillisTaken);
        }
        System.out.printf("Searching time: %d min. %d sec. %d ms.",
                searchMinutesTaken, searchSecondsTaken, searchMillisTaken);
    }

    private static void quickSortAndBinarySearch(ArrayList<String> phoneBook,
                                                 ArrayList<String> peopleToFind) {
        long sortStartTime = System.currentTimeMillis();
        QuickSort.sort(phoneBook, 0, phoneBook.size() - 1);
        long sortEndTime = System.currentTimeMillis() - sortStartTime;

        System.out.println("Start searching (quick sort + binary search)...");
        long searchStartTime = System.currentTimeMillis();
        int foundCounter = 0;
        for (String person : peopleToFind) {
            if (BinarySearch.search(phoneBook, person)) {
                foundCounter++;
            }
        }
        long searchEndTime = System.currentTimeMillis() - searchStartTime;

        printQuickBinaryResults(foundCounter, sortEndTime, searchEndTime);
    }

    private static void printQuickBinaryResults(int found, long sortTime, long searchTime) {
        final int SEARCH_SIZE = 500;

        long sortSecondsTaken = sortTime / 1000;
        long sortMillisTaken = sortTime % 1000;
        long sortMinutesTaken = sortSecondsTaken / 60;
        sortSecondsTaken %= 60;

        long searchSecondsTaken = searchTime / 1000;
        long searchMillisTaken = searchTime % 1000;
        long searchMinutesTaken = searchSecondsTaken / 60;
        searchSecondsTaken %= 60;

        long totalMinutes = searchMinutesTaken + sortMinutesTaken;
        long totalSeconds = searchSecondsTaken + sortSecondsTaken;
        long totalMillis = searchMillisTaken + sortMillisTaken;
        if (totalMillis > 1000) {
            totalSeconds++;
            totalMillis %= 1000;
        }
        if (totalSeconds > 60) {
            totalMinutes++;
            totalSeconds %= 60;
        }

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                found, SEARCH_SIZE, totalMinutes, totalSeconds, totalMillis);
        System.out.printf("Sorting time: %d min. %d sec. %d ms.\n",
                sortMinutesTaken, sortSecondsTaken, sortMillisTaken);
        System.out.printf("Searching time: %d min. %d sec. %d ms.",
                searchMinutesTaken, searchSecondsTaken, searchMillisTaken);
    }

    private static void hashTableSearch(ArrayList<String> phoneBook,
                                        ArrayList<String> peopleToFind) {

        long createStartTime = System.currentTimeMillis();
        HashTable table = new HashTable();
        table.put(phoneBook);
        long createEndTime = System.currentTimeMillis() - createStartTime;

        long searchStartTime = System.currentTimeMillis();
        int foundCounter = 0;
        for (String person : peopleToFind) {
            if (phoneBook.contains(person)) {
                foundCounter++;
            }
        }
        long searchEndTime = System.currentTimeMillis() - searchStartTime;

        printHashTableResult(foundCounter, createEndTime, searchEndTime);
    }

    private static void printHashTableResult(int found, long createTime, long searchTime) {
        final int SEARCH_SIZE = 500;

        long createSecondsTaken = createTime / 1000;
        long createMillisTaken = createTime % 1000;
        long createMinutesTaken = createSecondsTaken / 60;
        createSecondsTaken %= 60;

        long searchSecondsTaken = searchTime / 1000;
        long searchMillisTaken = searchTime % 1000;
        long searchMinutesTaken = searchSecondsTaken / 60;
        searchSecondsTaken %= 60;

        long totalMinutes = searchMinutesTaken + createMinutesTaken;
        long totalSeconds = searchSecondsTaken + createSecondsTaken;
        long totalMillis = searchMillisTaken + createMillisTaken;
        if (totalMillis > 1000) {
            totalSeconds++;
            totalMillis %= 1000;
        }
        if (totalSeconds > 60) {
            totalMinutes++;
            totalSeconds %= 60;
        }

        System.out.println("Start searching (hash table)...");
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                found, SEARCH_SIZE, totalMinutes, totalSeconds, totalMillis);
        System.out.printf("Creating time: %d min. %d sec. %d ms.\n",
                createMinutesTaken, createSecondsTaken, createMillisTaken);
        System.out.printf("Searching time: %d min. %d sec. %d ms.",
                searchMinutesTaken, searchSecondsTaken, searchMillisTaken);
    }
}