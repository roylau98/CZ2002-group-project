import javax.xml.crypto.Data;
import java.util.*;
import java.io.*;

public class RRPSS implements Serializable {

	private OrderApp orderApp;
	private ReservationApp reservationApp;
	private transient Scanner sc = new Scanner(System.in);

	public RRPSS() {
		orderApp = new OrderApp();
		reservationApp = new ReservationApp();
	}

	public void rrpsOptions() {
		int choice=0;
		
		
		while(choice!=4)
		{
			System.out.println("~~~~~Welcome to Krusty Krab~~~~~");
			System.out.println();
			System.out.println("What do you want?");
			System.out.println("(1) Reservation");
			System.out.println("(2) Order");
			System.out.println("(3) Edit Menu   (Admin)");
			System.out.println("(4) Sales Report(Admin)");
			System.out.println("(5) Exit");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			System.out.print("Enter Your Choice: ");
			sc = new Scanner(System.in);
			choice = sc.nextInt();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			switch (choice) {
				case 1:
					startReservationApp();
					break;
				case 2:
					startOrderApp();
					break;
				case 3:
					startMenuApp();
					
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
		

	}

	public void startReservationApp() {
		System.out.println("======Reservation Option======");
		//reservationApp.
		System.out.println("==End of Reservation Option===");
	}

	public void startOrderApp() {
		
		orderApp.orderAppOptions();
		
	}
	
	public void startMenuApp() {
		
		orderApp.openMenuApp();
		
	}
	
	public void startSalesReport() {
		System.out.println("=====Sales Report Option====");
		orderApp.salesReportOptions();
		System.out.println("=End of SalesReport Option==");
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		RRPSS main = null;
		Database database = new Database();
		
		while (choice != 3) {
			System.out.println("***Krusty Krab Restaurant Reservation and Point of Sale System Manager***");
			System.out.println();
			System.out.println("What do you want?");
			System.out.println("(1) Create New RRPSS System");
			System.out.println("(2) Load existing RRPSS System");
			System.out.println("(3) Exit");
			
			System.out.println("*************************************************************************");
			
			System.out.print("Enter Your Choice: ");
			choice = sc.nextInt();
			System.out.println("*************************************************************************");
			System.out.println();	
			switch (choice) {
				case 1:
					main = new RRPSS();
					System.out.println("New RRPSS created !");
					System.out.println();
					
					main.rrpsOptions();
					System.out.println("Saving system state...");
					database.save(main,"file1.txt");
					break;
				case 2:
					main = ( (RRPSS) database.load("file1.txt") );
					System.out.println("System state loaded !");
					System.out.println();
					main.rrpsOptions();
					System.out.println("Saving system state...");
					database.save(main,"file1.txt");
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
