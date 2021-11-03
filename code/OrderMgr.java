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
	/**
         * List of Order implemented in {@link ArrayList} data structure.
         * Each entry consists of a reference to existing {@link Order}object.
         */
	private ArrayList<Order> listOfOrders;
	private int orderIDtracker;

	/**
         * Constructs an {@code OrderApp} object and
         * initialize the attributes {@code Order}/{@code }/{@code } .
         */
	public OrderMgr() {
		listOfOrders = new ArrayList<>();
		orderIDtracker = 0;
	}

	public int createOrder(Customer customer, int tableNo, Staff staff) {

		Order customerOrder = new Order();

		customerOrder.setCustomer(customer);
		if (customerOrder.getCustomer() == null) {
			System.out.println("No Customer. Failed to create Order! Exiting");
			return -1;
		}
		customerOrder.setStaff(staff);
		if (customerOrder.getStaff() == null) {
			System.out.println("No Staff. Failed to create Order! Exiting");
			return -1;
		}

		customerOrder.setOrderID(orderIDtracker);
		orderIDtracker++;
		listOfOrders.add(customerOrder);

		return customerOrder.getOrderID();

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

	public void printItemsInOrder(int orderID) {
		Order selectedOrder = getOrder(orderID);

		for (int i =0; i < selectedOrder.getListOfItemsOrdered().size(); i++) {
			System.out.println(i+") "+ selectedOrder.getListOfItemsOrdered().get(i).getName() + selectedOrder.getListOfItemsOrdered().get(i).getPrice());
		}
	}

	public void addItemsInOrder(int orderID, MenuItem menuItem) {
		Order order = getOrder(orderID);
		order.addItemToOrder(menuItem);
	}

	public void removeItemsInOrder(int orderID, int index) {
		Order selectedOrder = getOrder(orderID);
		selectedOrder.removeItemFromOrder(index);
	}

	//--------------------------------------------------------------------------------------------------------------------
	/**
	 * A Do-While loop to update existing order (removing more items of AlaCarteItem and add PromotionalSet to it) by orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public void removeOrder(int orderID) {
		Order currOrder = getOrder(orderID);
		if (currOrder == null) {
			System.out.println("No such order");
		}
		else {
			System.out.println("Order removed");
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
	public Invoice chargeBill(ReservationMgr reservationMgr, int orderID) {
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
				reservationMgr.removeReservationAfterPayment(listOfOrders.get(i).getAssignedTable());
				return bill;
			}
		}

		System.out.println("No such order");
		return null;
	}

}
