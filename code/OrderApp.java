import java.util.*;
import java.io.*;
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

	//Pls add these func to order class(or i can add after u finish the order class)
	//void addMenuItems(MenuItems items)
	//void addPromoItems(Promotion promo)
	//void removeItems(int index)
	//void printAllItemsInOrder()
	//void printPrice()


	private transient Scanner sc = new Scanner(System.in);
	/**
         * List of Order implemented in {@link ArrayList} data structure.
         * Each entry consists of a reference to existing {@link Order}object.
         */
	private ArrayList<Order> listOfOrders;

	/**
         * 
         */
	private Menu menuApp;
	/**
	 *
	 */
	private SalesReport salesReport;

	private int orderIDtracker;

	
	/**
         * Constructs an {@code OrderApp} object and
         * initialize the attributes {@code Order}/{@code }/{@code } .
         */
	public OrderApp() {
		listOfOrders = new ArrayList<>();
		menuApp = new Menu();
		salesReport = new SalesReport();
		orderIDtracker = 0;
	}

	public void orderAppOptions() {
		sc = new Scanner(System.in);
		int choice;
		int input;

		do {
			System.out.println("Enter Option");
			choice = sc.nextInt();

			switch (choice) {
				case 1:
					System.out.println("Enter orderID");
					input = sc.nextInt();
					viewOrder(input);
					break;
				case 2:
					createOrder();
					break;
				case 3:
					System.out.println("Enter orderID");
					input = sc.nextInt();
					removeOrder(input);
					break;
				case 4:
					System.out.println("Enter orderID");
					input = sc.nextInt();
					updateOrder(input);
					break;
				case 5:
					System.out.println("Enter orderID");
					input = sc.nextInt();
					chargeBill(input);
					break;
				default:
					System.out.println("Invalid Option. Try again!");
			}

		} while(choice != -1);

	}

	//-----------------------------------------------------------------------------------------------------------
	/**
	 * A Do-While loop to create an order and add items of AlaCarteItem and add PromotionalSet to it 
	 * 
	 */

	public void createOrder() {
		sc = new Scanner(System.in);
		int choice=999;

		Order customerOrder = new Order();
		//TODO
		menuApp.printListOfMenuItems();
		while (true) {
			System.out.println("Enter menu item choice. Or -1 to Quit");
			choice = sc.nextInt(); // have not accounted for arrayOutOfBounds Error
			try 
			{
				MenuItem selectedMenuItem = menuApp.getMenuItem(choice-1);
				customerOrder.addItemToOrder(selectedMenuItem);
				break;
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Invalid choice!");
			}
		}
		customerOrder.setOrderID(orderIDtracker);
		orderIDtracker++;
		listOfOrders.add(customerOrder);
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
		Order selectedOrder = null;
		for (int i=0; i<listOfOrders.size(); i++) {
			if (listOfOrders.get(i).getOrderID() == orderID) {
				selectedOrder = listOfOrders.get(i);
				break;
			}
		}

		if (selectedOrder == null) {
			System.out.println("No such order");
		}
		else if (selectedOrder.isCompleted() == true) {
			System.out.println("Order is already completed and paid");
		}
		else {
			int choice;

			System.out.println("1) Add menuItem");
			System.out.println("2) Remove menuItem");
			System.out.println("-1) Quit");
			choice = sc.nextInt();

			while (choice != -1) {

				if (choice ==1) {
					menuApp.printListOfMenuItems();
					while (choice != -1) {
						System.out.println("Enter menuItem choice. Or -1 to Quit");
						choice = sc.nextInt(); // have not accounted for arrayOutOfBounds Error
						try 
						{
							MenuItem selectedMenuItem = menuApp.getMenuItem(choice-1);
							selectedOrder.addItemToOrder(selectedMenuItem);
						}
						catch(IndexOutOfBoundsException e)
						{
							System.out.println("Invalid choice!");
						}
					}
				}
				else if (choice == 2) {
					ArrayList<MenuItem> listOfItemsInCurrOrder = selectedOrder.getListOfItemsOrdered();
					for(int i=0; i<listOfItemsInCurrOrder.size(); i++) {
						System.out.println(i+") "+listOfItemsInCurrOrder.get(i).getName());
					}

					while (choice != -1) {
						System.out.println("Enter choice. Or -1 to Quit");
						choice = sc.nextInt(); // have not accounted for arrayOutOfBounds Error
						try 
						{
							selectedOrder.removeItemFromOrder(choice);
						}
						catch(IndexOutOfBoundsException e)
						{
							System.out.println("Invalid choice!");
						}
					}

				}
				else if (choice == -1) {
					break;
				}
				else {
					System.out.println("Invalid input. Try again!");
				}
			}

		}

	}
	//--------------------------------------------------------------------------------------------------------------------
	/**
	 * A Do-While loop to update existing order (removing more items of AlaCarteItem and add PromotionalSet to it) by orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public void removeOrder(int orderID) {
		sc = new Scanner(System.in);
		int index;
		Order currOrder;

		for(int i = 0; i< listOfOrders.size(); i++)
		{
			if(listOfOrders.get(i).getOrderID()==orderID) {
				currOrder = listOfOrders.get(i);
				ArrayList<MenuItem> currOrderItemList = currOrder.getListOfItemsOrdered();
				do {
					System.out.println("==============Your Current Ordered Items=============");
					for (int j=0; j < currOrderItemList.size(); j++) {
						System.out.println(j+") "+currOrderItemList.get(j).getName() + currOrderItemList.get(j).getPrice());
					}
					currOrder.getTotalPriceOfOrder();
					System.out.println("=============================================");
					System.out.println("Please select the item to remove (Enter -1 to exit)");
					index = sc.nextInt();
					currOrder.removeItemFromOrder(index);

				}while(index!=-1);


				System.out.println("==============Your Updated Order=============");
				for (int j=0; i < currOrderItemList.size(); j++) {
					System.out.println(j+") "+currOrderItemList.get(j).getName() + currOrderItemList.get(j).getPrice());
				}
				currOrder.getTotalPriceOfOrder();
				System.out.println("=============================================");
				break;
			}
		}
	}
	//-----------------------------------------------------------------------------------------------------------------------
	/**
	 * A function that printout the existing items in order by using orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public void viewOrder(int orderID) {

		Order selectedOrder;

		for(int i = 0; i< listOfOrders.size(); i++)
		{
			if(listOfOrders.get(i).getOrderID()==orderID)
			{

				selectedOrder = listOfOrders.get(i);
				System.out.println("==============Your Current Order=============");
				for(int j = 0; j< selectedOrder.getListOfItemsOrdered().size(); j++) {
					MenuItem currItem = selectedOrder.getListOfItemsOrdered().get(i);
					System.out.println(currItem.getName() + " " + currItem.getPrice());
				}
				selectedOrder.getTotalPriceOfOrder();
				System.out.println("=============================================");
				break;
			}
		}
	}
	//-----------------------------------------------------------------------------------------------------------------------
	/**
	 * A function that printout the bills in order by using orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public void chargeBill(int orderID) {
		Order selectedOrder;
		Invoice bill;

		for(int i = 0; i< listOfOrders.size(); i++) {
			if(listOfOrders.get(i).getOrderID()==orderID) {
				selectedOrder = listOfOrders.get(i);
				selectedOrder.createInvoice(selectedOrder);
				bill = selectedOrder.getInvoice();
				bill.printInvoice();
				salesReport.addInvoice(bill);
				return;
			}
		}

		System.out.println("No such order");
	}

	public ArrayList<Order> getListOfOrders() {
		return listOfOrders;
	}

	public void salesReportOptions() {
		salesReport.options();
	}

	public SalesReport getSalesReport() {
		return salesReport;
	}

}
