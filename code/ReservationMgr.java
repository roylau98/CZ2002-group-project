import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

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
    private ArrayList<Table> allTables;

    /**
     * Collection of all reservations in the restaurant.
     */
    private ArrayList<Reservation> allReservations;

    public ReservationMgr() {
        this.allTables = new ArrayList<>();
        this.allReservations = new ArrayList<>();
        allTables.add(new Table(1,2));
        allTables.add(new Table(2,2));
        allTables.add(new Table(3,4));
        allTables.add(new Table(4,4));
        createScheduler();
    }

    public int checkArrayListReservationSize(){
        return allReservations.size();
    }

    /**
     * Makes a reservation. Finds a suitable table that can contain the number of persons.
     * Reservation will be added to the collection and asks the table to be marked as unavailable at the specified date and time.
     * @param r Reservation.
     */
    public boolean makeReservation(Reservation r) {
        /*
        if (r.getDate().isBefore(LocalDate.now()) || (r.getDate().isEqual(LocalDate.now()) && r.getTime().isBefore(LocalTime.now()))) {
            System.out.println("Reservation time selected is before current time");
            return false;
        }
        */
        for (Table t : allTables) {
            if (r.getNoOfPax() > t.getCapacity())
                continue;
            if (t.checkAvailabilityAt(r.getDate(), r.getTime())) {
                t.markAsUnavailableAt(r.getDate(), r.getTime());
                r.setTableNo(t.getTableID());
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
        allTables.get(getIndexOfTable(r.getTableNo())).markAsAvailableAt(r.getDate(), r.getTime());
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

    public void removeOutdatedReservations() {
        allReservations.sort(Comparator.comparing(Reservation::getDate).thenComparing(Reservation::getTime));
        for (Reservation r : allReservations) {
            if (r.getDate().isBefore(LocalDate.now())) {
                Table table = allTables.get(getIndexOfTable(r.getTableNo()));
                table.markAsAvailableAt(r.getDate(), r.getTime());
                allReservations.remove(r);
            } else {
                break;
            }
        }
    }

    public void removeNoShowReservations() {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int i;
        for (Reservation r : allReservations) {
            if (LocalTime.now().isAfter(r.getTime().plusMinutes(15)) && !r.getCustArrived()) {
                System.out.println("Reservation " + r + " has expired and will be automatically removed.");
                temp.add(allReservations.indexOf(r));
            }
        }

        for (i=0; i<temp.size(); i++) {
            Reservation toRemove = allReservations.get(temp.get(i));
            Table table = allTables.get(getIndexOfTable(toRemove.getTableNo()));
            table.markAsAvailableAt(toRemove.getDate(), toRemove.getTime());
            allReservations.remove(toRemove);
        }
    }

    public void createScheduler() {
        //returns 0 to 59;
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

    public boolean customerArrivedAt(int tableNo) {
        for (Reservation r : allReservations) {
            if (r.getTableNo() == tableNo && r.getDate().isEqual(LocalDate.now()) && r.getTime().equals(LocalTime.now())) {
                r.setCustArrived(true);
                return true;
//                return r.getCustomer();
            }
        }
        return false;
    }

    public ArrayList<Table> getAllTables() {
        return allTables;
    }

    private int getIndexOfTable(int tableID) {
        for (Table t: allTables) {
            if (t.getTableID()==tableID) {
                return allTables.indexOf(t);
            }
        }
        return -1;
    }

    public boolean checkIfTableHasReservationNow(int tableNo) {
        for (Reservation r: allReservations) {
            if (r.getTableNo() == tableNo) {

            }
        }

    }

    public Reservation getReservationAtTableNow(int tableNo) {
        for (Reservation r: allReservations) {
            if (r.getTableNo() == tableNo) {

            }
        }

    }


}