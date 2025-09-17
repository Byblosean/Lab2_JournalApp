package lab2_journal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class JournalEntry {
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private String phone;
    private String address;

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public JournalEntry(String lastName, String firstName, LocalDate birthDate, String phone, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        String dob = birthDate != null ? birthDate.format(DATE_FORMAT) : "N/A";
        return String.format("%s %s | DOB: %s | Phone: %s | Address: %s",
                lastName, firstName, dob, phone, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JournalEntry)) return false;
        JournalEntry that = (JournalEntry) o;
        return Objects.equals(lastName, that.lastName) &&
               Objects.equals(firstName, that.firstName) &&
               Objects.equals(birthDate, that.birthDate) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, birthDate, phone, address);
    }
}
