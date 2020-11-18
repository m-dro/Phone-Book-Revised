package algorithms;

import phonebook.PhoneBook;
import java.util.ArrayList;
import java.util.Collections;

public class BubbleSort {

    private static ArrayList<String> phoneBookCopy;
    private static long executionTime;
    private static boolean stopped;

    public static ArrayList<String> getSortedPhoneBook() {
        if (!phoneBookCopy.isEmpty()) return phoneBookCopy;
        else return null;
    }

    public static long getExecutionTime() {
        return executionTime;
    }

    public static boolean wasStopped() {
        return stopped;
    }

    public static void sort() {
        stopped = false;
        copyPhoneBook();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < phoneBookCopy.size() - 1; i++) {
            for (int j = 0; j < phoneBookCopy.size() - i - 1; j++) {
                if (phoneBookCopy.get(j).compareTo(phoneBookCopy.get(j + 1)) > 0) {
                    Collections.swap(phoneBookCopy, j, j + 1);
                }
                if (System.currentTimeMillis() - startTime > 60_000) {
                    executionTime = System.currentTimeMillis() - startTime;
                    phoneBookCopy.clear();
                    stopped = true;
                    return;
                }
            }
        }
        executionTime = System.currentTimeMillis() - startTime;
    }

    private static void copyPhoneBook() {
        phoneBookCopy = PhoneBook.getEntries();
    }
}
