public class Table {
	private int tableID;
	private int capacity;
	private HashMap<LocalDate, Reservation[]> reservations;

	public Table(int tableID, int capacity) {
		this.tableID = tableID;
		this.capacity = capacity;
		this.reservations = new HashMap<>();
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public HashMap<LocalDate, Reservation[]> getReservations() {
		return reservations;
	}

	public void setReservations(HashMap<LocalDate, Reservation[]> reservations) {
		this.reservations = reservations;
	}

	//	public Boolean isAssigned() {
//
//	}
//
//	public int getNoOfSeats() {
//
//	}
//
//	public int getTableID() {
//
//	}
//
//	public void deleteReservation() {
//		// TODO - implement Table.deleteReservation
//		throw new UnsupportedOperationException();
//	}

}