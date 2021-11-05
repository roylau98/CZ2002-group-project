import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The main application where the RRPSS works. This class provides the interface for the user.
 * 
 * @author 
 * @since 2021-11-5
 */
public class RRPSS implements Serializable {
     private transient Scanner sc = new Scanner(System.in);
    /**
     * Order App wtih options
     */
    private OrderApp orderApp;
    /**
     * Reservation App wtih options
     */
    private ReservationApp reservationApp;
    /**
     * Menu App wtih options
     */
    private MenuApp menuApp;
    
   
    /**
     * Menu Manager shared by OrderApp and MenuApp
     */
    private MenuMgr menuMgr;
    
     /**
     * Class Constructor with default settings
     */
    public RRPSS() {
    	menuMgr = new MenuMgr();
        orderApp = new OrderApp(menuMgr);
        reservationApp = new ReservationApp();
        menuApp = new MenuApp(menuMgr);
    }
    /**
     * Interface of the application
     */
    public void rrpsOptions() {
        int choice = 0;

        while (choice != 5) {
            System.out.println("~~~~~Welcome to Krusty Krab~~~~~");
            System.out.println();
            System.out.println("What do you want?");
            System.out.println("(1) Reservation");
            System.out.println("(2) Order");
            System.out.println("(3) Edit Menu   (Admin)");
            System.out.println("(4) Sales Report(Admin)");
            System.out.println("(5) Exit");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            try {
                System.out.print("Enter Your Choice: ");
                sc = new Scanner(System.in);
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }

            switch (choice) {
                case 1:
                    System.out.println("======Reservation Option======");
                    reservationApp.startReservationApp();
                    System.out.println("==End of Reservation Option===");
                    break;
                case 2:
                    orderApp.orderAppOptions(reservationApp.getReservationMgr());
                    break;
                case 3:
                    menuApp.menuOptions();
                    break;
                case 4:
                    orderApp.salesReportOptions();
                    break;
                case 5:
                    System.out.println("Exited");
                    return;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }
    /**
     * Main function that runs the RRPSS app
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RRPSS rrpssApp;
        Database database = new Database();
        rrpssApp = (RRPSS) database.load("file.txt");
        if (rrpssApp == null) {
            System.out.println("Creating New RRPSS......");
            rrpssApp = new RRPSS();
        }
        rrpssApp.rrpsOptions();
        database.save(rrpssApp,"file.txt");
    }
}
