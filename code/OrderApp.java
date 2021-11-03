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
 * 
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
					while(true)
					{
						try 
						{
							System.out.println("Updating Order......");
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
					if(input==-1)
						System.out.println("Exited!");
					else
						updateOrder(input);
					break;
						
				case 5:
					billOrder(reservationMgr);
					break;
					
						
				default:
					System.out.println("Invalid Option. Try again!");
			}
			

		} while(choice != 6);

	}
	
	public void openMenuApp() {
		orderMgr.openMenuApp();
	}
	//-----------------------------------------------------------------------------------------------------------
	/**
	 * A Do-While loop to create an order and add items of AlaCarteItem and add PromotionalSet to it 
	 * 
	 */

	public void newOrder(ReservationMgr reservationMgr) {
		sc = new Scanner(System.in);
		int choice=999;

		System.out.println("Creating Order......");
		int tableNo = -1;

		try {
			System.out.println("Which table is ordering now?");
			reservationMgr.viewTablesWithReservationsNow();
			tableNo = sc.nextInt();
			sc.nextLine();
		}
		// update error checking e.g throwable in viewTablesWithReservationsNow()....
		catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid input");
		}

		int newOrderID = orderMgr.createOrder(reservationMgr.getCustomerAt(tableNo), tableNo, staffApp.selectStaff());
		if (newOrderID == -1) {
			System.out.println("Order Creation Failed!");
			return;
		}
		System.out.println("Order created, orderID: "+newOrderID);

		choice = 999;
		while (choice !=1 || choice != -1) {
			System.out.println("Add items? 1-Yes, -1-No");
			choice = sc.nextInt();
			if (choice == 1) {
				updateOrder(newOrderID);
			}
			else if (choice==-1) {
				return;
			}
			else {
				System.out.println("Wrong choice. Try again!");
			}
		}


	}
	//------------------------------------------------------------------------------------------------------------
	/**
	 * A Do-While loop to update existing order (adding more items of AlaCarteItem and add PromotionalSet to it) by orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public void updateOrder(int orderID) {
		sc = new Scanner(System.in);

		if (orderMgr.getOrder(orderID) == null)
		{
			System.out.println("No such order");
		}
		else if (orderMgr.getOrder(orderID).isCompleted() == true)
		{
			System.out.println("Order is already completed and paid");
		}
		else
		{
			int choice;
			while(true)
			{
				try
				{
					System.out.println("Update Order Option");
					System.out.println("===================");
					System.out.println("1) Add menuItem");
					System.out.println("2) Remove menuItem");
					System.out.println("-1) Quit");
					System.out.println("===================");
					System.out.print("Enter Your Choice: ");
					choice = sc.nextInt();
					System.out.println("===================");
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("Wrong Option!!!!!");
					sc.nextLine();
				}
			}

			while (choice != -1)
			{

				if (choice ==1)
				{
					orderMgr.getMenuApp().printListOfMenuItems();
					while (choice != -1)
					{
						try
						{
							System.out.println("Enter menuItem choice. Or -1 to Quit");
							System.out.print("Enter Your Choice: ");
							choice = sc.nextInt(); // have not accounted for arrayOutOfBounds Error
						}
						catch(InputMismatchException e)
						{
							System.out.println("Wrong Option!!!!!");
							sc.nextLine();
						}
						if(choice==-1) {
							return;
						}
						try
						{
							orderMgr.addItemsInOrder(orderID,orderMgr.getMenuApp().getMenuItem(choice));
						}
						// need rework on catch
						catch(IndexOutOfBoundsException e)
						{
							System.out.println("Invalid choice!");
						}
					}
					orderMgr.viewOrder(orderID);
				}
				else if (choice == 2)
				{
					orderMgr.printItemsInOrder(orderID);
					while (choice != -1) {
						try
						{
							System.out.println("Enter choice. Or -1 to Quit");
							System.out.print("Enter Your Choice: ");
							choice = sc.nextInt(); // have not accounted for arrayOutOfBounds Error
						}
						catch(InputMismatchException e)
						{
							System.out.println("Wrong Option!!!!!");
							sc.nextLine();
						}
						if (choice==-1) {
							return;
						}

						try
						{
							orderMgr.removeItemsInOrder(orderID,choice);
						}
						// need rework on catch
						catch(IndexOutOfBoundsException e)
						{
							System.out.println("Invalid choice!");
						}
					}

				}
				else if (choice == -1)
				{
					break;
				}
				else
				{
					System.out.println("Invalid input. Try again!");
				}
			}

		}

	}
	//--------------------------------------------------------------------------------------------------------------------

	public void deleteOrder() {
		sc = new Scanner(System.in);
		int input=999;
		while(true)
		{
			try
			{
				System.out.println("Removing Order......");
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
			orderMgr.removeOrder(input);
		}

	}
	//-----------------------------------------------------------------------------------------------------------------------
	/**
	 * A function that printout the existing items in order by using orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public void showOrder() {
		sc = new Scanner(System.in);
		int input = 999;
		while (true) {
			try {
				System.out.println("View Order......");
				System.out.print("Enter orderID or -1 to exit: ");
				input = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid Option.");
				sc.nextLine();
			}
		}

		if (input == -1) {
			System.out.println("Exited!");
		}
		else {
			orderMgr.viewOrder(input);
		}
	}
	//-----------------------------------------------------------------------------------------------------------------------
	/**
	 * A function that printout the bills in order by using orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public void billOrder(ReservationMgr reservationMgr) {

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
			orderMgr.chargeBill(reservationMgr, input);
		}


	}

	public void salesReportOptions() {
		salesReportApp.options();
	}


}
