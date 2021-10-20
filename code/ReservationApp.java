import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Application class for reservation
 * Handles reservation addition, removal, updating and finding.
 *
 * @author
 * @since 2021-10-19
 */
public class ReservationApp {
	private static ArrayList<Table> listOfTables = new ArrayList<>();

    /**
     * Adds a reservation to the table's records
     *
     * @param r The reservation object
     */
    public static void addReservation(Reservation r) {
        if (r.getDate().isBefore(LocalDate.now()) || (r.getDate().isEqual(LocalDate.now()) && r.getTime().isBefore(LocalTime.now()))) {
            System.out.println("Reservation time selected is before current time");
            return;
        }
        for (Table t : listOfTables) {
            if (r.getNoOfPax() > t.getCapacity()) {
                continue;
            }
            if (t.isAvailableAt(r.getDate(), r.getTime())) {
                t.insertReservation(r);
                return;
            }
        }
        System.out.println("No available table found");
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

    /**
     * Cancels the reservation
     *
     * @param r The reservation object
     */
    private static void cancelReservation(Reservation r) {
        if (r == null) {
            System.out.println("Reservation cannot be found");
            return;
        }
        Table restaurantTable = listOfTables.get(r.getTableNo());
        restaurantTable.removeReservation(r.getDate(), r.getTime());
    }

    /**
     * Update the reservation
     *
     * @param oldReservation To remove the old reservation object
     * @param newReservation To add the new reservation object
     */
    public static void updateReservation(Reservation oldReservation, Reservation newReservation) {
        if (oldReservation == null) {
            System.out.println("Previous reservation not found, New reservation not added");
            return;
        }
        System.out.println("Reservation updated by performing the actions below");
        cancelReservation(oldReservation);
        addReservation(newReservation);
    }

    // for testing of RestaurantApp's functionality
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

        Reservation r1 = new Reservation(LocalDate.of(2021, 10, 30), LocalTime.of(14, 0), 2, c1);
        Reservation r2 = new Reservation(LocalDate.of(2021, 10, 30), LocalTime.of(14, 0), 2, c2);
        Reservation r3 = new Reservation(LocalDate.of(2021, 10, 30), LocalTime.of(14, 0), 2, c3);
//		Reservation r3 = new Reservation(LocalDateTime.of(2021, 10, 19, 13, 0), 2, c1);
//		Reservation r4 = new Reservation(LocalDateTime.of(2021, 10, 19, 13, 0), 2, c2);

        ReservationApp.addReservation(r1);
        ReservationApp.addReservation(r2);
//		ReservationApp.addReservation(r3);
//		ReservationApp.addReservation(r4);

        Reservation s1 = ReservationApp.findReservation(LocalDateTime.of(2021, 10, 30, 14, 0), "James", "123", 2);
        Reservation s2 = ReservationApp.findReservation(LocalDateTime.of(2021, 10, 30, 14, 0), "Mark", "123", 2);
        ReservationApp.updateReservation(r2, r3);
        ReservationApp.cancelReservation(r3);

//		ReservationApp.cancelReservation(r2);
//		ReservationApp.cancelReservation(r3);
//		ReservationApp.cancelReservation(r4);
	}
}