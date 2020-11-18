package algorithms;

import java.util.ArrayList;

public class LinearSearch {

    private static long executionTime;

    public static long getExecutionTime() {
        return executionTime;
    }

    public static int search(ArrayList<String> phoneBook, ArrayList<String> namesToSearch) {
        long startTime = System.currentTimeMillis();
        int phoneNumbersFound = 0;
        for (String name : namesToSearch) {
            for (String entry : phoneBook) {
                if (entry.contains(name)) { phoneNumbersFound++; break; }
            }
        }
        executionTime = System.currentTimeMillis() - startTime;
        return phoneNumbersFound;
    }
}
