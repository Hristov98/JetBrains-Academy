import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }
    }

    private static class HashTable<T> {
        private int size;
        private int currentSize;
        private TableEntry[] table;

        public HashTable(int size) {
            currentSize = 0;
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            int index = findKey(key);

            if (index == -1) {
                return false;
            }

            if (table[index] == null) {
                table[index] = new TableEntry(key, value);
            } else {
                T oldValue = (T) table[index].getValue();
                table[index] = new TableEntry(key, (String) oldValue + value);
            }

            return true;
        }

        public T get(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return null;
            }

            return (T) table[idx].getValue();
        }

        public TableEntry[] entrySet() {
            return table;
        }

        private int findKey(int key) {
            int hash = key % size;

            while (table[hash] != null) {
                if (table[hash].getKey() == key) {
                    break;
                } else {
                    hash = (hash + 1) % size;

                    if (hash == key % size) {
                        return -1;
                    }
                }
            }

            currentSize++;
            if (currentSize == size) {
                rehash();
            }

            return hash;
        }

        private void rehash() {
            size *= 2;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        HashTable<String> table = new HashTable<>(size);

        for (int i = 0; i < size; i++) {
            int id = scan.nextInt();
            String name = scan.nextLine();

            table.put(id, name);
        }

        for (TableEntry<String> entry : table.entrySet()) {
            if (entry != null) {
                System.out.println(entry.key + ": " + entry.value);
            }
        }

    }
}