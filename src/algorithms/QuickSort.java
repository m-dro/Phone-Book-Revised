package algorithms;

import phonebook.PhoneBook;
import java.util.ArrayList;
import java.util.Collections;

public class QuickSort {

    private static ArrayList<String> phoneBookCopy;
    private static long executionTime;

    public static ArrayList<String> getSortedPhoneBook() {
        if (!phoneBookCopy.isEmpty()) return phoneBookCopy;
        else return null;
    }

    public static long getExecutionTime() {
        return executionTime;
    }

    public static void sort() {
        copyPhoneBook();
        long startTime = System.currentTimeMillis();
        quickSort(0, phoneBookCopy.size() - 1);
        executionTime = System.currentTimeMillis() - startTime;
    }

    private static void quickSort(int left, int right) {
        if (left < right) {
            int pivotIndex = partition(left, right);
            quickSort(left, pivotIndex - 1);
            quickSort(pivotIndex + 1, right);
        }
    }

    private static int partition(int left, int right) {
        String pivot = phoneBookCopy.get(right);
        int partitionIndex = left;
        for (int i = left; i < right; i++) {
            if (phoneBookCopy.get(i).compareTo(pivot) <= 0) {
                Collections.swap(phoneBookCopy, i, partitionIndex);
                partitionIndex++;
            }
        }
        Collections.swap(phoneBookCopy, partitionIndex, right);
        return partitionIndex;
    }

    private static void copyPhoneBook() {
        phoneBookCopy = PhoneBook.getEntries();
    }
}
