package algorithms;

import java.util.ArrayList;

public class JumpSearch {

    private static long executionTime;

    public static long getExecutionTime() {
        return executionTime;
    }

    public static int search(ArrayList<String> phoneBook, ArrayList<String> namesToSearch) {
        if (phoneBook.size() == 0 || namesToSearch.size() == 0) { executionTime = 0; return 0; }
        long startTime = System.currentTimeMillis();
        int phoneNumbersFound = 0;
        int jumpLength = (int) Math.sqrt(phoneBook.size());

        for (String name : namesToSearch) {
            if (phoneBook.get(0).contains(name)) { phoneNumbersFound++; }
            boolean foundPhoneNumber = false;
            int currentRight = 0;
            int prevRight = 0;
            while (currentRight < phoneBook.size() - 1) {
                currentRight = Math.min(phoneBook.size() - 1, currentRight + jumpLength);
                if (phoneBook.get(currentRight).compareTo(name) >= 0) {
                    foundPhoneNumber = true;
                    break;
                }
                prevRight = currentRight;
            }
            if (foundPhoneNumber) {
                for (int j = currentRight; j > prevRight; j--) {
                    if (phoneBook.get(j).contains(name)) { phoneNumbersFound++; break; }
                }
            }
        }

        executionTime = System.currentTimeMillis() - startTime;
        return phoneNumbersFound;
    }
}
