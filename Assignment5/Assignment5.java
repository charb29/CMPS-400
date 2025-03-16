package Assignment5;

public class Assignment5 {

    private final static int ALPHABET_SIZE = 256;

    public static void main(String[] args) {
        example1();
        example2();
    }

    private static void example1() {
        String text = "Hello, World! How are you doing today?";
        String pattern = "World";

        System.out.println("\nEXAMPLE 1:");
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        int position = search(text, pattern);
        if (position != -1) {
            System.out.println("Pattern found at position: " + position);
        } else {
            System.out.println("Pattern not found");
        }
    }

    private static void example2() {
        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String pattern = "LOTU";

        System.out.println("\nEXAMPLE 2:");
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        int position = search(text, pattern);
        if (position != -1) {
            System.out.println("Pattern found at position: " + position);
        } else {
            System.out.println("Pattern not found\n");
        }
    }

    private static int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m > n || m == 0) return -1;

        int[] shiftTable = buildShiftTable(pattern);
        int i = m - 1;
        while (i < n) {
            int k = 0;
            while (k < m && pattern.charAt(m - 1 -k) == text.charAt(i - k)) {
                k++;
            }

            if (k == m) return i - m + 1;
            i += shiftTable[text.charAt(i)];
        }

        return -1;
    }

    private static int[] buildShiftTable(String pattern) {
        int m = pattern.length();
        int[] shiftTable = new int[ALPHABET_SIZE];
        
        for (int i = 0; i < ALPHABET_SIZE; i++) shiftTable[i] = m;
        for (int i = 0; i < m - 1; i++) shiftTable[pattern.charAt(i)] = m - 1 - i;

        return shiftTable;
    }
}