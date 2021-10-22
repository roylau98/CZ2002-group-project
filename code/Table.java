import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Represents a table object in the restaurant.
 *
 * @author
 * @since 2021-10-19
 */
public class Table {
    /**
     * Maximum number of seats at the table
     */
    private int capacity;

    /**
     * Stores all reservation for the table instance using HashMap.
     * The key used is the date of reservation, while the value is an array of Reservation object.
     * The array length is 24, in order to mimic 24 hours in a day which the customer can book.
     */
    private HashMap<LocalDate, Boolean[]> availabilityRecord;

    /**
     * Class constructor
     *
     * @param tableID  unique identifier for the table
     * @param capacity maximum number of seats at teh table
     */
    public Table(int capacity) {
        this.capacity = capacity;
        this.availabilityRecord = new HashMap<>();
    }

    /**
     * Gets the capacity of the table.
     *
     * @return Capcaity of the table.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the table
     *
     * @param capacity capacity of the table
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public HashMap<LocalDate, Boolean[]> getAvailabilityRecord() {
        return availabilityRecord;
    }

    public void setAvailabilityRecord(HashMap<LocalDate, Boolean[]> availabilityRecord) {
        this.availabilityRecord = availabilityRecord;
    }

    /**
     * Determines if a table is available for reservation at a particular date and time.
     *
     * @param date date to be checked
     * @param time time to be checked
     * @return true if the table is available, false otherwise
     */
    public boolean checkAvailabilityAt(LocalDate date, LocalTime time) {
        if (!availabilityRecord.containsKey(date))
            return true;
        else
            return availabilityRecord.get(date)[time.getHour()];
    }

    public void markAsAvailableAt(LocalDate date, LocalTime time) {
        if (!availabilityRecord.containsKey(date))
            return;
        availabilityRecord.get(date)[time.getHour()] = true;
        // map cleaning?
    }

    public void markAsUnavailableAt(LocalDate date, LocalTime time) {
        if (!availabilityRecord.containsKey(date)) {
            Boolean[] a = new Boolean[24];
            Arrays.fill(a, true);
            availabilityRecord.put(date, a);
        }
        availabilityRecord.get(date)[time.getHour()] = false;
    }

    @Override
    public String toString() {
        return "Table{" +
                "capacity=" + capacity +
                ", availabilityRecord=" + availabilityRecord +
                '}';
    }
}