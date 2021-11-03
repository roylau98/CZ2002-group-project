import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderApp implements Serializable{
	
	private transient Scanner sc;
	private final OrderMgr orderMgr = new OrderMgr();
	private final Menu menuApp = new Menu();
	
	public void orderAppOptions(ReservationMgr reservationMgr) {
		sc = new Scanner(System.in);
		int choice = 0;

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
					viewOrderOption();
					break;
				case 2:
					createOrderOption(reservationMgr);
					break;
				case 3:
					removeOrderOption();
					break;
				case 4:
					updateOrderOption();
					break;
						
				case 5:
					chargeBillOption(reservationMgr);
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
	public void SalesReportApp()
	{
		orderMgr.salesReportOptions();
	}
	
	public void viewOrderOption()
	{
		int input;
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
		{
			System.out.println("Exited!");
			return;
		}
		else
			orderMgr.viewOrder(input);
	}
	public void createOrderOption(ReservationMgr reservationMgr)
	{
		int choice;
		
		System.out.println("Creating Order......");
		int tableNo = -1;
		Order customerOrder = new Order();
		try {
			System.out.println("Which table is ordering now?");
			reservationMgr.viewTablesWithReservationsNow();
			tableNo = sc.nextInt();
			sc.nextLine();
			customerOrder.setAssignedTable(tableNo);
		} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid input");
		}
		customerOrder.setCustomer(reservationMgr.getCustomerAt(tableNo));
		if (customerOrder.getCustomer() == null) {
			System.out.println("Failed to create Order! Exiting");
			return;
		}
		// customerOrder.setCustomer(reservationMgr.getReservationAtTableNow(customerOrder.getTable().getTableID()).getCustomer());
		// reservationApp.getReservationMgr().customerArrivedAt(customerOrder.getTable().getTableID());
		
		
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
		orderMgr.createOrder(customerOrder);
	}
	public void removeOrderOption()
	{
		int input;
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
		{
			System.out.println("Exited!");
			return;
		}
		else
			orderMgr.removeOrder(input);
	}
	public void updateOrderOption()
	{
		int input;
		Order selectedOrder;
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
		{
			selectedOrder=orderMgr.getOrder(input);
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
						orderMgr.viewOrder(input);
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
						orderMgr.updateOrder(input, selectedOrder);
						break;
					}
					else 
					{
						System.out.println("Invalid input. Try again!");
					}
				}

			}
		}
	}
	public void chargeBillOption(ReservationMgr reservationMgr)
	{
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
		if(input==-1)
			System.out.println("Exited!");
		else
			orderMgr.chargeBill(reservationMgr, input);
	}
}

