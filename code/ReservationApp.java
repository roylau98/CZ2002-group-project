import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ReservationApp {

    public static void main(String[] args) {
        // TODO - look for a better place to add tables to the restaurant
        ReservationMgr.addTable(2);
        ReservationMgr.addTable(2);
        ReservationMgr.addTable(4);
        ReservationMgr.addTable(4);

        System.out.println("Welcome to the ReservationApp.");
        Scanner scanner = new Scanner(System.in);
        boolean cont = true;
        while (true) {
            System.out.println("Please select one of the options below:\n" +
                    "1. Make a new reservation\n" +
                    "2. Cancel an existing reservation\n" +
                    "3. Amend an existing reservation\n" +
                    "4. View the list of reservations\n" +
                    "5. View the list of tables\n" +
                    "6. Exit this application and return to the main menu");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    ReservationMgr.viewAllReservations();
                    System.out.println("Which reservation would you like to cancel?");
                    int temp = scanner.nextInt();
                    scanner.nextLine();
                    ReservationMgr.cancelReservation(temp);
                    break;
                case 3:
                    updateReservation();
                    break;
                case 4:
                    ReservationMgr.viewAllReservations();
                    break;
                case 5:
                    ReservationMgr.viewAllTables();
                    break;
                case 6:
                    cont = false;
                    break;
            }
        }
    }

    private static void makeReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the following details below:");

        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Month: ");
        int month = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Date: ");
        int date = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Hour: ");
        int hour = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Number of persons: ");
        int noOfPax = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Gender: ");
        System.out.println();
        // TODO - implement later
        Sex gender = Sex.FEMALE;

        System.out.print("Contact number: ");
        String contactNumber = scanner.nextLine();

        System.out.print("Member? ");
        char entry = scanner.next().charAt(0);
        boolean isMember = entry == 'Y' || entry == 'y';

        ReservationMgr.makeReservation(
                new Reservation(LocalDate.of(year, month, date),
                        LocalTime.of(hour, 0),
                        noOfPax,
                        new Customer(name, gender, contactNumber, isMember)));
    }

    private static void updateReservation() {
        Scanner scanner = new Scanner(System.in);
        ReservationMgr.viewAllReservations();
        System.out.println("Which reservation would you like to amend?");
        int temp = scanner.nextInt();
        scanner.nextLine();


    }
}
