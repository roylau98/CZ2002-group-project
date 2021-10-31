import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ReservationApp implements Serializable {
    private transient Scanner scanner;
    private final ReservationMgr reservationMgr = new ReservationMgr();

    public void startReservationApp() {
        scanner = new Scanner(System.in);
        reservationMgr.removeOutdatedReservations();
        System.out.println("Welcome to the ReservationApp.");
        boolean cont = true;
        while (cont) {
            System.out.println("Please select one of the options below:\n" +
                    "1. Make a new reservation\n" +
                    "2. Cancel an existing reservation\n" +
                    "3. Amend an existing reservation\n" +
                    "4. View the list of reservations\n" +
                    "5. View the list of tables\n" +
                    "6. Check table availability\n" +
                    "7. Exit this application and return to the main menu");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    updateReservation();
                    break;
                case 4:
                    reservationMgr.viewAllReservations();
                    break;
                case 5:
                    reservationMgr.viewAllTables();
                    break;
                case 6:
                    reservationMgr.checkAvailabilityAt(askUserForDate(), askUserForTime(), askUserForPax());
                    break;
                case 7:
                    cont = false;
                    break;
            }
        }
    }

    private void makeReservation() {
        System.out.println("Please enter the following details below:");
        LocalDate date = askUserForDate();
        LocalTime time = askUserForTime();
        int noOfPax = askUserForPax();
        Customer customer = askUserForCustomerDetails();
        reservationMgr.makeReservation(new Reservation(date, time, noOfPax, customer));
    }

    private void cancelReservation() {
        reservationMgr.viewAllReservations();
        System.out.println("Which reservation would you like to cancel?");
        int temp = scanner.nextInt();
        scanner.nextLine();
        reservationMgr.cancelReservation(temp);
    }

    private void updateReservation() {
        reservationMgr.viewAllReservations();
        System.out.println("Which reservation would you like to amend?");
        int reservationNoToUpdate = scanner.nextInt();
        scanner.nextLine();

        System.out.println("What would you like to amend?\n" +
                "1. Date of reservation\n" +
                "2. Time of reservation\n" +
                "3. Number of persons\n" +
                "4. Customer details");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                LocalDate date = askUserForDate();
                reservationMgr.updateReservation(reservationNoToUpdate, date);
                break;
            case 2:
                LocalTime time = askUserForTime();
                reservationMgr.updateReservation(reservationNoToUpdate, time);
                break;
            case 3:
                int noOfPax = askUserForPax();
                reservationMgr.updateReservation(reservationNoToUpdate, noOfPax);
                break;
            case 4:
                Customer updatedCustomer = askUserForCustomerDetails();
                reservationMgr.updateReservation(reservationNoToUpdate, updatedCustomer);
                break;
        }
    }

    private LocalDate askUserForDate() {
        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Month: ");
        int month = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Date: ");
        int date = scanner.nextInt();
        scanner.nextLine();

        return LocalDate.of(year, month, date);
    }

    private LocalTime askUserForTime() {
        System.out.print("Hour: ");
        int hour = scanner.nextInt();
        scanner.nextLine();

        return LocalTime.of(hour, 0);
    }

    private int askUserForPax() {
        System.out.print("Number of persons: ");
        int noOfPax = scanner.nextInt();
        scanner.nextLine();

        return noOfPax;
    }

    private Customer askUserForCustomerDetails() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Gender (Male/Female): ");
        char entry = scanner.nextLine().charAt(0);
        boolean isMale = entry == 'M' || entry == 'm';
        Sex gender = isMale ? Sex.MALE : Sex.FEMALE;

        System.out.print("Contact number: ");
        String contactNumber = scanner.nextLine();

        System.out.print("Member (Yes/No): ");
        entry = scanner.nextLine().charAt(0);
        boolean isMember = entry == 'Y' || entry == 'y';

        return new Customer(name, gender, contactNumber, isMember);
    }
}
