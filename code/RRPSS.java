import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RRPSS implements Serializable {
    private OrderApp orderApp;
    private ReservationApp reservationApp;
    private MenuApp menuApp;
    private transient Scanner sc = new Scanner(System.in);
    private MenuMgr menuMgr;

    public RRPSS() {
    	menuMgr = new MenuMgr();
        orderApp = new OrderApp(menuMgr);
        reservationApp = new ReservationApp();
        menuApp = new MenuApp(menuMgr);
    }

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
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
            } catch (InputMismatchException e) {
                sc.nextLine();
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

    public static void main(String args[]) {
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
