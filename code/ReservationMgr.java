import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Manager of all reservations and tables.
 * Supports addition, removal and amendment of reservations.
 * @author
 * @since 2021-10-23
 */
public class ReservationMgr implements Serializable {
    /**
     * Collection of all tables in the restaurant.
     */
    private final ArrayList<Table> allTables;
    /**
     * Collection of all reservations in the restaurant.
     */
    private final ArrayList<Reservation> allReservations;

    public ReservationMgr() {
        this.allTables = new ArrayList<>();
        this.allReservations = new ArrayList<>();
        addTables();
        createScheduler();
    }

    private void addTables() {
        allTables.add(new Table(2));
        allTables.add(new Table(2));
        allTables.add(new Table(4));
        allTables.add(new Table(4));
    }

    public int getTotalNoOfReservations(){
        return allReservations.size();
    }

    /**
     * Makes a reservation. Finds a suitable table that can contain the number of persons.
     * Reservation will be added to the collection and asks the table to be marked as unavailable at the specified date and time.
     * @param r Reservation.
     */
    public boolean makeReservation(Reservation r) {
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
    public void cancelReservation(int reservationNo) {
        Reservation r = allReservations.get(reservationNo);
        allTables.get(r.getTableNo()).markAsAvailableAt(r.getDate(), r.getTime());
        allReservations.remove(reservationNo);
        System.out.println("Reservation has been cancelled");
    }

    public void updateReservation(int reservationNo, LocalDate newDate) {
        Reservation deepCopy = new Reservation(allReservations.get(reservationNo));
        deepCopy.setDate(newDate);
        updateReservation(reservationNo, deepCopy);
    }

    public void updateReservation(int reservationNo, LocalTime newTime) {
        Reservation deepCopy = new Reservation(allReservations.get(reservationNo));
        deepCopy.setTime(newTime);
        updateReservation(reservationNo, deepCopy);
    }

    public void updateReservation(int reservationNo, int newNoOfPax) {
        Reservation deepCopy = new Reservation(allReservations.get(reservationNo));
        deepCopy.setNoOfPax(newNoOfPax);
        updateReservation(reservationNo, deepCopy);
    }

    public void updateReservation(int reservationNo, Customer newCust) {
        allReservations.get(reservationNo).setCustomer(newCust);
        System.out.println("Customer details updated");
    }

    /**
     * Updates a reservation.
     * @param oldReservationNo Reservation number of the outdated reservation to be removed.
     * @param newReservation Updated reservation to be added.
     */
    private void updateReservation(int oldReservationNo, Reservation newReservation) {
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
    public void viewAllReservations() {
        System.out.println("---List of all reservations---");
        for (Reservation r : allReservations)
            System.out.println("Reservation " + allReservations.indexOf(r) + ": " + r.toString());
        System.out.println("----------");
    }

    /**
     * Prints a list of all the tables and their respective availabilities to the console.
      */
    public void viewAllTables() {
        System.out.println("---List of all tables---");
        for (Table t : allTables)
            System.out.println("Table " + allTables.indexOf(t) + ": " + t.toString());
        System.out.println("----------");
    }

    public boolean checkAvailabilityAt(LocalDate date, LocalTime time, int noOfPax) {
        for (Table t : allTables) {
            if (noOfPax > t.getCapacity())
                continue;
            if (t.checkAvailabilityAt(date, time))
                System.out.println("Table " + allTables.indexOf(t) + " is available for reservation at " + date + " " + time);
                return true;
        }
        System.out.println("No tables are available for reservation at " + date + time);
        return false;
    }

    public void removeNoShowReservations() {
        allReservations.sort(Comparator.comparing(Reservation::getDate).thenComparing(Reservation::getTime));
        for (ListIterator<Reservation> it = allReservations.listIterator(); it.hasNext(); ) {
            Reservation r = it.next();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime reservationDateTime = LocalDateTime.of(r.getDate(), r.getTime());
            if (now.isAfter(reservationDateTime.plusMinutes(15)) && !r.getCustArrived()) {
                System.out.println(r + " has expired and will be automatically removed.");
                Table t = allTables.get(r.getTableNo());
                t.markAsAvailableAt(r.getDate(), r.getTime());
                it.remove();
            }
        }
    }

    public void createScheduler() {
        Timer timerSchedule = new Timer();
        TimerTask taskToRun = new ExpiredReservationsRemover(this);

        int minutesNow = LocalTime.now().getMinute();
        int offset;
        if (minutesNow < 15) {
            offset = (15 - minutesNow) * 1000 * 60;
        } else {
            offset = (75 - minutesNow) * 1000 * 60;
        }
        timerSchedule.scheduleAtFixedRate(taskToRun, offset, 3600000);

    }

    public void viewTablesWithReservationsNow() {
        System.out.println("---List of tables with reservations now---");
        for (Table t : allTables) {
            // tables with a reservation now are not available for another reservation
            if (!t.checkAvailabilityAt(LocalDate.now(), LocalTime.now()))
                System.out.println("Table " + allTables.indexOf(t) + ": " + t.toString());
        }
        System.out.println("----------");
    }

    public Customer getCustomerAt(int tableNo) {
        for (Reservation r : allReservations) {
            if (r.getTableNo() == tableNo && r.getDate().isEqual(LocalDate.now()) && r.getTime().getHour() == LocalTime.now().getHour()) {
                r.setCustArrived(true);
                return r.getCustomer();
            }
        }
        return null;
    }

    public void removeReservationAfterPayment(int tableNo) {
        for (ListIterator<Reservation> it = allReservations.listIterator(); it.hasNext(); ) {
            Reservation r = it.next();
            if (r.getTableNo() == tableNo && r.getDate().isEqual(LocalDate.now()) && r.getTime().getHour() == LocalTime.now().getHour()) {
                allTables.get(r.getTableNo()).markAsAvailableAt(r.getDate(), r.getTime());
                it.remove();
            }
        }
    }
}