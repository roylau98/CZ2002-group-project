/**
 * Represents a Reservation object to reserve a table.
 * It reserves a table at a particular date and time for a customer.
 * @author
 * @version
 * @since 2021-10-19
 */

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
	/**
	 * The date of reservation.
	 */
	private LocalDate date;
	/**
	 * The time of reservation.
	 */
	private LocalTime time;
	/**
	 * The hour that is reserved.
	 */
	private int hour;
	/**
	 * Number of persons
	 */
	private int noOfPax;
	private Customer customer;
	private int tableNo;

	/**
	 * Class constructor
	 * @param date date of Reservation
	 * @param time time of Reservation
	 * @param noOfPax number of pax for Reservation
	 * @param customer Customer object for Reservation
	 */
	public Reservation(LocalDate date, LocalTime time, int noOfPax, Customer customer) {
		this.date = date;
		this.time = time;
		this.noOfPax = noOfPax;
		this.customer = customer;
	}

	/**
	 * Gets the booking date of the reservation
	 * @return the booking date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Changes the booking date of the reservation.
	 * @param date The new reservation date.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * Gets the booking time of the reservation
	 * @return the booking time
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * Changes the booking time of the reservation.
	 * @param time The new reservation time.
	 */
	public void setTime(LocalTime time) {
		this.time = time;
		setHour(time.getHour());
	}
	/**
	 * Gets the current local time
	 * @return the current local time
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Changes the booking hour of the reservation.
	 * @param hour The new reservation hour.
	 */
	private void setHour(int hour) {
		this.hour = hour;
	}
	/**
	 * Gets the number of pax
	 * @return the number of pax
	 */
	public int getNoOfPax() {
		return noOfPax;
	}

	public void setNoOfPax(int noOfPax) {
		this.noOfPax = noOfPax;
	}

	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Gets the table number
	 * @return the table number
	 */
	public int getTableNo() {
		return tableNo;
	}

	/**
	 * Changes the table number of the reservation
	 * @param tableNo new table number
	 */
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	/*
	public void timeUp() {
		// TODO - implement ReservationItem.timeUp
		throw new UnsupportedOperationException();
	}

	public void missedReservation() {
		// TODO - implement ReservationItem.missedReservation
		throw new UnsupportedOperationException();
	}

	public void releaseTable() {
		// TODO - implement ReservationItem.releaseTable
		throw new UnsupportedOperationException();
	}

}