package algorithms;

import java.util.ArrayList;

public class BinarySearch {

    private static long executionTime;

    public static long getExecutionTime() {
        return executionTime;
    }

    public static int search(ArrayList<String> phoneBook, ArrayList<String> namesToSearch) {
        long startTime = System.currentTimeMillis();
        int phoneNumbersFound = 0;
        for (String name : namesToSearch) {
            int left = 0;
            int right = phoneBook.size();
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (phoneBook.get(mid).contains(name)) { phoneNumbersFound++; break; }
                else if (name.compareTo(phoneBook.get(mid)) < 0) { right = mid - 1; }
                else { left = mid + 1; }
            }
        }

        executionTime = System.currentTimeMillis() - startTime;
        return phoneNumbersFound;
    }
}
