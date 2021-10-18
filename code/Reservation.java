import java.time.LocalDateTime;

public class Reservation {
	private LocalDateTime bookingTime;
	private int noOfPax;
	private Customer customer;
	private int tableNo;

	public Reservation(LocalDateTime bookingTime, int noOfPax, Customer customer) {
		this.bookingTime = bookingTime;
		this.noOfPax = noOfPax;
		this.customer = customer;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public int getNoOfPax() {
		return noOfPax;
	}

	public void setNoOfPax(int noOfPax) {
		this.noOfPax = noOfPax;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getTableNo() {
		return tableNo;
	}

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