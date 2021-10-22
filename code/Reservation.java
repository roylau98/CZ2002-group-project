import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Reservation object to reserve a table.
 * It reserves a table at a particular date and time for a customer.
 *
 * @author
 * @since 2021-10-19
 */
public class Reservation {
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

    /**
     * Class constructor
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
    }

    /**
     * Gets the date of the reservation.
     * @return Date reserved.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the time of the reservation.
     * @return Time reserved.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Gets the number of persons.
     * @return Number of persons.
     */
    public int getNoOfPax() {
        return noOfPax;
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