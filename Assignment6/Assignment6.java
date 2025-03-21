package Assignment6;

import java.util.Arrays;

public class Assignment6 {
    public static void main(String[] args) {
        int tableSize = 10;
        HashTable hashTable = new HashTable(tableSize);

        int[] keys = {89, 18, 49, 58, 9};
        System.out.println("\nInserting 89, 18, 49, 58, 9:");
        for (int key : keys) hashTable.insertKey(key);

        System.out.println("\nSearching for 49:");
        System.out.println("Found at index: " + hashTable.findKey(49));

        System.out.println("\nDeleting 49:");
        hashTable.deleteKey(49);
        hashTable.printTable();

        System.out.println("\nSearching for 49 after deletion:");
        int index = hashTable.findKey(49);
        if (index == -1) {
            System.out.println("Key '49' not found in the hash table.\n");
        } else {
            System.out.println("Key '49' found at index: " + index + "\n");
        }
    }

    private static class HashTable {
        private static final int DELETED = -1;
        private int tableSize;
        private Integer[] table;

        public HashTable(int tableSize) {
            this.tableSize = tableSize;
            this.table = new Integer[tableSize];
            Arrays.fill(this.table, null);
        }

        public int insertKey(int key) {
            int keyIndex = hash(key);
            int currentIndex = 0;

            while (table[calculateIndex(keyIndex, currentIndex)] != null 
                && table[calculateIndex(keyIndex, currentIndex)] != DELETED) {
                currentIndex++;
                if (currentIndex >= tableSize) {
                    return -1;
                }
            }

            int finalIndex = calculateIndex(keyIndex, currentIndex);
            table[finalIndex] = key;
            return finalIndex;
        }

        public int findKey(int key) {
            int keyIndex = hash(key);
            int i = 0;

            while (table[calculateIndex(keyIndex, i)] != null) {
                int currentIndex = calculateIndex(keyIndex, i);
                if (table[currentIndex] == key) {
                    return currentIndex;
                }

                i++;
                if (i >= tableSize) {
                    return -1;
                }
            }

            return -1;
        }

        public int deleteKey(int key) {
            int keyIndex = findKey(key);
            if (keyIndex != -1) {
                table[keyIndex] = DELETED;
                return keyIndex;
            }
            return -1;
        }

        public void printTable() {
            System.out.println(Arrays.toString(table));
        }

        private int hash(int key) {
            return key % tableSize;
        }

        private int calculateIndex(int keyIndex, int currentIndex) {
            return (keyIndex + currentIndex * currentIndex) % tableSize;
        }
    }
}