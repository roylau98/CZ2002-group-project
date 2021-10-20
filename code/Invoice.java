import java.time.LocalDateTime;
import java.util.ArrayList;

public class Invoice {

	private double gst;
	private double serviceCharge;
	private double totalPrice;
	private double finalPrice;
	private Order order;
	private ArrayList<MenuItem> listOfSoldItems;
	private LocalDateTime timestamp;
	private int tableNo;

	public Invoice() {
		gst = 0.7;
		serviceCharge = 0.2;
		totalPrice = 0;
		finalPrice = 0;
		order = null;
		listOfSoldItems = null;
		timestamp = LocalDateTime.now();
	}
	public Invoice(double gst, double serviceCharge, Order order) {
		this.gst = gst;
		this.serviceCharge = serviceCharge;
		this.totalPrice = 0;
		this.finalPrice = 0;
		this.order = order;
		listOfSoldItems = order.getListOfItemsOrdered();
		timestamp = LocalDateTime.now();
	}
	public void printInvoice() {
		for (int i = 0; i<order.getListOfItemsOrdered().size(); i++) {
			System.out.println((i+1)+" "+order.getListOfItemsOrdered().get(i).getName()+" "+order.getListOfItemsOrdered().get(i).getPrice());
		}
		calculateFinalPrice();
		System.out.println("Total Price : "+getTotalPrice());
		System.out.println("Total Price : "+getFinalPrice());
		order.setOrderAsCompleted();
	}

	public double getFinalPrice() {
		return finalPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public ArrayList<MenuItem> getListOfSoldItems() {
		return listOfSoldItems;
	}

	public double calculateFinalPrice() {
		for (int i = 0; i<order.getListOfItemsOrdered().size(); i++) {
			totalPrice = totalPrice + order.getListOfItemsOrdered().get(i).getPrice();
		}
		finalPrice = (totalPrice * (1+serviceCharge)) * (1+gst) ;
		return finalPrice;
	}



}