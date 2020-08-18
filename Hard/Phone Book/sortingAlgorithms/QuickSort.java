package phonebook.sortingAlgorithms;

import java.util.ArrayList;

public class QuickSort {
    public static void sort(ArrayList<String> phoneBook, int left, int right) {
        if (left < right) {
            int partition = getPartition(phoneBook, left, right);
            sort(phoneBook, left, partition - 1);
            sort(phoneBook, partition + 1, right);
        }
    }

    private static int getPartition(ArrayList<String> phoneBook, int left, int right) {
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (phoneBook.get(i).compareTo(phoneBook.get(right)) < 0) {
                swap(phoneBook,i,partitionIndex);
                partitionIndex++;
            }
        }

        swap(phoneBook,right,partitionIndex);

        return partitionIndex;
    }

    private static void swap(ArrayList<String> phoneBook, int i, int j) {
        String helper = phoneBook.get(i);
        phoneBook.set(i, phoneBook.get(j));
        phoneBook.set(j, helper);
    }
}
