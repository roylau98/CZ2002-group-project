import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a {@code Reservation} object to reserve a table.
 * It reserves a {@link Table} at a particular date and time for a customer.
 * {@code Reservation} object also tracks the number of pax coming, and if the customer arrived during his reservation timing.
 *
 * @since 2021-11-5
 */
public class Reservation implements Serializable {
    /**
     * Date that the customer wants to make a {@code Reservation}.
     */
    private LocalDate date;

    /**
     * Time that the customer wants to make a {@code Reservation}.
     */
    private LocalTime time;

    /**
     * The hour which the customer wants to make a {@code Reservation}.
     */
    private int hour;

    /**
     * Number of persons that are coming.
     */
    private int noOfPax;

    /**
     * The customer which made the reservation.
     */
    private Customer customer;

    /**
     * Table that has been reserved.
     */
    private int tableNo;

    /**
     * Tracks if Customer has arrived at his {@code Reservation} timing.
     */
    private boolean customerArrived;

    /**
     * Class constructor.
     *
     * @param date     Date of {@code Reservation}.
     * @param time     Time of {@code Reservation}.
     * @param noOfPax  Number of persons for the {@code Reservation}.
     * @param customer Customer that made the {@code Reservation}.
     */
    public Reservation(LocalDate date, LocalTime time, int noOfPax, Customer customer) {
        this.date = date;
        this.time = time;
        this.noOfPax = noOfPax;
        this.customer = customer;
        this.hour = time.getHour();
        this.customerArrived = false;
        this.tableNo = -1;
    }

    /**
     * Copy constructor.
     *
     * @param r {@code Reservation} to be cloned.
     */
    public Reservation(Reservation r) {
        this.date = r.date;
        this.time = r.time;
        this.hour = r.hour;
        this.noOfPax = r.noOfPax;
        this.tableNo = r.tableNo;
        this.customer = new Customer(r.customer);
        this.customerArrived = r.customerArrived;
    }

    /**
     * Gets the date of the {@code Reservation}.
     *
     * @return date reserved.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the {@code Reservation}.
     *
     * @param date Date reserved.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the time of the {@code Reservation}.
     *
     * @return time reserved.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the time of the {@code Reservation}.
     *
     * @param time Time reserved.
     */
    public void setTime(LocalTime time) {
        this.time = time;
        this.hour = time.getHour();
    }

    /**
     * Gets the hour of the {@code Reservation}.
     *
     * @return the hour of the reservation
     */
    public int getHour() {
        return hour;
    }

    /**
     * Gets the number of persons.
     *
     * @return Number of persons.
     */
    public int getNoOfPax() {
        return noOfPax;
    }

    /**
     * Sets the number of persons.
     *
     * @param noOfPax Number of persons.
     */
    public void setNoOfPax(int noOfPax) {
        this.noOfPax = noOfPax;
    }

    /**
     * Gets the table number that the {@code Reservation} has been assigned to.
     *
     * @return Table number.
     */
    public int getTableNo() {
        return tableNo;
    }

    /**
     * Sets the table number that the {@code Reservation} has been assigned to.
     *
     * @param tableNo Table number.
     */
    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    /**
     * Gets the customer that is making the {@code Reservation}.
     *
     * @return customer object
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the Customer that is making the {@code Reservation}.
     *
     * @param customer The customer that is making the {@code Reservation}.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Tracks if the customer has arrived at his {@code Reservation} timing.
     *
     * @return true if customer arrived, else false
     */
    public boolean getCustomerArrived() {
        return customerArrived;
    }

    /**
     * Sets the attribute customerArrived when a customer arrived at his reservation timing.
     *
     * @param customerArrived true if customerArrived, else false
     */
    public void setCustomerArrived(boolean customerArrived) {
        this.customerArrived = customerArrived;
    }

    /**
     * Return a string with {@code Reservation} details.
     *
     * @return String with {@code Reservation} details.
     */
    @Override
    public String toString() {
        return date + " " + time + "\t" + noOfPax + " persons\t" + customer + "\tTable Number " + tableNo + " ";
    }
}
