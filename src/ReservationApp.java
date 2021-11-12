import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interface of the Reservation App which has the option of create/remove etc. Reservation
 * <p>
 *
 * @since 2021-11-5
 */
public class ReservationApp implements Serializable,AppInterface {
    /**
     * Object Manager of Reservation
     */
    private final ReservationMgr reservationMgr;
    /**
     * Capacity of table in restaurant
     */
    private final int MAX_TABLE_CAPACITY = 10;
    private transient Scanner scanner;
    /**
     * Year for reservation booking
     */
    private int year = 0;
    /**
     * Month for reservation booking
     */
    private int month = 0;
    /**
     * Date for reservation booking
     */
    private int date = 0;
    /**
     * Hours for reservation booking
     */
    private int hour = 0;

    /**
     * Class Constructor
     */
    public ReservationApp(ReservationMgr reservationMgrEx) {
        reservationMgr = reservationMgrEx;
    }

    /**
     * Interface of the ReservationApp with several options available
     */
    public void openOptions() {
        scanner = new Scanner(System.in);
        reservationMgr.removeNoShowReservations();
        reservationMgr.createScheduler();
        boolean cont = true;
        int choice = 0;
        while (cont) {
            boolean error = true;
            while (cont) {
                do {
                    try {
                        System.out.print("\nReservation App\n" +
                                "Please select one of the options below:\n" +
                                "1. Make a new reservation\n" +
                                "2. Cancel an existing reservation\n" +
                                "3. Amend an existing reservation\n" +
                                "4. View the list of reservations\n" +
                                "5. Check availability at a specified date and time\n" +
                                "6. Exit this application and return to the previous page\n" +
                                "Enter your choice: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        error = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for options. (Valid values 1 - 7)\n");
                        error = true;
                        scanner.nextLine();
                    }
                } while (error);
                if (choice > 6 || choice < 1) {
                    System.out.println("Invalid value for options. (Valid values 1 - 7)\n");
                } else {
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
                    printAll();
                    break;
                case 5:
                    reservationMgr.checkAvailabilityAt(askUserForDate(), askUserForTime(), askUserForPax());
                    break;
                case 6:
                    cont = false;
                    break;
            }
        }
    }
    /**
     * Print all the existing reservation
     */
    public void printAll()
    {
    	reservationMgr.viewAllReservations();
    }
    /**
     * Create new Reservation
     */
    private void makeReservation() {
    	boolean error = true;
    	LocalTime time;
        System.out.println("Please enter the following details below:");
        LocalDate date = askUserForDate();
        do {
        	try {
        		time = askUserForTime();
        		error = false;
        	}
        	catch (DateTimeException e) {
        		System.out.println("Error in reservation, did not reserve. Try reserving again.");
        		return;
        	}
        } while (error);
        int noOfPax = askUserForPax();
        Customer customer = askUserForCustomerDetails();
        reservationMgr.makeReservation(new Reservation(date, time, noOfPax, customer));
    }

    /**
     * Cancel existing Reservation
     */
    private void cancelReservation() {
        if (reservationMgr.getTotalNoOfReservations() == 0) {
            System.out.println("No Reservations in system.");
            return;
        }
        boolean cont = true;
        boolean error = true;
        while (cont) {
            int temp = 0;
            do {
                try {
                    reservationMgr.viewAllReservations();
                    System.out.println("Which reservation would you like to cancel?");
                    temp = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for options. Enter integers.");
                    error = true;
                    scanner.nextLine();
                }
            } while (error);
            if ((reservationMgr.getTotalNoOfReservations() - 1) < temp || temp < 0) {
                System.out.println("Invalid input. Try again.");
                cont = true;
            } else {
                cont = false;
                reservationMgr.cancelReservation(temp);
            }
        }
    }

    /**
     * Update details of existing Reservation
     */
    private void updateReservation() {
        if (reservationMgr.getTotalNoOfReservations() == 0) {
            System.out.println("No Reservations in system.");
            return;
        }
        boolean cont = true;
        boolean error = true;
        int choice = 0;
        int reservationNoToUpdate = 0;
        while (cont) {
            do {
                try {
                    reservationMgr.viewAllReservations();
                    System.out.println("Which reservation would you like to amend?");
                    reservationNoToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for options. Enter integers.");
                    error = true;
                    scanner.nextLine();
                }
            } while (error);
            if (((reservationMgr.getTotalNoOfReservations() - 1) < reservationNoToUpdate) || reservationNoToUpdate < 0) {
                System.out.println("Invalid input. Try again.");
                cont = true;
            } else {
                cont = false;
            }
        }
        cont = true;
        error = true;
        while (cont) {
            do {
                try {
                    System.out.println("What would you like to amend?\n" +
                            "1. Date/time of reservation\n" +
                            "2. Number of persons\n" +
                            "3. Customer details");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for options. Enter integers.");
                    error = true;
                    scanner.nextLine();
                }
            } while (error);

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid value. (Valid value: 1 - 3)\n");
            } else {
                cont = false;
            }
        }

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

    /**
     * Scanner to ask for user input (Date) with error checking
     *
     * @return  the date input by user
     */
    private LocalDate askUserForDate() {    
        boolean cont = true;
        boolean error = true;
        while (cont) {
            do {
                try {
                    System.out.print("Year: ");
                    year = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. (Valid values: " + LocalDate.now().getYear() + " onwards)");
                    scanner.nextLine();
                    error = true;
                }
            } while (error);
            if (year < LocalDate.now().getYear()) {
                System.out.println("Invalid value. (Valid values: " + LocalDate.now().getYear() + " onwards)");
                cont = true;
            } else {
                cont = false;
            }
        }
        cont = true;
        while (cont) {
            do {
                try {
                    System.out.print("Month (Jan:1, Feb:2, Mar:3, Apr:4, May:5, Jun:6, Jul:7, Aug:8, Sep:9, Oct:10, Nov:11, Dec:12): ");
                    month = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                } catch (InputMismatchException e) {
                    if (LocalDate.now().getYear() == year) {
                        System.out.println("Invalid input. (Valid values: " + LocalDate.now().getMonth() + " onwards)");
                    } else {
                        System.out.println("Invalid input. (Valid values: 1 - 12)");
                    }
                    scanner.nextLine();
                    error = true;
                }
            } while (error);
            if (month < LocalDate.now().getMonthValue() && year == LocalDate.now().getYear()) {
                System.out.println("Invalid value. (Valid values: " + LocalDate.now().getMonth() + " onwards)");
            } else if (month < 1 || month > 12) {
                System.out.println("Invalid Value. (Value values: 1 - 12)");
            } else {
                cont = false;
            }
        }
        cont = true;
        while (cont) {
        	LocalDate lastDayOfMonth = LocalDate.of(year, month, 1);
        	int maxDay = lastDayOfMonth.getMonth().length(lastDayOfMonth.isLeapYear());
            do {

                try {
                    System.out.print("Date: ");
                    date = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. (Valid values: From 1 onwards to " + maxDay + ")");
                    scanner.nextLine();
                    error = true;
                }
            } while (error);

            if (year == LocalDate.now().getYear() && month == LocalDate.now().getMonthValue()) {
                if (date < LocalDate.now().getDayOfMonth() || date > maxDay) {
                    System.out.println("Invalid value. (Valid values: From " + LocalDate.now().getDayOfMonth() + " onwards to " + maxDay + ")");
                    cont = true;
                } else if (LocalTime.now().getHour() == 23 && date <= LocalDate.now().getDayOfMonth()){
                    System.out.println("Invalid value. (Valid values: From " + (LocalDate.now().getDayOfMonth() + 1) + " onwards to " + maxDay + ")");
                } else {
                    cont = false;
                }
            }  else {
                if (date < 1 || date > maxDay) {
                    System.out.println("Invalid value. (Valid values: From 1 - " + maxDay + ")");
                    cont = true;
                } else {
                    cont = false;
                }
            }
        }
        return LocalDate.of(year, month, date);
    }

    /**
     * Scanner to ask for user input (Time) with error checking
     *
     * @return  the time input by user
     */
    private LocalTime askUserForTime() {
        boolean cont = true;
        boolean error = true;
        cont = true;
        while (cont) {
            do {
                try {
                    System.out.print("Hour: ");
                    hour = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. (Enter an integer)");
                    scanner.nextLine();
                    error = true;
                }
            } while (error);
            if (hour < 0 || hour > 23) {
                System.out.println("Invalid value. (Valid values: 0 - 23)");
            } 
            else if (year == LocalDate.now().getYear() && month == LocalDate.now().getMonthValue() && date == LocalDate.now().getDayOfMonth() && hour <= LocalTime.now().getHour()) {
            	if (LocalTime.now().getHour() == 23) { //time exceeded, hour rolled over.
            		System.out.println("Time rolled over, re-book another date from tomorrow.");
            		return LocalTime.of(-1, -1); // to throw dateTimeException
            	}
            	else {
            		System.out.println("Invalid value. (Valid values: " + (LocalTime.now().getHour() + 1) + " - 23)");
            	}
            }
            else if (year < LocalDate.now().getYear()) { //time exceeded, year rolled over.
            	System.out.println("Time rolled over, re-book another date from next year.");
            	return LocalTime.of(-1, -1); // to throw dateTimeException
            }
            else if (year == LocalDate.now().getYear() && month < LocalDate.now().getMonthValue()) { //time exceeded, month rolled over.
            	System.out.println("Time rolled over, re-book another date from next month.");
            	return LocalTime.of(-1, -1); // to throw dateTimeException
            }
            else if (year == LocalDate.now().getYear() && month == LocalDate.now().getMonthValue() && date < LocalDate.now().getDayOfMonth()) { //time exceeded, date rolled over.
            	System.out.println("Time rolled over, re-book another date from next day.");
				return LocalTime.of(-1, -1); // to throw dateTimeException
            }
        	else {
                cont = false;
            }
        }
        return LocalTime.of(hour,  0);
    }

    /**
     * Scanner to ask for user input (noOfPax) with error checking
     *
     * @return  the no. of people 
     */
    private int askUserForPax() {
        boolean cont = true;
        boolean error = true;
        int noOfPax = 0;
        while (cont) {
            do {
                try {
                    System.out.print("Number of persons: ");
                    noOfPax = scanner.nextInt();
                    scanner.nextLine();
                    error = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. (Valid values: Enter a number from 1 to " + MAX_TABLE_CAPACITY + ")");
                    scanner.nextLine();
                    error = true;
                }
            } while (error);
            if (noOfPax < 1 || noOfPax > MAX_TABLE_CAPACITY) {
                System.out.println("Invalid value. (Valid values: 1 - " + MAX_TABLE_CAPACITY + ")");
                cont = true;
            } else {
                cont = false;
            }
        }
        return noOfPax;
    }

    /**
     * Scanner to ask for user input (Customer Details) with error checking
     *
     * @return  the customer object
     */
    private Customer askUserForCustomerDetails() {
        boolean cont = true;
        boolean error = true;
        System.out.print("Name: ");
        String name = scanner.nextLine();
        char entry;
        int contactNumber_int = 0;
        Sex gender = Sex.MALE;
        boolean isMember = false;

        while (cont) {
            System.out.print("Gender (M/F): ");
            entry = scanner.nextLine().charAt(0);
            if (entry == 'M' || entry == 'm' || entry == 'F' || entry == 'f') {
                boolean isMale = entry == 'M' || entry == 'm';
                gender = isMale ? Sex.MALE : Sex.FEMALE;
                cont = false;
            } else {
                cont = true;
            }
        }
        do {
            try {
                System.out.print("Contact number: ");
                contactNumber_int = scanner.nextInt();
                scanner.nextLine();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. (Valid values: Enter a number from 1 to 9)");
                scanner.nextLine();
                error = true;
            }
        } while (error);
        String contactNumber = Integer.toString(contactNumber_int);
        cont = true;
        while (cont) {
            System.out.print("Member (Y/N): ");
            entry = scanner.nextLine().charAt(0);
            if (entry == 'Y' || entry == 'y' || entry == 'N' || entry == 'n') {
                isMember = entry == 'Y' || entry == 'y';
                cont = false;
            } else {
                cont = true;
            }
        }

        return new Customer(name, gender, contactNumber, isMember);
    }

    /**
     * Return the reservationMgr
     *
     * @return reservationMgr the object manager of Reservation
     */
    public ReservationMgr getReservationMgr() {
        return reservationMgr;
    }
}
