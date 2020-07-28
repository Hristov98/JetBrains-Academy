import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

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

        public void remove() {
            removed = true;
        }

        public boolean isRemoved() {
            return removed;
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

            if (index != -1) {
                table[index] = new TableEntry(key, value);
                return true;
            } else {
                rehash();
                int newIndex = findKey(key);
                table[newIndex] = new TableEntry(key, value);
            }

            return true;
        }

        public void remove(int key) {
            int index = findKey(key);

            try {
                if (index != -1) {
                    table[index].remove();
                }
            } catch (NullPointerException ignored) {
            }
        }

        private int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;


                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private void rehash() {
            size *= 2;
            TableEntry[] oldTable = table;
            TableEntry[] newTable = new TableEntry[size];
            table = newTable;

            for (int i = 0; i < oldTable.length; i++) {
                put(oldTable[i].getKey(), (T) oldTable[i].getValue());
            }
        }

        @Override
        public String toString() {
            StringBuilder tableStringBuilder = new StringBuilder();

            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    tableStringBuilder.append(i + ": null");
                } else {
                    tableStringBuilder.append(i + ": key=" + table[i].getKey()
                            + ", value=" + table[i].getValue()
                            + ", removed=" + table[i].isRemoved());
                }

                if (i < table.length - 1) {
                    tableStringBuilder.append("\n");
                }
            }

            return tableStringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int remove = scan.nextInt();
        HashTable<String> table = new HashTable<>(5);

        for (int i = 0; i < size; i++) {
            int id = scan.nextInt();
            String name = scan.next();

            table.put(id, name);
        }

        for (int i = 0; i < remove; i++) {
            int id = scan.nextInt();
            table.remove(id);
        }

        System.out.println(table.toString());
    }
}