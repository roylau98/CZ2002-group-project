import java.time.LocalDateTime;
import java.util.*;

/**
 * Stores information of a order invoices in the restaurant.
 * <p>
 * This class keep track of the sold items ({@link Order}) made by the customer,
 * calculate the final price, and print the order invoice which consist
 * of {@code Order} details and price breakdown.
 *
 * <p>
 * The price is broken down into:
 * <ul>
 * 	<li>{@code totalPrice}: The price for all items that customer had ordered 
 * 	<li>{@code gst}: Goods and service tax 
 * 	<li>{@code serviceCharge}: Service charge of
 * 	<li>{@code finalPrice}: The amount that customer will pay. This final price take accounts the GST,service charge
 * </ul>
 *  
 * @author Chua Zi Jian
 */

public class Invoice {

	private double gst;
	private double serviceCharge;
	private double totalPrice;
	private double finalPrice;
	private Order order;
	private ArrayList<MenuItem> listOfSoldItems;
	private LocalDateTime timestamp;
	private int tableNo;
	
	/**
    	 * Constructs an {@code Invoice} object with default value of GST,serviceCharge and order
     	 */
	public Invoice() {
		gst = 0.07;
		serviceCharge = 0.02;
		totalPrice = 0;
		finalPrice = 0;
		order = null;
		listOfSoldItems = null;
		timestamp = LocalDateTime.now();
	}
	/**
    	 * Constructs an {@code Invoice} object with specific value of GST,serviceCharge and order
	 * @param gst		goods and service tax
	 * @param serviceCharge	service charge from the restaurant
	 * @param order		the ordered item from customer
	 */
	public Invoice(double gst, double serviceCharge, Order order) {
		this.gst = gst;
		this.serviceCharge = serviceCharge;
		this.totalPrice = 0;
		this.finalPrice = 0;
		this.order = order;
		listOfSoldItems = order.getListOfItemsOrdered();
		timestamp = LocalDateTime.now();
	}


	public void setOrder(Order order) {
		this.order = order;
		this.totalPrice = order.getTotalPriceOfOrder();
		calculateFinalPrice();
		this.finalPrice = getFinalPrice();
		this.listOfSoldItems = order.getListOfItemsOrdered();
	}
	
	/**
    	 * Print the Invoice of this order
     	 */
	public void printInvoice() {
		System.out.println("TimeStamp: " + getTimestamp());
		for (int i = 0; i< listOfSoldItems.size(); i++) {
			System.out.println((i+1)+" "+listOfSoldItems.get(i).getName()+" "+listOfSoldItems.get(i).getPrice());
		}
		calculateFinalPrice();
		System.out.println("Order's Total Price : "+getTotalPrice());
		System.out.println("Order's Final Price : "+getFinalPrice());
		order.setOrderAsCompleted();
	}
	
	
	/**
    	 * Return the final price that take accounts of GST,service charge 
     	 */
	public double getFinalPrice() {
		return finalPrice;
	}
	/**
    	 * Return the total price of the ordered item(exclude GST,service charge )
     	 */	
	public double getTotalPrice() {
		return totalPrice;
	}
	/**
    	 *  Return the list of item ordered by the customer
     	 */
	public ArrayList<MenuItem> getListOfSoldItems() {
		return listOfSoldItems;
	}

	/**
    	 * Calculate and return the final price that take accounts of GST,service charge
     	 */
	public double calculateFinalPrice() {
		for (int i = 0; i<order.getListOfItemsOrdered().size(); i++) {
			totalPrice = totalPrice + order.getListOfItemsOrdered().get(i).getPrice();
		}
		finalPrice = (totalPrice * (1+serviceCharge)) * (1+gst) ;
		return finalPrice;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}
