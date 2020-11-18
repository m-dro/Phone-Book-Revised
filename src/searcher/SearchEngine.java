package searcher;

import algorithms.*;
import hash.HashTable;
import phonebook.PhoneBook;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SearchEngine {

    private static ArrayList<String> namesToSearch;

    public static void run() {
        PhoneBook.importPhoneBook("C:\\Users\\Admin\\Desktop\\directory.txt");
        importNamesToSearch("C:\\Users\\Admin\\Desktop\\find.txt");
        executeLinearBlock();
        executeBubbleAndJumpBlock();
        executeQuickAndBinaryBlock();
        executeHashBlock();
    }

    private static void executeLinearBlock() {
        System.out.println("\nStart searching (linear search)...");
        int phoneNumbersFound = LinearSearch.search(PhoneBook.getEntries(), namesToSearch);
        printBasicResults(phoneNumbersFound, namesToSearch.size(), convertTime(LinearSearch.getExecutionTime()));
    }

    private static void executeBubbleAndJumpBlock() {
        System.out.println("\nStart searching (bubble sort + jump search)...");
        BubbleSort.sort();
        int phoneNumbersFound;
        if (BubbleSort.wasStopped()) {
            phoneNumbersFound = LinearSearch.search(PhoneBook.getEntries(), namesToSearch);
            printBasicResults(phoneNumbersFound, namesToSearch.size(), convertTime(BubbleSort.getExecutionTime() + LinearSearch.getExecutionTime()));
            printDetails(convertTime(BubbleSort.getExecutionTime()) + " - STOPPED, moved to linear search", convertTime(LinearSearch.getExecutionTime()));
        } else {
            phoneNumbersFound = JumpSearch.search(BubbleSort.getSortedPhoneBook(), namesToSearch);
            printBasicResults(phoneNumbersFound, namesToSearch.size(), convertTime(BubbleSort.getExecutionTime() + JumpSearch.getExecutionTime()));
            printDetails(convertTime(BubbleSort.getExecutionTime()), convertTime(JumpSearch.getExecutionTime()));
        }
    }

    private static void executeQuickAndBinaryBlock() {
        System.out.println("\nStart searching (quick sort + binary search)...");
        QuickSort.sort();
        int phoneNumbersFound = BinarySearch.search(QuickSort.getSortedPhoneBook(), namesToSearch);
        printBasicResults(phoneNumbersFound, namesToSearch.size(), convertTime(QuickSort.getExecutionTime() + BinarySearch.getExecutionTime()));
        printDetails(convertTime(QuickSort.getExecutionTime()), convertTime(BinarySearch.getExecutionTime()));
    }

    private static void executeHashBlock() {
        System.out.println("\nStart searching (hash table)...");
        long startTime = System.currentTimeMillis();
        int phoneNumbersFound = 0;
        HashTable<Long> hashTable = new HashTable<>(PhoneBook.getSize() * 3);
        for (String entry : PhoneBook.getEntries()) {
            String[] words = entry.split("_");
            hashTable.put(Math.abs(words[0].hashCode()), Long.valueOf(words[1]));
        }
        long creatingTime = System.currentTimeMillis() - startTime;
        for (String name : namesToSearch) {
            if (hashTable.get(Math.abs(name.hashCode())) != null) {
                phoneNumbersFound++;
            }
        }
        long operationTime = System.currentTimeMillis() - startTime;
        printBasicResults(phoneNumbersFound, namesToSearch.size(), convertTime(operationTime));
        System.out.printf("Creating time: %s\n", convertTime(creatingTime));
        System.out.printf("Searching time: %s\n", convertTime(operationTime - creatingTime));
    }

    private static void printBasicResults(int foundEntries, int searchedEntries, String time) {
        System.out.printf("Found %d / %d entries. ", foundEntries, searchedEntries);
        System.out.printf("Time taken: %s\n", time);
    }

    private static void printDetails(String sortingTime, String searchingTime) {
        System.out.println("Sorting time: " + sortingTime);
        System.out.println("Searching time: " + searchingTime);
    }

    private static String convertTime(long milliseconds) {
        long minutes = (milliseconds / 1000) / 60;
        long seconds = (milliseconds / 1000) % 60;
        long millis = milliseconds - (seconds * 1000) - (minutes * 1000 * 60);
        return String.format("%02d min. %02d sec. %02d ms.", minutes, seconds, millis);
    }

    private static void importNamesToSearch(String namesToFindPath) {
        try { namesToSearch = (ArrayList<String>) Files.readAllLines(Paths.get(namesToFindPath)); }
        catch (IOException e) { System.out.println(e.getMessage() + ", cause: " + e.getCause()); }
    }
}
