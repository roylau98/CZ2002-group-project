import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Manages all the {@link Order} objects of the whole restaurants, 
 * basically the "manager" of {@link RRPSS} to {@link Order} objects
 * <p> 
 * This class provides various methods to create,update(add/remove MenuItem) of the order,
 * and view individual order details and bills.
 * <p>
 * @author
 */

public class OrderApp implements Serializable {
	private transient Scanner sc = new Scanner(System.in);
	private OrderMgr orderMgr;
	private StaffApp staffApp;
	private SalesReport salesReportApp;
	private Menu menuApp;

	/**
         * Constructs an {@code OrderApp} object and
         * initialize the attributes {@code Order}/{@code }/{@code } .
         */
	public OrderApp() {
		orderMgr = new OrderMgr();
		staffApp = new StaffApp();
		salesReportApp = new SalesReport();
		menuApp = new Menu();
	}

	public void openMenuApp() {
		menuApp.menuOptions();
	}

	public void salesReportOptions() {
		salesReportApp.options();
	}

	public void orderAppOptions(ReservationMgr reservationMgr) {
		sc = new Scanner(System.in);
		int choice = 0;
		int input = 0;
		do {
			try {
				System.out.println("========Order Option========");
				System.out.println("(1)View Order");
				System.out.println("(2)Create new Order");
				System.out.println("(3)Remove Order");
				System.out.println("(4)Update Item In Order");
				System.out.println("(5)Charge Bill");
				System.out.println("(6)Exit");
				System.out.println("----------------------------");
				System.out.print("Enter Your Option: ");
				choice = sc.nextInt();
				System.out.println("----------------------------");
				System.out.println();
			} catch(InputMismatchException e) {
				sc.nextLine();
			}
			switch (choice) {
				case 1:
					showOrder();
					break;
				case 2:
					newOrder(reservationMgr);
					break;
				case 3:
					deleteOrder();
					break;
				case 4:
					updateOrder();
					break;
				case 5:
					billOrder(reservationMgr);
					break;
				default:
					System.out.println("Invalid Option. Try again!");
					break;
			}
		} while (choice != 6);
	}

	/**
	 * A Do-While loop to create an order and add items of AlaCarteItem and add PromotionalSet to it
	 */
	private void newOrder(ReservationMgr reservationMgr) {
		System.out.println("Creating Order......");
		System.out.println("Which table is this new order for?");
		// update error checking e.g throwable in viewTablesWithReservationsNow()....
		reservationMgr.viewTablesWithReservationsNow();
		int tableNo;
		try {
			tableNo = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input received.");
			return;
		}
		Customer c = reservationMgr.getCustomerAt(tableNo);
		if (c == null) {
			System.out.println("Invalid input received.");
			return;
		}
		int newOrderID = orderMgr.createOrder(c, tableNo, staffApp.selectStaff());
		System.out.println("Order created, orderID: " + newOrderID);
		updateOrder(newOrderID);
	}

	private void updateOrder() {
		System.out.println("Updating Order......");
		System.out.print("What is the orderID of the order you would like to update? ");
		int orderID;
		try {
			orderID = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input received.");
			return;
		}
		updateOrder(orderID);
	}

	/**
	 * A Do-While loop to update existing order (adding more items of AlaCarteItem and add PromotionalSet to it) by orderID
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object
	 */
	private void updateOrder(int orderID) {
		if (orderMgr.getOrder(orderID) == null) {
			System.out.println("No such order");
			return;
		}
		if (orderMgr.getOrder(orderID).isCompleted()) {
			System.out.println("Order is already completed and paid");
			return;
		}

		System.out.println("Update Order Option");
		System.out.println("===================");
		System.out.println("1) Add menuItem");
		System.out.println("2) Remove menuItem");
		System.out.println("3) Quit");
		System.out.println("===================");
		System.out.print("Enter Your Choice: ");
		int choice = 0;
		try {
			choice = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input received.");
			return;
		}
		System.out.println("===================");

		switch (choice) {
			case 1:
				menuApp.printListOfMenuItems();
				while (choice != -1) {
					System.out.println("Enter menuItem choice. Or -1 to Quit");
					System.out.print("Enter Your Choice: ");
					try {
						choice = sc.nextInt();
						sc.nextLine();
						orderMgr.addItemsInOrder(orderID, menuApp.getMenuItem(choice));
					} catch (InputMismatchException | IndexOutOfBoundsException e) {
						System.out.println("Wrong Option!!!!!");
						return;
					}
				}
				orderMgr.viewOrder(orderID);
				break;
			case 2:
				orderMgr.printItemsInOrder(orderID);
				while (choice != -1) {
					System.out.println("Enter choice. Or -1 to Quit");
					System.out.print("Enter Your Choice: ");
					try {
						choice = sc.nextInt();
						sc.nextLine();
						orderMgr.removeItemsInOrder(orderID, choice);
					} catch (InputMismatchException | IndexOutOfBoundsException e) {
						System.out.println("Wrong Option!!!!!");
						return;
					}
				}
				break;
			case 3:
				break;
			default:
				System.out.println("Invalid input received.");
				break;
		}
	}

	private void deleteOrder() {
		System.out.println("Removing Order......");
		System.out.print("Enter orderID or -1 to exit: ");
		int orderID = 0;
		try {
			orderID = sc.nextInt();
			sc.nextLine();
			orderMgr.removeOrder(orderID);
		} catch (InputMismatchException | IndexOutOfBoundsException e) {
			System.out.println("Invalid input received.");
		}
	}

	private void showOrder() {
		System.out.println("View Order......");
		System.out.print("Enter orderID or -1 to exit: ");
		int orderID = 0;
		try {
			orderID = sc.nextInt();
			sc.nextLine();
			orderMgr.viewOrder(orderID);
		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid Option.");
			return;
		}
	}

	private void billOrder(ReservationMgr reservationMgr) {

		sc = new Scanner(System.in);
		int input;

		while(true)
		{
			try
			{
				System.out.println("Charge Bill......");
				System.out.print("Enter orderID or -1 to exit: ");
				input = sc.nextInt();
				break;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Invalid Option.");
				sc.nextLine();
			}
		}
		if(input==-1) {
			System.out.println("Exited!");
		}
		else {
			salesReportApp.addInvoice(orderMgr.chargeBill(reservationMgr, input));
		}


	}
}