import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Represents a table object in the restaurant.
 *
 * @author
 * @since 2021-10-22
 */
public class Table implements Serializable {
    /**
     * Capacity of the table.
     */
    private int capacity;
    /**
     * Record of the table's availability for reservations as a map.
     * Records are accessed by a LocalDate key, which will retrieve an array of boolean values that indicate the table's availability.
     * A true value indicates that the table is available for reservation, while a false value indicates that it is not.
     * The array length is 24, in order to mimic 24 hours in a day which the customer can book.
     */
    private final HashMap<LocalDate, Boolean[]> availabilityRecord;

    /**
     * Class constructor.
     * @param capacity Capacity of the table.
     */
    public Table(int capacity) {
        this.capacity = capacity;
        this.availabilityRecord = new HashMap<>();
    }

    /**
     * Gets the capacity of the table.
     * @return Capacity of the table.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the table.
     * @param capacity Capacity of the table.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Determines if the table is available for reservation at the specified date and time.
     * @param date Reservation date.
     * @param time Reservation time.
     * @return true if the table is available, false otherwise
     */
    public boolean checkAvailabilityAt(LocalDate date, LocalTime time) {
        if (!availabilityRecord.containsKey(date))
            return true;
        else
            return availabilityRecord.get(date)[time.getHour()];
    }

    /**
     * Records that the table is now available for reservations at the specified date and time.
     * @param date Reservation date.
     * @param time Reservation time.
     */
    public void markAsAvailableAt(LocalDate date, LocalTime time) {
        if (!availabilityRecord.containsKey(date))
            return;
        availabilityRecord.get(date)[time.getHour()] = true;
        mapCleanup();
    }

    /**
     * Records that the table is now unavailable for reservations at the specified date and time.
     * @param date Reservation date.
     * @param time Reservation time.
     */
    public void markAsUnavailableAt(LocalDate date, LocalTime time) {
        if (!availabilityRecord.containsKey(date)) {
            Boolean[] a = new Boolean[24];
            Arrays.fill(a, true);
            availabilityRecord.put(date, a);
        }
        availabilityRecord.get(date)[time.getHour()] = false;
    }

    private void mapCleanup() {
        for (LocalDate date : availabilityRecord.keySet()) {
            if (date.isBefore(LocalDate.now()))
                availabilityRecord.remove(date);
            boolean noRecords = true;
            for (int i = 0; i < 24; i++) {
                if (!availabilityRecord.get(date)[i])
                    noRecords = false;
            }
            if (noRecords)
                availabilityRecord.remove(date);
        }
    }

    @Override
    public String toString() {
        return "Table{" +
                "capacity=" + capacity +
                ", availabilityRecord=" + availabilityRecord +
                '}';
    }
}