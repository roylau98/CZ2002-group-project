import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The main application where the RRPSS works. This class provides the interface for the user.
 *
 * @since 2021-11-5
 */
public class RRPSS implements Serializable {
    /**
     * Order App with options
     */
    private final OrderApp orderApp;
    /**
     * Reservation App with options
     */
    private final ReservationApp reservationApp;

    /**
     * Class Constructor with default settings
     */
    public RRPSS() {
        orderApp = new OrderApp();
        reservationApp = new ReservationApp();
    }

    /**
     * Main function that runs the RRPSS app
     */
    public static void main(String[] args) {
        RRPSS rrpssApp;
        Database database = new Database();
        rrpssApp = (RRPSS) database.load("file.txt");
        if (rrpssApp == null) {
            System.out.println("New RRPSS system created.");
            rrpssApp = new RRPSS();
        }
        rrpssApp.rrpssOptions();
        database.save(rrpssApp, "file.txt");
        System.out.println("Saving....");
        System.out.println("Exited!");
    }

    /**
     * Interface of the application
     */
    public void rrpssOptions() {
        int choice = 0;

        while (true) {
            System.out.print("\nWelcome to Krusty Krab!\n" +
                    "Please select one of the options below:\n" +
                    "1. Manage reservations\n" +
                    "2. Manage orders\n" +
                    "3. Edit the menu\n" +
                    "4. View sales report\n" +
                    "5. Quit RRPSS and save all data\n" +
                    "Enter your choice: ");
            try {
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }

            switch (choice) {
                case 1:
                    reservationApp.startReservationApp();
                    break;
                case 2:
                    orderApp.orderAppOptions(reservationApp.getReservationMgr());
                    break;
                case 3:
                    orderApp.getMenuApp().menuOptions();
                    break;
                case 4:
                    orderApp.salesReportOptions();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }
}
