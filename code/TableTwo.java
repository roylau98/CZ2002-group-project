import java.time.LocalDate;
import java.util.HashMap;

public class TableTwo {
	private int tableID;
	private int capacity;
	private HashMap<LocalDate, Boolean[]> reservations;

	public TableTwo(int tableID, int capacity) {
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

	public HashMap<LocalDate, Boolean[]> getReservations() {
		return reservations;
	}

	public void setReservations(HashMap<LocalDate, Boolean[]> reservations) {
		this.reservations = reservations;
	}
}