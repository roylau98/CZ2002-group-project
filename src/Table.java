import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents a table object in the restaurant.
 *
 * @since 2021-11-5
 */
public class Table implements Serializable {

    /**
     * Record of the table's availability for reservations as a map.
     * Records are accessed by a LocalDate key, which will retrieve an array of boolean values that indicate the table's availability.
     * A true value indicates that the table is available for reservation, while a false value indicates that it is not.
     * The array length is 24, in order to mimic 24 hours in a day which the customer can book.
     */
    private final HashMap<LocalDate, Boolean[]> availabilityRecord;
    /**
     * Capacity of the table.
     */
    private int capacity;

    /**
     * Class constructor for table.
     *
     * @param capacity Capacity of the table.
     */
    public Table(int capacity) {
        this.capacity = capacity;
        this.availabilityRecord = new HashMap<>();
    }

    /**
     * Gets the capacity of the table.
     *
     * @return capacity of the table.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the table.
     *
     * @param capacity Capacity of the table.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Determines if the table is available for reservation at the specified date and time.
     * If the hashmap does not contain the key (date), then it is available since there are no bookings at that date.
     * Otherwise, we will fetch the array associated with the date from the hashmap and returns true only if the time
     * does not have a reservation.
     *
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
     * It first checks to ensure that the date has a reservation before trying to mark the date and time as available.
     * Lastly, it will attempt to clean up the hashmap.
     *
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
     * A new array will be created if the key (date) is not present in the hashmap, before marking the
     * hour as unavailable.
     *
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

    /**
     * Cleans up the hashmap and removes the key, value pair.
     * It will only remove when the date is passed or when there is no reservations on a particular date for this table.
     */
    private void mapCleanup() {
        for (Iterator<Map.Entry<LocalDate, Boolean[]>> it = availabilityRecord.entrySet().iterator(); it.hasNext(); ) {
            LocalDate date = it.next().getKey();
            if (date.isBefore(LocalDate.now())) {
                it.remove();
                continue;
            }
            boolean noRecords = true;
            for (int i = 0; i < 24; i++) {
                if (!availabilityRecord.get(date)[i])
                    noRecords = false;
            }
            if (noRecords)
                it.remove();
        }
    }

    /**
     * Return a string with table details.
     *
     * @return String with table details.
     */
    @Override
    public String toString() {
        return "Table{" +
                "capacity=" + capacity +
                ", availabilityRecord=" + availabilityRecord +
                '}';
    }
}
