import java.time.LocalDateTime;
import java.util.ArrayList;

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
 * @author 
 */

public class Invoice {

	private double gst;
	private double serviceCharge;
	private double totalPrice;
	private double finalPrice;
	private double memberDiscount;
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
		memberDiscount = 1;
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
		
		if((this.order.getCustomer()).getMembershipStatus()==true)
			memberDiscount=0.01;
		else
			memberDiscount=1;
		
		listOfSoldItems = order.getOrderContents();
		timestamp = LocalDateTime.now();
	}
	
	
	/**
    	 * Print the Invoice of this order
     	 */
	public void printInvoice() {
		for (int i=0; i<order.getOrderContents().size(); i++) {
			System.out.println((i+1)+" "+order.getOrderContents().get(i).getName()+" "+order.getOrderContents().get(i).getPrice());
		}
		calculateSale();
		System.out.println("Total Price : "+getTotalPrice());
		System.out.println("Total Price : "+getFinalPrice());
		order.orderComplete();
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
    	 * Calculate and return the final price that take accounts of GST,service charge
     	 */
	public double calculateSale() {
		for (int i=0; i<order.getOrderContents().size(); i++) {
			totalPrice = totalPrice + order.getOrderContents().get(i).getPrice();
		}
		finalPrice = (totalPrice * (1+serviceCharge)) * (1+gst)*(1-memberDiscount) ;
		return finalPrice;
	}
	
	
	/**
    	 *  Return the list of item ordered by the customer
     	 */
	public ArrayList<MenuItem> getListOfSoldItems() {
		return listOfSoldItems;
	}

}
