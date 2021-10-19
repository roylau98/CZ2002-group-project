import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReservationApp {
	private static ArrayList<Table> listOfTables = new ArrayList<>();

	/**
	 * Adds a reservation to the table's records
	 * @param r The reservation object
	 * @return true if reservation is added, else false
	 */
	public static boolean addReservation(Reservation r) {
		//maybe can change to void instead of boolean. (!!!)
		if (r.getDate().isBefore(LocalDate.now()) || (r.getDate().isEqual(LocalDate.now()) && r.getTime().isBefore(LocalTime.now()))){
			System.out.println("Reservation time selected is before current time");
			return false;
		}

		for (Table t : listOfTables) {
			if (r.getNoOfPax() <= t.getCapacity()) {
				// get the date and time of booking;
				LocalDate bookingDate = r.getBookingTime().toLocalDate();
				LocalTime bookingTime = r.getBookingTime().toLocalTime();
				int bookingHour = bookingTime.getHour();

				// if there is a previous booking at the date
				if (t.getReservations().containsKey(bookingDate)) {
					// if the current table is available during the booking hour, add reservation
					if (t.getReservations().get(bookingDate)[bookingHour] != null) {
						continue;
					} else {
						t.getReservations().get(bookingDate)[bookingHour] = r;
						r.setTableNo(t.getTableID());
						System.out.println("Successfully reserved table " + t.getTableID() + " at " + r.getBookingTime());
						return true;
					}
				} else {
					// no previous booking, we can make a new array
					Reservation[] reservationsArray = new Reservation[24];
					for (int i=0; i<24; i++) {
						reservationsArray[i] = null;
					}
					reservationsArray[bookingHour] = r;
					t.getReservations().put(bookingDate, reservationsArray);
					r.setTableNo(t.getTableID());
					System.out.println("Successfully reserved table " + t.getTableID() + " at " + r.getBookingTime());
					return true;
				}
			}
		}
		System.out.println("No available table found");
		return false;
	}

	public static void cancelReservation(LocalDateTime dt, String name, String contactNo, int noOfPax) {
		LocalDate bookingDate = dt.toLocalDate();
		LocalTime bookingTime = dt.toLocalTime();
		int bookingHour = bookingTime.getHour();

		for (Table t : listOfTables) {
			if (t.getReservations().containsKey(bookingDate)) {
				Reservation r = t.getReservations().get(bookingDate)[bookingHour];
				if (r != null
						&& r.getCustomer().getName().equals(name)
						&& r.getCustomer().getContactNo().equals(contactNo)
						&& r.getNoOfPax() == noOfPax) {
					return r;
				}
			}
		}

		System.out.println("Reservation not found");
	}

	private static void cancelReservation(Reservation r) {
		if (r == null) {
			System.out.println("Reservation cannot be found");
			return;
		}
		// get the date and time of booking
		LocalDate bookingDate = r.getDate();
		LocalTime bookingTime = r.getTime();
		int bookingHour = bookingTime.getHour();

		Table restaurantTable = listOfTables.get(r.getTableNo());
//		//error checking, to check if table really contains reservation for that date.
//		if (!restaurantTable.getReservations().containsKey(bookingDate)) {
//			System.out.println("Reservation not found");
//			return;
//		}

		restaurantTable.getReservations().get(bookingDate)[bookingHour] = null;
		System.out.println("Reservation successfully cancelled");

		boolean isNotEmpty = false;
		for (int i=0; i<24; i++) {
			if (restaurantTable.getReservations().get(bookingDate)[i] != null) {
				isNotEmpty = true;
				break;
			}
		}

		if (!isNotEmpty) {
			restaurantTable.getReservations().remove(bookingDate);
		}
	}

	public static void updateReservation(Reservation oldReservation, Reservation newReservation) {
		cancelReservation(oldReservation);
		if (addReservation(newReservation)) {
			System.out.println("Successfully updated");
		} else {
			addReservation(oldReservation);
			System.out.println("Unable to amend reservation. Old reservation kept.");
		}
	}

	public static void main(String[] args) {
		Customer c1 = new Customer("James", Sex.MALE, "123", false);
		Customer c2 = new Customer("John", Sex.MALE, "456", false);
		Customer c3 = new Customer("Mark", Sex.MALE, "789", false);
		Table t1 = new Table(0, 2);
		Table t2 = new Table(1, 2);
		Table t3 = new Table(2, 4);
		Table t4 = new Table(3, 4);
		ReservationApp.listOfTables.add(t1);
		ReservationApp.listOfTables.add(t2);
		ReservationApp.listOfTables.add(t3);
		ReservationApp.listOfTables.add(t4);

		Reservation r1 = new Reservation(LocalDateTime.of(2021, 10, 19, 13, 0), 2, c1);
		Reservation r2 = new Reservation(LocalDateTime.of(2021, 10, 19, 13, 0), 2, c2);
		Reservation r3 = new Reservation(LocalDateTime.of(2021, 10, 19, 13, 0), 2, c1);
		Reservation r4 = new Reservation(LocalDateTime.of(2021, 10, 19, 13, 0), 2, c2);

		ReservationApp.addReservation(r1);
		ReservationApp.addReservation(r2);
		ReservationApp.addReservation(r3);
		ReservationApp.addReservation(r4);

		Reservation s1 = ReservationApp.findReservation(LocalDateTime.of(2021, 10, 20, 13, 0), "James", "123", 2);
		Reservation s2 = ReservationApp.findReservation(LocalDateTime.of(2021, 10, 20, 13, 0), "Mark", "123", 2);
		//ReservationApp.updateReservation(s1, s2);
		//ReservationApp.cancelReservation(s1);
		//ReservationApp.updateReservation(r2, r3);
		//ReservationApp.cancelReservation(r3);

//		ReservationApp.cancelReservation(r2);
//		ReservationApp.cancelReservation(r3);
//		ReservationApp.cancelReservation(r4);
	}
}