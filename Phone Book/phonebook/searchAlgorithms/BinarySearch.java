package phonebook.searchAlgorithms;

import java.util.ArrayList;

public class BinarySearch {
    public static boolean search(ArrayList<String> phoneBook, String entry) {
        int left = 0;
        int right = phoneBook.size() - 1;

        while (left <= right) {
            String middle = phoneBook.get(left + (right - left) / 2);

            if (entry.compareTo(middle) == 0) {
                return true;
            } else if (entry.compareTo(middle) < 0) {
                right = phoneBook.indexOf(middle) - 1;
            } else {
                left = phoneBook.indexOf(middle) + 1;
            }
        }

        return false;
    }
}
