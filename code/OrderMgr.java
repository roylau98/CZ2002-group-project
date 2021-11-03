import java.io.Serializable;
import java.util.ArrayList;
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

public class OrderMgr implements Serializable {

	private transient Scanner sc = new Scanner(System.in);
	private Menu menuApp;
	/**
         * List of Order implemented in {@link ArrayList} data structure.
         * Each entry consists of a reference to existing {@link Order}object.
         */
	private ArrayList<Order> listOfOrders;
	private StaffApp staffApp;
	private SalesReport salesReport;
	private int orderIDtracker;

	/**
         * Constructs an {@code OrderApp} object and
         * initialize the attributes {@code Order}/{@code }/{@code } .
         */
	public OrderMgr() {
		listOfOrders = new ArrayList<>();
		salesReport = new SalesReport();
		orderIDtracker = 0;
		staffApp = new StaffApp();
		menuApp = new Menu();
	}

	public void assignTableForOrder(ReservationMgr reservationMgr) {
		try {
			System.out.println("Which table is ordering now?");
			reservationMgr.viewTablesWithReservationsNow();
			tableNo = sc.nextInt();
			sc.nextLine();
			customerOrder.setAssignedTable(tableNo);
		}
		catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid input");
		}
	}


	public void createOrder(ReservationMgr reservationMgr) {
		sc = new Scanner(System.in);
		int choice=999;

		Order customerOrder = new Order();
		int tableNo = -1;

		customerOrder.setCustomer(reservationMgr.getCustomerAt(tableNo));
		if (customerOrder.getCustomer() == null) {
			System.out.println("Failed to create Order! Exiting");
			return;
		}
		// customerOrder.setCustomer(reservationMgr.getReservationAtTableNow(customerOrder.getTable().getTableID()).getCustomer());
		// reservationApp.getReservationMgr().customerArrivedAt(customerOrder.getTable().getTableID());
		customerOrder.setOrderID(orderIDtracker);
		orderIDtracker++;
		customerOrder.setStaff(staffApp.selectStaff());

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

		viewOrder(orderIDtracker);
		System.out.print("Please take note that this is your orderID: "+customerOrder.getOrderID());
		listOfOrders.add(customerOrder);
	}


	//------------------------------------------------------------------------------------------------------------
	/**
	 * A Do-While loop to update existing order (adding more items of AlaCarteItem and add PromotionalSet to it) by orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public Order getOrder(int orderID)
	{
		Order selectedOrder = null;
		for (int i=0; i<listOfOrders.size(); i++) 
		{
			if (listOfOrders.get(i).getOrderID() == orderID) 
			{
				selectedOrder = listOfOrders.get(i);
				break;
			}
		}
		return selectedOrder;
	}

	public void updateOrder(int orderID,Order updatedOrder)
	{
		sc = new Scanner(System.in);
		Order selectedOrder = null;
		for (int i=0; i<listOfOrders.size(); i++)
		{
			if (listOfOrders.get(i).getOrderID() == orderID)
			{
				selectedOrder = listOfOrders.get(i);
				selectedOrder = updatedOrder;
				break;
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
				System.out.println("Total Price: $ "+selectedOrder.getTotalPriceOfOrder();
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
	public void chargeBill(ReservationMgr reservationMgr, int orderID) {
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
				reservationMgr.removeReservationAfterPayment(listOfOrders.get(i).getAssignedTable());
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

	public void openMenuApp()
	{
		menuApp.menuOptions();
	}


}
