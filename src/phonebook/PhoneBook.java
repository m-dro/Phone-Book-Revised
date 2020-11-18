package phonebook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {

    private static ArrayList<String> entries = new ArrayList<>();

    public static void importPhoneBook(String phoneBookPath) {
        try (Scanner scan = new Scanner(new File(phoneBookPath))) {
            while (scan.hasNext()) {
                String number = scan.next();
                entries.add(scan.nextLine().trim() + "_" + number);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + ", cause: " + e.getCause());
        }
    }

    public static ArrayList<String> getEntries() {
        return new ArrayList<>(entries);
    }

    public static int getSize() {
        return entries.size();
    }
}
