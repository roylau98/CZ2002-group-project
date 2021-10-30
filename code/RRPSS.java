import javax.xml.crypto.Data;
import java.util.*;
import java.io.*;

public class RRPSS implements Serializable {

	private OrderApp orderApp;
	private ReservationApp reservationApp;

	public RRPSS() {
		orderApp = new OrderApp();
		reservationApp = new ReservationApp();
	}

	public void rrpsDataBaseOption() {


	}

	public void rrpsOptions() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What do you want?");
		int choice = sc.nextInt();

		switch (choice) {
			case 1:
				startReservationApp();
				break;
			case 2:
				startOrderApp();
				break;
			case 3:
				startSalesReport();
				break;
			case 4:
				System.out.println("Exited");
				return;
			default:
				System.out.println("Invalid option. Try again!");
		}

	}

	public void startReservationApp() {

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
		RRPSS main;
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
					break;
				case 2:
					main = ( (RRPSS) database.load("file1.txt") );
					break;
				case 3:
					System.out.println("Exited");
					return;
				default:
					System.out.println("Wrong input. Try again!");
			}

		}

		main.rrpsOptions();
		System.out.println("Saving system state...");
		database.save(main,"file1.txt");

	}
}