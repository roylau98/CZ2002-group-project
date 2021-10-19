import java.time.LocalDate;
/**
 * Represents a table object in the restaurant.
 * @author
 * @version
 * @since 2021-10-19
 */
import java.util.HashMap;


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
    private HashMap<LocalDate, Reservation[]> reservations;

    /**
     * Class constructor
     * @param tableID unique identifier for the table
     * @param capacity maximum number of seats at teh table
     */
    public Table(int tableID, int capacity) {
        this.tableID = tableID;
        this.capacity = capacity;
        this.reservations = new HashMap<>();
    }

    /**
     * Gets the tableID
     * @return the tableID
     */
    public int getTableID() {
        return tableID;
    }

    /**
     * Sets the tableID
     * @param tableID the table ID
     */
    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    /**
     * Gets the capacity of the table.
     * @return Capcaity of the table.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the table
     * @param capacity capacity of the table
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the HashMap containing all reservation of the Table instance.
     * @return HashMap containing all reservations.
     */
    public HashMap<LocalDate, Reservation[]> getReservations() {
        return reservations;
    }

    /**
     * Sets the list of reservations at the table
     * @param reservations updated list of reservations in a HashMap
     */
    public void setReservations(HashMap<LocalDate, Reservation[]> reservations) {
        this.reservations = reservations;
    }
}
