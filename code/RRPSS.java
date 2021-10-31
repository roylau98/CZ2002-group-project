import java.util.*;

public class RRPSS {

	private OrderApp orderApp;
	private ReservationApp reservationApp;

	public RRPSS() {
		orderApp = new OrderApp();
		reservationApp = new ReservationApp();
	}

	public void rrpsOptions() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What do you want?");
		int choice = sc.nextInt();

		switch (choice) {
			case 1:

			case 2:

			case 3:

			case 4:
				break;
			case 5:
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
		RRPSS main = new RRPSS();
		main.rrpsOptions();
	}
}