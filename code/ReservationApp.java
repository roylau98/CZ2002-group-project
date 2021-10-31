import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReservationApp implements Serializable {
    private transient Scanner scanner;
    private final ReservationMgr reservationMgr = new ReservationMgr();

    public void startReservationApp() {
        scanner = new Scanner(System.in);
        reservationMgr.removeOutdatedReservations();
        System.out.println("Welcome to the ReservationApp.");
        boolean cont = true;
        int choice = 0;
        while (cont) {
            boolean error = true;
            while (cont) {
                do {
                    try {
                        System.out.println("Please select one of the options below:\n" +
                                "1. Make a new reservation\n" +
                                "2. Cancel an existing reservation\n" +
                                "3. Amend an existing reservation\n" +
                                "4. View the list of reservations\n" +
                                "5. View the list of tables\n" +
                                "6. Check availability at a specified date and time\n" +
                                "7. Exit this application and return to the main menu");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        error = false;
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Invalid input for options. (Valid values 1 - 7)\n");
                        error = true;
                        scanner.nextLine();
                    }
                } while (error);
                if (choice > 7 || choice < 1) {
                    System.out.println("Invalid value for options. (Valid values 1 - 7)\n");
                }
                else {
                    cont = false;
                }
            }
            cont = true;

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
        boolean error = true;
        boolean cont = true;
        LocalDate localDate = LocalDate.now();
        int year = 0;
        int month = 0;
        int date = 0;
        while (cont) {
            int currentYear = localDate.getYear();
            do {
                try {
                    System.out.print("Year: ");
                    year = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                }
                catch (InputMismatchException e){
                    System.out.println("Invalid input. (Valid values: " + currentYear + " onwards)");
                    scanner.nextLine();
                    error = true;
                }
            } while (error);
            if (year < currentYear) {
                System.out.println("Invalid value. (Valid values: " + currentYear + " onwards)");
                cont = true;
            }
            else {
                cont = false;
            }
        }
        cont = true;
        while (cont) {
            int currentMonth = localDate.getMonthValue();
            Month currentMonthEnum = localDate.getMonth();
            do {
                try {
                    System.out.println("Month (Jan:1, Feb:2, Mar:3, Apr:4, May:5, Jun:6, Jul:7, Aug:8, Sep:9, Oct:10, Nov:11, Dec:12): " );
                    month = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                }
                catch (InputMismatchException e){
                    System.out.println("Invalid input. (Valid values: " + currentMonthEnum + " onwards)");
                    scanner.nextLine();
                    error = true;
                }
            } while (error);
            if (month < currentMonth) {
                System.out.println("Invalid value. (Valid values: " + currentMonthEnum + " onwards)");
                cont = true;
            }
            else {
                cont = false;
            }
        }
        cont = true;
        while (cont) {
            int currentDate = localDate.getDayOfMonth(); // day 1 == 1;
            int maxDay = localDate.lengthOfMonth();
            do {
                try {
                    System.out.print("Date: ");
                    date = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                }
                catch (InputMismatchException e){
                    System.out.println("Invalid input. (Valid values: From " + currentDate + " onwards to " + maxDay + ")");
                    scanner.nextLine();
                    error = true;
                }
            } while (error);
            if (date < currentDate || date > maxDay) {
                System.out.println("Invalid value. (Valid values: From " + currentDate + " onwards to " + maxDay + ")");
                cont = true;
            }
            else {
                cont = false;
            }
        }
        return LocalDate.of(year, month, date);
    }

    private LocalTime askUserForTime() {
        boolean error = true;
        boolean cont = true;
        LocalTime localTime = LocalTime.now();
        int hour = 0;
        while (cont) {
            int currentHour = localTime.getHour();
            do {
                try {
                    System.out.print("Hour: ");
                    hour = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                }
                catch (InputMismatchException e){
                    System.out.println("Invalid input. (Valid values: " + currentHour + " onwards)");
                    scanner.nextLine();
                    error = true;
                }
            } while (error);
            if (hour < currentHour || hour > 23) {
                System.out.println("Invalid value. (Valid values: " + currentHour + " - 23)");
                cont = true;
            }
            else {
                cont = false;
            }
        }
        return LocalTime.of(hour, 0);
    }

    private int askUserForPax() {
        boolean error = true;
        boolean cont = true;
        int MAX_TABLE_SIZE = 50;
        int noOfPax = 0;
        while (cont) {
            do {
                try {
                    System.out.print("Number of persons: ");
                    noOfPax = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                }
                catch (InputMismatchException e){
                    System.out.println("Invalid input. (Valid values: Enter a number from 1 to " + MAX_TABLE_SIZE +")");
                    scanner.nextLine();
                    error = true;
                }
            } while (error);
            if (noOfPax < 1 || noOfPax > MAX_TABLE_SIZE) {
                System.out.println("Invalid value. (Valid values: 1 - " + MAX_TABLE_SIZE + " )");
                cont = true;
            }
            else {
                cont = false;
            }
        }
        return noOfPax;
    }

    private Customer askUserForCustomerDetails() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        char entry;
        int contactNumber_int = 0;
        Sex gender = Sex.MALE;
        boolean isMember = false;
        boolean cont = true;
        boolean error = true;

        while (cont) {
            System.out.print("Gender (Male/Female): ");
            entry = scanner.nextLine().charAt(0);
            if (entry == 'M' || entry == 'm' || entry == 'F' || entry == 'f') {
                boolean isMale = entry == 'M' || entry == 'm';
                gender = isMale ? Sex.MALE : Sex.FEMALE;
                cont = false;
            }
            else {
                cont = true;
            }
        }
        do {
            try {
                System.out.print("Contact number: ");
                contactNumber_int = scanner.nextInt();
                scanner.nextLine();
                error = false;
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input. (Valid values: Enter a number from 1 to 9)");
                scanner.nextLine();
                error = true;
            }
        } while (error);
        String contactNumber = Integer.toString(contactNumber_int);

        while (cont) {
            System.out.print("Member (Yes/No): ");
            entry = scanner.nextLine().charAt(0);
            if (entry == 'Y' || entry == 'y' || entry == 'N' || entry == 'n') {
                isMember = entry == 'Y' || entry == 'y';
                cont = false;
            }
            else {
                cont = true;
            }
        }

        return new Customer(name, gender, contactNumber, isMember);
    }
}
