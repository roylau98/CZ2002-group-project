import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReservationApp implements Serializable {
	private transient Scanner scanner;
	private final ReservationMgr reservationMgr = new ReservationMgr();
	private static final int MAX_TABLE_CAPACITY = 25;
	private static LocalDate localDate = LocalDate.now();
	private static LocalTime localTime = LocalTime.now();
	private static int currentYear = localDate.getYear();
	private static int currentMonth = localDate.getMonthValue();
	private static Month currentMonthEnum = localDate.getMonth();
	private static int currentDate = localDate.getDayOfMonth();
	private static int currentHour = localTime.getHour();
	private static int year = 0;
	private static int month = 0;
	private static int date = 0;
	private static int hour = 0;
	
    public void startReservationApp() {
        scanner = new Scanner(System.in);
        reservationMgr.removeNoShowReservations();
		reservationMgr.createScheduler();
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
                                "7. Quit this application and return to the previous page");
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
    	if (reservationMgr.getTotalNoOfReservations() == 0) {
    		System.out.println("No Reservations in system.");
    		return;
    	}
		
        try {
            reservationMgr.viewAllReservations();
            System.out.println("Which reservation would you like to cancel?");
            int reservationNumber = scanner.nextInt();
            scanner.nextLine();
            reservationMgr.cancelReservation(reservationNumber);
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println("Invalid input entered.");
            cancelReservation();
        }
    }

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
    			}
    			catch (InputMismatchException e) {
    				System.out.println("Invalid input for options. Enter integers.");
                    error = true;
                    scanner.nextLine();
    			}
    		} while (error);
	        if (((reservationMgr.getTotalNoOfReservations()-1) < reservationNoToUpdate) || reservationNoToUpdate < 0) {
	        	System.out.println("Invalid input. Try again.");
	        	cont = true;
	        }
	        else {
	        	cont = false;
	        }
    	}
    	cont = true;
    	error = true;
    	while (cont) {
    		do {
    			try {
    				System.out.println("What would you like to amend?\n" +
    		                "1. Date of reservation\n" +
    		                "2. Time of reservation\n" +
    		                "3. Number of persons\n" +
    		                "4. Customer details");
	    		    choice = scanner.nextInt();
	    		    scanner.nextLine();
	    		    error = false;
    			}
    			catch (InputMismatchException e) {
    				System.out.println("Invalid input for options. Enter integers.");
                    error = true;
                    scanner.nextLine();
    			}
    		} while (error);
		    
		    if (choice < 1 || choice > 4) {
		    	System.out.println("Invalid value. (Valid value: 1 - 4)\n");
		    }
		    else {
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

    private LocalDate askUserForDate() {
		System.out.print("Year: ");
		year = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Month (Jan:1, Feb:2, Mar:3, Apr:4, May:5, Jun:6, Jul:7, Aug:8, Sep:9, Oct:10, Nov:11, Dec:12): " );
		month = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Date: ");
		date = scanner.nextInt();
		scanner.nextLine();

		/*
		boolean cont = true;
        boolean error = true;
    	while (cont) {
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
	    	if (month < currentMonth && year == currentYear) {
	        	System.out.println("Invalid value. (Valid values: " + currentMonthEnum + " onwards)");
	        }
	    	else if (month < 0 || month > 12) {
	    		System.out.println("Invalid Value. (Valud values: 1 - 12");
	    	}
	    	else {
	    		cont = false;
	    	}
    	}
    	cont = true;
    	LocalDate lastDayOfMonth = LocalDate.of(year, month, 1);
    	int maxDay = lastDayOfMonth.getMonth().length(lastDayOfMonth.isLeapYear());
    	while (cont) {
	    	do {
	    		
		    	try {
			        System.out.print("Date: ");
			        date = scanner.nextInt();
			        scanner.nextLine();
			        error = false;
		    	}
		    	catch (InputMismatchException e){
		    		System.out.println("Invalid input. (Valid values: From 1 onwards to " + maxDay + ")");
		    		scanner.nextLine();
		    		error = true;
		    	}
	    	} while (error);
	    	
			if (year == currentYear && month == currentMonth){
				if (date < currentDate || date > maxDay) {
					System.out.println("Invalid value. (Valid values: From " + currentDate + " onwards to " + maxDay + ")");
					cont = true;
				}
				else {
					if (currentHour == 23) {
						System.out.println("Invalid value. (Valid values: From " + (currentDate+1) + " onwards to " + maxDay + ")");
					}
					else {
						cont = false;
					}
				}
			}
			else {
				if (date < 1 || date > maxDay) {
					System.out.println("Invalid value. (Valid values: From 1 - " + maxDay + ")");
					cont = true;
				}
				else {
					cont = false;
				}
			}
    	}
*/
        return LocalDate.of(year, month, date);
    }

    private LocalTime askUserForTime() {
		System.out.print("Hour: ");
		hour = scanner.nextInt();
		scanner.nextLine();

//        boolean cont = true;
//        boolean error = true;
//    	cont = true;
//    	while (cont) {
//	    	do {
//		    	try {
//			        System.out.print("Hour: ");
//			        hour = scanner.nextInt();
//			        scanner.nextLine();
//			        error = false;
//		    	}
//		    	catch (InputMismatchException e){
//		    		System.out.println("Invalid input. (Enter an integer)");
//		    		scanner.nextLine();
//		    		error = true;
//		    	}
//	    	} while (error);
//	    	if (hour < 0 || hour > 23) {
//	    		System.out.println("Invalid value. (Valid values: 0 - 23)");
//	    	}
//	    	else if (year == currentYear && month == currentMonth && date == currentDate && hour <= currentHour){
//		    	System.out.println("Invalid value. (Valid values: " + (currentHour + 1) + " - 23)");
//		    }
//	    	else {
//			   	cont = false;
//			}
//    	}
		return LocalTime.of(hour, 0);
    }

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
		    	}
		    	catch (InputMismatchException e){
		    		System.out.println("Invalid input. (Valid values: Enter a number from 1 to " + MAX_TABLE_CAPACITY +")");
		    		scanner.nextLine();
		    		error = true;
		    	}
	    	} while (error);
	    	if (noOfPax < 1 || noOfPax > MAX_TABLE_CAPACITY) {
	        	System.out.println("Invalid value. (Valid values: 1 - " + MAX_TABLE_CAPACITY + ")");
	        	cont = true;
	        }
	    	else {
	    		cont = false;
	    	}
    	}
        return noOfPax;
    }

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
	    	System.out.print("Member (Y/N): ");
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

	public ReservationMgr getReservationMgr() {
		return reservationMgr;
	}
}
