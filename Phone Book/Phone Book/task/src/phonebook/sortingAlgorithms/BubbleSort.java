package phonebook.sortingAlgorithms;

import java.util.ArrayList;

public class BubbleSort {
    public static boolean sort(long linearTime, ArrayList<String> phoneBook) {
        long sortStartTime = System.currentTimeMillis();

        for (int i = 0; i < phoneBook.size() - 1; i++) {
            if (linearTime * 10 < System.currentTimeMillis() - sortStartTime) {
                return true;
            }

            for (int j = 0; j < phoneBook.size() - 1; j++) {
                if (phoneBook.get(j).compareTo(phoneBook.get(j + 1)) > 0) {
                    String temp = phoneBook.get(j);
                    phoneBook.set(j, phoneBook.get(j + 1));
                    phoneBook.set(j + 1, temp);
                }
            }
        }

        return false;
    }
}
