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

	private StaffApp staffApp;
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
		staffApp = new StaffApp();
	}

	public void orderAppOptions() {
		sc = new Scanner(System.in);
		int choice = 0;
		int input = 0;

		do {
			try 
			{
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
			}
			catch(InputMismatchException e)
		        {
		            	sc.nextLine();
		        }
			switch (choice) 
			{
				case 1:
					while(true)
					{
						try 
						{
							System.out.println("View Order......");
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
						viewOrder(input);
					break;
				case 2:
					System.out.println("Creating Order......");
					createOrder();
					break;
				case 3:
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
					if(input==-1)
						System.out.println("Exited!");
					else
						removeOrder(input);
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
					if(input==-1)
						System.out.println("Exited!");
					else
						chargeBill(input);
					break;
					
						
				default:
					System.out.println("Invalid Option. Try again!");
			}
			

		} while(choice != 6);

	}
	
	public void openMenuApp()
	{
		menuApp.menuOptions();
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

		menuApp.printListOfMenuItems();
		while (true) 
		{
			while(true)
			{
				try {
					System.out.println("Enter menu item choice. Or -1 to Quit");
					choice = sc.nextInt(); // have not accounted for arrayOutOfBounds Error
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("Invalid choice!");
					sc.nextLine();
				}
			}
			if(choice==-1)
				break;
			try 
			{
				MenuItem selectedMenuItem = menuApp.getMenuItem(choice);
				customerOrder.addItemToOrder(selectedMenuItem);
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Invalid choice!");
			}
		}
		customerOrder.setOrderID(orderIDtracker);

		customerOrder.getTable();
		orderIDtracker++;
		viewOrder(orderIDtracker);
		System.out.print("Please take note that this is your orderID: ");
		System.out.println(customerOrder.getOrderID());
		System.out.println();
		listOfOrders.add(customerOrder);
		customerOrder.setStaff(staffApp.selectStaff());
		customerOrder.setCustomer();

		customerOrder.assignTable();


	}
	//------------------------------------------------------------------------------------------------------------
	/**
	 * A Do-While loop to update existing order (adding more items of AlaCarteItem and add PromotionalSet to it) by orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public void updateOrder(int orderID) 
	{
		sc = new Scanner(System.in);
		Order selectedOrder = null;
		for (int i=0; i<listOfOrders.size(); i++) 
		{
			if (listOfOrders.get(i).getOrderID() == orderID) 
			{
				selectedOrder = listOfOrders.get(i);
				break;
			}
		}

		if (selectedOrder == null) 
		{
			System.out.println("No such order");
		}
		else if (selectedOrder.isCompleted() == true) 
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
					menuApp.printListOfMenuItems();
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
						if(choice==-1)
							return;
						try 
						{
							MenuItem selectedMenuItem = menuApp.getMenuItem(choice);
							selectedOrder.addItemToOrder(selectedMenuItem);
						}
						catch(IndexOutOfBoundsException e)
						{
							System.out.println("Invalid choice!");
						}
					}
					viewOrder(orderID);
				}
				else if (choice == 2) 
				{
					ArrayList<MenuItem> listOfItemsInCurrOrder = selectedOrder.getListOfItemsOrdered();
					for(int i=0; i<listOfItemsInCurrOrder.size(); i++) 
					{
						System.out.println((i+1)+") "+listOfItemsInCurrOrder.get(i).getName());
					}

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
						if(choice==-1)
							return;
						try 
						{
							selectedOrder.removeItemFromOrder(choice-1);
						}
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
	/**
	 * A Do-While loop to update existing order (removing more items of AlaCarteItem and add PromotionalSet to it) by orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public void removeOrder(int orderID) {
		Order currOrder;

		for(int i = 0; i< listOfOrders.size(); i++)
		{
			if(listOfOrders.get(i).getOrderID() == orderID) 
			{
				currOrder = listOfOrders.get(i);
				System.out.println("Order removed");
				return;
			}
		}
		System.out.println("No such order");
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
				System.out.println("No.\tItem\tPrice");
				for(int j = 0; j< selectedOrder.getListOfItemsOrdered().size(); j++) 
				{
					MenuItem currItem = selectedOrder.getListOfItemsOrdered().get(j);
					System.out.println((j+1)+")\t"+currItem.getName() + "\t$" + currItem.getPrice());
				}
				System.out.println("---------------------------------------------");
				System.out.println("Total Price: $ "+selectedOrder.getTotalPriceOfOrder());
				
				System.out.println("=============================================");
				System.out.println();
				return;
			}
		}
		System.out.println("No such order");
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

		for(int i = 0; i< listOfOrders.size(); i++) 
		{
			if(listOfOrders.get(i).getOrderID()==orderID) 
			{
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
