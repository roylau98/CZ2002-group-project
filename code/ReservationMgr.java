import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Manager of all reservations and tables.
 * Supports addition, removal and amendment of reservations.
 * @author
 * @since 2021-10-23
 */
public class ReservationMgr {
    /**
     * Collection of all tables in the restaurant.
     */
    private static ArrayList<Table> allTables = new ArrayList<>();

    /**
     * Collection of all reservations in the restaurant.
     */
    private static ArrayList<Reservation> allReservations = new ArrayList<>();

    /**
     * Makes a reservation. Finds a suitable table that can contain the number of persons.
     * Reservation will be added to the collection and asks the table to be marked as unavailable at the specified date and time.
     * @param r Reservation.
     */
    public static boolean makeReservation(Reservation r) {
        if (r.getDate().isBefore(LocalDate.now()) || (r.getDate().isEqual(LocalDate.now()) && r.getTime().isBefore(LocalTime.now()))) {
            System.out.println("Reservation time selected is before current time");
            return false;
        }
        for (Table t : allTables) {
            if (r.getNoOfPax() > t.getCapacity())
                continue;
            if (t.checkAvailabilityAt(r.getDate(), r.getTime())) {
                t.markAsUnavailableAt(r.getDate(), r.getTime());
                r.setTableNo(allTables.indexOf(t));
                allReservations.add(r);
                System.out.println("Reservation made successfully at table number " + allTables.indexOf(t));
                return true;
            }
        }
        System.out.println("No available table found");
        return false;
    }

    /**
     * Cancels a reservation.
     * Removes the reservation from the collection and asks the table to be marked as available for other reservations at the specified date and time.
     * @param reservationNo Reservation number.
     */
    public static void cancelReservation(int reservationNo) {
        Reservation r = allReservations.get(reservationNo);
        allTables.get(r.getTableNo()).
                markAsAvailableAt(r.getDate(), r.getTime());
        allReservations.remove(reservationNo);
        System.out.println("Reservation has been cancelled");
    }

    public static void updateReservation(int reservationNo, LocalDate newDate) {
        Reservation deepCopy = new Reservation(allReservations.get(reservationNo));
        deepCopy.setDate(newDate);
        updateReservation(reservationNo, deepCopy);
    }

    public static void updateReservation(int reservationNo, LocalTime newTime) {
        Reservation deepCopy = new Reservation(allReservations.get(reservationNo));
        deepCopy.setTime(newTime);
        updateReservation(reservationNo, deepCopy);
    }

    public static void updateReservation(int reservationNo, int newNoOfPax) {
        Reservation deepCopy = new Reservation(allReservations.get(reservationNo));
        deepCopy.setNoOfPax(newNoOfPax);
        updateReservation(reservationNo, deepCopy);
    }

    public static void updateReservation(int reservationNo, Customer newCust) {
        allReservations.get(reservationNo).setCustomer(newCust);
        System.out.println("Customer details updated");
    }

    /**
     * Updates a reservation.
     * @param oldReservationNo Reservation number of the outdated reservation to be removed.
     * @param newReservation Updated reservation to be added.
     */
    private static void updateReservation(int oldReservationNo, Reservation newReservation) {
        System.out.println("Reservation updated by performing the actions below");
        Reservation oldReservation = allReservations.get(oldReservationNo);
        cancelReservation(oldReservationNo);
        if (!makeReservation(newReservation)) {
            makeReservation(oldReservation);
            System.out.println("Update unsuccessful. Previous reservation restored.");
        }
        System.out.println("Update completed");
    }

    /**
     * Prints a list of all active reservations to the console.
     */
    public static void viewAllReservations() {
        System.out.println("---List of all reservations---");
        for (Reservation r : allReservations)
            System.out.println("Reservation " + allReservations.indexOf(r) + ": " + r.toString());
        System.out.println("----------");
    }

    /**
     * Prints a list of all the tables and their respective availabilities to the console.
     */
    public static void viewAllTables() {
        System.out.println("---List of all tables---");
        for (Table t : allTables)
            System.out.println("Table " + allTables.indexOf(t) + ": " + t.toString());
        System.out.println("----------");
    }

    public static void addTables() {
        allTables.add(new Table(2));
        allTables.add(new Table(2));
        allTables.add(new Table(4));
        allTables.add(new Table(4));
    }
}