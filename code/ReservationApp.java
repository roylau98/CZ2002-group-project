import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReservationApp {
	private static ArrayList<Table> listOfTables = new ArrayList<>();

//	public ReservationApp() {
//		listOfTables = new ArrayList<>();
//	}

	public static boolean addReservation(Reservation r) {
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

		//
		for (Table t : listOfTables) {
			if (t.getReservations().containsKey(bookingDate)
					&& t.getReservations().get(bookingDate)[bookingHour] != null
					&& t.getReservations().get(bookingDate)[bookingHour].getCustomer().getName().equals(name)
					&& t.getReservations().get(bookingDate)[bookingHour].getCustomer().getContactNo().equals(contactNo)
					&& t.getReservations().get(bookingDate)[bookingHour].getNoOfPax() == noOfPax) {
				t.getReservations().get(bookingDate)[bookingHour] = null;
				System.out.println("Reservation at table number " + t.getTableID() + " successfully cancelled");
				return;
			}
		}

		System.out.println("Reservation not found");
	}

	private static void cancelReservation(Reservation r) {
		// get the date and time of bookin
		LocalDate bookingDate = r.getBookingTime().toLocalDate();
		LocalTime bookingTime = r.getBookingTime().toLocalTime();
		int bookingHour = bookingTime.getHour();

		Table restaurantTable = listOfTables.get(r.getTableNo());
		//error checking, to check if table really contains reservation for that date.
		if (!restaurantTable.getReservations().containsKey(bookingDate)) {
			System.out.println("Reservation not found");
			return;
		}

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
		Customer c1 = new Customer("James", "123", false);
		Customer c2 = new Customer("John", "456", false);
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

		ReservationApp.cancelReservation(LocalDateTime.of(2021, 10, 19, 13, 0), "James", "123", 2);
		ReservationApp.cancelReservation(LocalDateTime.of(2021, 10, 19, 13, 0), "Non-existant Person", "123", 2);
//		ReservationApp.cancelReservation(r1);
//		ReservationApp.cancelReservation(r2);
//		ReservationApp.cancelReservation(r3);
//		ReservationApp.cancelReservation(r4);
	}
}