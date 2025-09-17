package lab2_journal;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JournalApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<JournalEntry> entries = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("===== Curator's Journal =====");
            System.out.println("1. Add new entry");
            System.out.println("2. Show all entries");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addEntry();
                    break;
                case "2":
                    showEntries();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
            System.out.println();
        }

        System.out.println("Program finished.");
        scanner.close();
    }

    private static void addEntry() {
        System.out.println("--- Add new journal entry ---");

        // last name
        String lastName;
        while (true) {
            System.out.print("Enter last name: ");
            lastName = scanner.nextLine().trim();
            if (isValidName(lastName)) break;
            System.out.println("Invalid last name. Use letters, spaces or '-', min 2 chars.");
        }

        // first name
        String firstName;
        while (true) {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine().trim();
            if (isValidName(firstName)) break;
            System.out.println("Invalid first name. Use letters, spaces or '-', min 2 chars.");
        }

        // birth date
        LocalDate birthDate;
        while (true) {
            System.out.print("Enter birth date (dd.MM.yyyy): ");
            String dateStr = scanner.nextLine().trim();
            birthDate = parseDate(dateStr);
            if (birthDate != null) {
                if (!birthDate.isAfter(LocalDate.now())) break;
                else System.out.println("Birth date cannot be in the future.");
            } else {
                System.out.println("Invalid date format. Use dd.MM.yyyy.");
            }
        }

        // phone
        String phone;
        while (true) {
            System.out.print("Enter phone (digits, optional '+', spaces or '-' allowed): ");
            phone = scanner.nextLine().trim();
            if (isValidPhone(phone)) break;
            System.out.println("Invalid phone. Provide 7-15 digits, optional leading '+'.");
        }

        // address
        String address;
        while (true) {
            System.out.print("Enter address (street, house, apt): ");
            address = scanner.nextLine().trim();
            if (!address.isEmpty() && address.length() >= 5) break;
            System.out.println("Invalid address. Please provide non-empty address.");
        }

        JournalEntry entry = new JournalEntry(lastName, firstName, birthDate, normalizePhone(phone), address);
        entries.add(entry);
        System.out.println("Entry successfully created!");
    }

    private static void showEntries() {
        if (entries.isEmpty()) {
            System.out.println("No entries yet.");
            return;
        }
        System.out.println("All journal entries:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, entries.get(i).toString());
        }
    }

    // --- Validators & helpers ---

    private static boolean isValidName(String s) {
        if (s == null) return false;
        String trimmed = s.trim();
        if (trimmed.length() < 2 || trimmed.length() > 60) return false;
        // allow letters (any Unicode letter), spaces, hyphens and apostrophes
        return trimmed.matches("[\\p{L} .'-]+");
    }

    private static LocalDate parseDate(String s) {
        try {
            return LocalDate.parse(s, JournalEntry.DATE_FORMAT);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    private static boolean isValidPhone(String s) {
        if (s == null) return false;
        String cleaned = s.replaceAll("[\\s\\-()]", "");
        // allow leading + and then 7..15 digits, or 7..15 digits
        return cleaned.matches("^\\+?\\d{7,15}$");
    }

    private static String normalizePhone(String s) {
        return s.replaceAll("[\\s\\-()]", "");
    }
}
