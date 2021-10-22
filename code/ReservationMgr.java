import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Application class for reservation
 * Handles reservation addition, removal, updating and finding.
 *
 * @author
 * @since 2021-10-19
 */
public class ReservationMgr {
    /**
     * A list of all tables in the restaurant.
     */
    private static ArrayList<Table> allTables = new ArrayList<>();
    private static ArrayList<Reservation> allReservations = new ArrayList<>();

    /**
     * Adds a reservation to the table's records
     *
     * @param r The reservation object
     */
    public static void makeReservation(Reservation r) {
        if (r.getDate().isBefore(LocalDate.now()) || (r.getDate().isEqual(LocalDate.now()) && r.getTime().isBefore(LocalTime.now()))) {
            System.out.println("Reservation time selected is before current time");
            return;
        }
        for (Table t : allTables) {
            if (r.getNoOfPax() > t.getCapacity())
                continue;
            if (t.checkAvailabilityAt(r.getDate(), r.getTime())) {
                t.markAsUnavailableAt(r.getDate(), r.getTime());
                r.setTableNo(allTables.indexOf(t));
                allReservations.add(r);
                System.out.println("Reservation made successfully at table number " + allTables.indexOf(t));
                return;
            }
        }
        System.out.println("No available table found");
    }

    /**
     * Finds the reservation based on the customer's name, contactNo, date and time of reservation,
     * and the number of pax.
     *
     * @param dt        preferred date and time for the reservation
     * @param name      customer's name
     * @param contactNo customer's contact number
     * @param noOfPax   number of persons
     * @return reservation object
     */
//    public static Reservation findReservation(LocalDateTime dt, String name, String contactNo, int noOfPax) {
//        for (Table t : allTables) {
//            Reservation r = t.comparesReservation(dt, name, contactNo, noOfPax);
//            if (r != null)
//                return r;
//        }
//        return null;
//    }

    /**
     * Cancels the reservation
     *
     * @param r The reservation object
     */
    public static void cancelReservation(int reservationNo) {
        Reservation r = allReservations.get(reservationNo);
        allTables.get(r.getTableNo()).
                markAsAvailableAt(r.getDate(), r.getTime());
        allReservations.remove(reservationNo);
        System.out.println("Reservation has been cancelled");
    }

    /**
     * Update the reservation
     *
     * @param oldReservation To remove the old reservation object
     * @param newReservation To add the new reservation object
     */
    public static void updateReservation(int oldReservationNo, Reservation newReservation) {
        System.out.println("Reservation updated by performing the actions below");
        cancelReservation(oldReservationNo);
        makeReservation(newReservation);
        System.out.println("Update completed");
    }

    public static void viewAllReservations() {
        System.out.println("---List of all reservations---");
        for (Reservation r : allReservations)
            System.out.println("Reservation " + allReservations.indexOf(r) + ": " + r.toString());
        System.out.println("----------");
    }

    public static void viewAllTables() {
        System.out.println("---List of all tables---");
        for (Table t : allTables)
            System.out.println("Table " + allTables.indexOf(t) + ": " + t.toString());
        System.out.println("----------");
    }

    public static void addTable(int capacity) {
        allTables.add(new Table(capacity));
    }

    // for testing of RestaurantApp's functionality
    public static void main(String[] args) {
        Customer c1 = new Customer("James", Sex.MALE, "123", false);
        Customer c2 = new Customer("John", Sex.MALE, "456", false);
        Customer c3 = new Customer("Mark", Sex.MALE, "789", false);
        Customer c4 = new Customer("Sally", Sex.FEMALE, "789", false);
        ReservationMgr.allTables.add(new Table(2));
        ReservationMgr.allTables.add(new Table(2));
        ReservationMgr.allTables.add(new Table(4));
        ReservationMgr.allTables.add(new Table(4));

        Reservation r1 = new Reservation(LocalDate.of(2021, 10, 30), LocalTime.of(14, 0), 2, c1);
        Reservation r2 = new Reservation(LocalDate.of(2021, 10, 30), LocalTime.of(14, 0), 2, c2);
        Reservation r3 = new Reservation(LocalDate.of(2021, 10, 30), LocalTime.of(14, 0), 2, c3);
//		Reservation r3 = new Reservation(LocalDateTime.of(2021, 10, 19, 13, 0), 2, c1);
//		Reservation r4 = new Reservation(LocalDateTime.of(2021, 10, 19, 13, 0), 2, c2);

        ReservationMgr.makeReservation(r1);
        ReservationMgr.makeReservation(r2);
		ReservationMgr.makeReservation(r3);
        ReservationMgr.viewAllReservations();
        ReservationMgr.viewAllTables();
//		ReservationApp.makeReservation(r4);

//        Reservation s1 = ReservationMgr.findReservation(LocalDateTime.of(2021, 10, 30, 14, 0), "James", "123", 2);
//        Reservation s2 = ReservationMgr.findReservation(LocalDateTime.of(2021, 10, 30, 14, 0), "Mark", "123", 2);
//        ReservationMgr.updateReservation(r2, r3);
        ReservationMgr.cancelReservation(2);

//		ReservationApp.cancelReservation(r2);
//		ReservationApp.cancelReservation(r3);
//		ReservationApp.cancelReservation(r4);
//		System.out.println(listOfTables.get(0).getReservations().toString());
    }
}