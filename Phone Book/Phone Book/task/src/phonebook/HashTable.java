package phonebook;

import java.util.ArrayList;

public class HashTable {
    private ArrayList<String> names;

    public HashTable() {
        names = new ArrayList<>();
    }

    public void put(ArrayList<String>  book) {
        for (String entry : book) {
            names.add(entry);
        }
    }

}
