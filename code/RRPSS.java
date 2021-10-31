import java.io.Serializable;
import java.util.Scanner;

public class RRPSS implements Serializable {

	private OrderApp orderApp;
	private ReservationApp reservationApp;
	private Menu menuApp;
	private transient Scanner sc = new Scanner(System.in);

	public RRPSS() {
		orderApp = new OrderApp();
		reservationApp = new ReservationApp();
		menuApp = new Menu();
	}

	public void rrpsOptions() {
		System.out.println("What do you want?");
		sc = new Scanner(System.in);
		int choice = sc.nextInt();

		switch (choice) {
			case 1:
				startReservationApp();
				break;
			case 2:
				startOrderApp();
				break;
			case 3:
				menuApp.menuOptions();
				break;
			case 4:
				startSalesReport();
				break;
			case 5:
				System.out.println("Exited");
				return;
			default:
				System.out.println("Invalid option. Try again!");
		}

	}

	public void startReservationApp() {
		reservationApp.startReservationApp();
	}

	public void startOrderApp() {
		orderApp.orderAppOptions();
	}

	public void startSalesReport() {
		orderApp.salesReportOptions();
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		RRPSS main = null;
		Database database = new Database();

		while (choice != 3) {
			System.out.println("What do you want?");
			System.out.println("1.) Create New RRPSS System");
			System.out.println("2.) Load existing RRPSS System");
			System.out.println("3.) Exit");
			choice = sc.nextInt();

			switch (choice) {
				case 1:
					main = new RRPSS();
					main.rrpsOptions();//
					System.out.println("Saving system state...");
					database.save(main,"file1.ser");
					break;
				case 2:
					main = ( (RRPSS) database.load("file1.ser") );
					main.rrpsOptions();
					System.out.println("Saving system state...");
					database.save(main,"file1.ser");
					break;
				case 3:
					System.out.println("Exited");
					return;
				default:
					System.out.println("Wrong input. Try again!");
			}

		}

	}
}