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
			System.out.println("Please select one of the options below:\n" +
					"1. View an existing order\n" +
					"2. Make a new order\n" +
					"3. Cancel an existing order\n" +
					"4. Update an existing order\n" +
					"5. Make payment for an order\n" +
					"6. Quit this application and return to the previous page");
			try {
				choice = sc.nextInt();
				sc.nextLine();
				System.out.println();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input received.");
			}
			switch (choice) {
				case 1:
					viewOrder();
					break;
				case 2:
					newOrder(reservationMgr);
					break;
				case 3:
					removeOrder();
					break;
				case 4:
					updateOrder();
					break;
				case 5:
					billOrder(reservationMgr);
					break;
				default:
					System.out.println("Invalid input received.");
					break;
			}
		} while (choice != 6);
	}

	private void viewOrder() {
		if (orderMgr.getTotalNoOfOrders() == 0) {
			System.out.println("No orders have been made.");
			return;
		}
		orderMgr.viewOrder(askUserForOrderID());
	}

	/**
	 * A Do-While loop to create an order and add items of AlaCarteItem and add PromotionalSet to it
	 */
	private void newOrder(ReservationMgr reservationMgr) {
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
		System.out.println("Order " + newOrderID + " has been created.");
		updateOrder(newOrderID);
	}

	private void removeOrder() {
		if (orderMgr.getTotalNoOfOrders() == 0) {
			System.out.println("No orders have been made.");
			return;
		}
		orderMgr.removeOrder(askUserForOrderID());
	}

	private void updateOrder() {
		if (orderMgr.getTotalNoOfOrders() == 0) {
			System.out.println("No orders have been made.");
			return;
		}
		int orderID = askUserForOrderID();
		updateOrder(orderID);
	}

	private void updateOrder(int orderID) {
		if (orderMgr.getOrder(orderID).isCompleted()) {
			System.out.println("Order " + orderID + " is complete and payment has been made.");
			return;
		}
		System.out.println("How would you like to update your order?\n" +
				"1. Add a menu item to the order\n" +
				"2. Remove an item from the order\n" +
				"3. Exit and return to the previous page");
		int choice;
		try {
			choice = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input received.");
			return;
		}
		switch (choice) {
			case 1:
				addToOrder(orderID);
				break;
			case 2:
				removeFromOrder(orderID);
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid input received.");
				break;
		}
	}

	private void addToOrder(int orderID) {
		if (menuApp.getNumberOfMenuItems() == 0) {
			System.out.println("No items are on the menu.");
			return;
		}
		menuApp.printListOfMenuItems();
		System.out.println("Which item would you like to order?");
		orderMgr.addItemsInOrder(orderID, menuApp.getMenuItem(askUserForMenuItemNo()));
		orderMgr.viewOrder(orderID);
	}

	private void removeFromOrder(int orderID) {
		orderMgr.printItemsInOrder(orderID);
		if (orderMgr.getOrder(orderID).getNumberOfItemsOrdered() == 0) {
			System.out.println("No items ordered so far.");
			return;
		}
		System.out.println("Which item would you like to remove?");
		int itemNo;
		try {
			itemNo = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input received.");
			return;
		}
		if (itemNo < 0 || itemNo >= orderMgr.getOrder(orderID).getNumberOfItemsOrdered()) {
			System.out.println("Invalid input received.");
			return;
		}
		orderMgr.removeItemsInOrder(orderID, itemNo);
	}

	private void billOrder(ReservationMgr reservationMgr) {
		if (orderMgr.getTotalNoOfOrders() == 0) {
			System.out.println("No orders have been made.");
			return;
		}
		salesReportApp.addInvoice(orderMgr.chargeBill(reservationMgr, askUserForOrderID()));
	}

	private int askUserForOrderID() {
		System.out.print("Enter an OrderID: ");
		int orderID;
		try {
			orderID = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input received.");
			return askUserForOrderID();
		}
		if (!orderMgr.validateOrderID(orderID)) {
			System.out.println("Invalid input received.");
			return askUserForOrderID();
		}
		return orderID;
	}

	private int askUserForMenuItemNo() {
		System.out.print("Enter a menu item number: ");
		int menuItemNo;
		try {
			menuItemNo = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input received.");
			return askUserForMenuItemNo();
		}
		if (!menuApp.validateMenuItemNo(menuItemNo)) {
			System.out.println("Invalid input received.");
			return askUserForMenuItemNo();
		}
		return menuItemNo;
	}
}