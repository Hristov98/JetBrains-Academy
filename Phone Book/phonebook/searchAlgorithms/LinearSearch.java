package phonebook.searchAlgorithms;

import java.util.ArrayList;

public class LinearSearch {
    public static boolean search(ArrayList<String> phoneBook, String person) {
        for (String entry : phoneBook) {
            if (entry.equals(person)) {
                return true;
            }
        }

        return false;
    }
}
