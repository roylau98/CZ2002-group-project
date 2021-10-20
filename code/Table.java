import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

/**
 * Represents a table object in the restaurant.
 *
 * @author
 * @since 2021-10-19
 */
public class Table {
    /**
     * Unique identifier for the table instance.
     */
    private int tableID;

    /**
     * Maximum number of seats at the table
     */
    private int capacity;

    /**
     * Stores all reservation for the table instance using HashMap.
     * The key used is the date of reservation, while the value is an array of Reservation object.
     * The array length is 24, in order to mimic 24 hours in a day which the customer can book.
     */
    private HashMap<LocalDate, Reservation[]> reservationsMap;

    /**
     * Class constructor
     *
     * @param tableID  unique identifier for the table
     * @param capacity maximum number of seats at teh table
     */
    public Table(int tableID, int capacity) {
        this.tableID = tableID;
        this.capacity = capacity;
        this.reservationsMap = new HashMap<>();
    }

    /**
     * Gets the tableID
     *
     * @return the tableID
     */
    public int getTableID() {
        return tableID;
    }

    /**
     * Sets the tableID
     *
     * @param tableID the table ID
     */
    public void setTableID(int tableID) {
        this.tableID = tableID;
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

    /**
     * Gets the HashMap containing all reservation of the Table instance.
     *
     * @return HashMap containing all reservations.
     */
    public HashMap<LocalDate, Reservation[]> getReservations() {
        return reservationsMap;
    }

    /**
     * Sets the list of reservations at the table
     *
     * @param reservations updated list of reservations in a HashMap
     */
    public void setReservations(HashMap<LocalDate, Reservation[]> reservations) {
        this.reservationsMap = reservations;
    }

    /**
     * Determines if a table is available for reservation at a particular date and time.
     *
     * @param date date to be checked
     * @param time time to be checked
     * @return true if the table is available, false otherwise
     */
    public boolean isAvailableAt(LocalDate date, LocalTime time) {
        if (!reservationsMap.containsKey(date))
            return true;
        else
            return reservationsMap.get(date)[time.getHour()] == null;
    }

    /**
     * Inserts a reservation into this table's records.
     *
     * @param r Reservation that will be added
     */
    public void insertReservation(Reservation r) {
        r.setTableNo(this.tableID);
        if (reservationsMap.containsKey(r.getDate())) {
            reservationsMap.get(r.getDate())[r.getHour()] = r;
        } else {
            Reservation[] reservationsArray = new Reservation[24];
            for (int i = 0; i < 24; i++) {
                reservationsArray[i] = null;
            }
            reservationsArray[r.getHour()] = r;
            reservationsMap.put(r.getDate(), reservationsArray);
        }
        System.out.println("Reservation successfully added");
    }

    /**
     * Removes a reservation from this table's records.
     *
     * @param date date of the reservation
     * @param time time of the reservation
     * @see #removeKeyValuePair(LocalDate)
     */
    public void removeReservation(LocalDate date, LocalTime time) {
        int bookingHour = time.getHour();

        reservationsMap.get(date)[bookingHour] = null;
        System.out.println("Reservation successfully cancelled");
        removeKeyValuePair(date);
    }

    /**
     * Attempts to find a reservation at the table that matches the details provided exactly
     *
     * @param datetime  date and time of the reservation
     * @param name      name of the customer that made the reservation
     * @param contactNo contact number of the customer
     * @param noOfPax   number of persons
     * @return reservation object if found, null otherwise
     */
    public Reservation comparesReservation(LocalDateTime datetime, String name, String contactNo, int noOfPax) {
        LocalDate bookingDate = datetime.toLocalDate();
        LocalTime bookingTime = datetime.toLocalTime();
        int bookingHour = bookingTime.getHour();

        if (reservationsMap.containsKey(bookingDate)) {
            Reservation r = reservationsMap.get(bookingDate)[bookingHour];
            if (r != null && r.getCustomer().getName().equals(name) && r.getCustomer().getContactNo().equals(contactNo) && r.getNoOfPax() == noOfPax) {
                return r;
            }
        }
        return null;
    }

    /**
     * Cleans the HashMap by removing unnecessary entries.
     * Keys of type LocalDate which do not contain any reservations are removed.
     *
     * @param date date
     */
    private void removeKeyValuePair(LocalDate date) {
        boolean isNotEmpty = false;
        Reservation[] reservationOnDate = reservationsMap.get(date);

        for (int i = 0; i < 24; i++) {
            if (reservationOnDate[i] != null) {
                isNotEmpty = true;
                break;
            }
        }

        if (!isNotEmpty) {
            reservationsMap.remove(date);
        }
    }
}