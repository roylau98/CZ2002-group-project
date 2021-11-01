import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Reservation object to reserve a table.
 * It reserves a table at a particular date and time for a customer.
 *
 * @author
 * @since 2021-10-19
 */
public class Reservation implements Serializable {
    /**
     * Date reserved.
     */
    private LocalDate date;

    /**
     * Time reserved.
     */
    private LocalTime time;

    /**
     * Hour reserved.
     */
    private int hour;

    /**
     * Number of persons.
     */
    private int noOfPax;

    /**
     * Customer that made the booking.
     */
    private Customer customer;

    /**
     * Table that has been reserved.
     */
    private int tableNo;

    private boolean arrived;

    /**
     * Class constructor.
     *
     * @param date     Date of reservation.
     * @param time     Time of reservation.
     * @param noOfPax  Number of persons for the reservation.
     * @param customer Customer that made the reservation.
     */
    public Reservation(LocalDate date, LocalTime time, int noOfPax, Customer customer) {
        this.date = date;
        this.time = time;
        this.noOfPax = noOfPax;
        this.customer = customer;
        this.hour = time.getHour();
        this.arrived = false;
    }

    /**
     * Copy constructor.
     * @param r Reservation to be cloned.
     */
    public Reservation(Reservation r) {
        this.date = r.date;
        this.time = r.time;
        this.hour = r.hour;
        this.noOfPax = r.noOfPax;
        this.tableNo = r.tableNo;
        this.customer = new Customer(r.customer);
        this.arrived = r.arrived;
    }

    /**
     * Gets the date of the reservation.
     * @return Date reserved.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the reservation.
     * @param date Date reserved.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the time of the reservation.
     * @return Time reserved.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the time of the reservation.
     * @param time Time reserved.
     */
    public void setTime(LocalTime time) {
        this.time = time;
        this.hour = time.getHour();
    }

    /**
     * Gets the number of persons.
     * @return Number of persons.
     */
    public int getNoOfPax() {
        return noOfPax;
    }

    /**
     * Sets the number of persons.
     * @param noOfPax Number of persons.
     */
    public void setNoOfPax(int noOfPax) {
        this.noOfPax = noOfPax;
    }

    /**
     * Gets the table number that that the reservation has been asssigned to.
     * @return Table number.
     */
    public int getTableNo() {
        return tableNo;
    }

    /**
     * Sets the table number that the reservation has been assigned to.
     * @params Table number.
     */
    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "date=" + date +
                ", time=" + time +
                ", hour=" + hour +
                ", noOfPax=" + noOfPax +
                ", customer=" + customer +
                ", tableNo=" + tableNo +
                '}';
    }
}