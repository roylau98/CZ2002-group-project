import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Stores information of an order invoices in the restaurant.
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
 * @since 2021-11-12
 */

public class Invoice implements Serializable {
    /**
     * Goods service tax
     */
    private final double gst;
    /**
     * Service charge of the restaurant
     */
    private final double serviceCharge;
    /**
     * Discount for the customer who holds membership
     */
    private final double memberDiscount;
    /**
     * Timestamp of the invoice
     */
    private final LocalDateTime timestamp;
    /**
     * Total Price of the order(taxes excluded)
     */
    private double totalPrice;
    /**
     * Total Price of the order(taxes included)
     */
    private double finalPrice;
    /**
     * Order of the customer
     */
    private Order order;
    /**
     * Menu Items that have been ordered by customer
     */
    private ArrayList<MenuItem> listOfSoldItems;
    /**
     * Table no of this invoice
     */
    private int tableNo;

    /**
     * Constructs an {@code Invoice} object with default value of GST,serviceCharge and order
     */
    public Invoice() {
        gst = 0.07;
        serviceCharge = 0.02;
        totalPrice = 0;
        finalPrice = 0;
        memberDiscount = 0.1;
        order = null;
        listOfSoldItems = null;
        timestamp = LocalDateTime.now();
    }

    /**
     * Constructs an {@code Invoice} object with specific value of GST,serviceCharge and order
     *
     * @param gst           goods and service tax
     * @param serviceCharge service charge from the restaurant
     * @param order         the ordered item from customer
     */
    public Invoice(double gst, double serviceCharge, Order order) {
        this.gst = gst;
        this.serviceCharge = serviceCharge;
        this.totalPrice = 0;
        this.finalPrice = 0;
        this.order = order;
        this.memberDiscount = 0.1;
        this.listOfSoldItems = order.getListOfItemsOrdered();
        this.timestamp = LocalDateTime.now();
        this.tableNo = this.order.getAssignedTable();
    }

    /**
     * Set a new Order object to this invoice
     *
     * @param order order from the customer
     */
    public void setOrder(Order order) {
        this.order = order;
        this.totalPrice = order.getTotalPriceOfOrder();
        calculateFinalPrice();
        this.finalPrice = getFinalPrice();
        this.listOfSoldItems = order.getListOfItemsOrdered();
    }

    /**
     * Print the Invoice for this order
     */
    public void printInvoice() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("                THE KRUSTY KRAB                  ");
        System.out.println("          Bikini Bottom, Pacific Ocean           ");
        System.out.println("                                                 ");
        System.out.println("                                                 ");
        System.out.println("Table No: " + this.tableNo);
        System.out.println("Order ID: " + this.order.getOrderID());
        System.out.println("TimeStamp: " + getTimestamp());
        System.out.println("-------------------------------------------------");
        System.out.println("No.\tItem Name\tPrice");
        for (int i = 0; i < listOfSoldItems.size(); i++) {
            System.out.printf((i + 1) + ".\t" + listOfSoldItems.get(i).getName() + "\t\t$%.2f\n" + listOfSoldItems.get(i).getPrice());
        }
        calculateFinalPrice();
        System.out.println("-------------------------------------------------");
        System.out.printf("Order's Total Price  :  $%.2f\n", getTotalPrice());
        System.out.printf("Taxes                :  $%.2f\n", (getTotalPrice() * (serviceCharge + gst)));
        if (this.order.getCustomer().getMembershipStatus())
            System.out.printf("Member Discount      : -$%.2f\n", (getTotalPrice() * (1 + serviceCharge) * (1 + gst) * (memberDiscount)));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("Order's Final Price  :  "
                + "$%.2f\n", getFinalPrice());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        System.out.println("          THANK YOU FOR DINING WITH US           ");
        System.out.println("                PLEASE COME AGAIN                ");
        System.out.println();
        System.out.println("=================================================");
        System.out.println();
        order.setOrderAsCompleted();
    }


    /**
     * Return the final price that take accounts of GST,service charge
     *
     * @return finalPrice    total price of order with taxes included.
     */
    public double getFinalPrice() {
        return finalPrice;
    }

    /**
     * Return the total price of the ordered item(exclude GST,service charge )
     *
     * @return totalPrice    total price of order with taxes excluded
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Return the list of item ordered by the customer
     *
     * @return listOfSoldItems        Menu items ordered in this order
     */
    public ArrayList<MenuItem> getListOfSoldItems() {
        return listOfSoldItems;
    }

    /**
     * Calculate the final price that take accounts of GST,service charge
     */
    public void calculateFinalPrice() {
        totalPrice = 0;
        for (int i = 0; i < order.getListOfItemsOrdered().size(); i++) {
            totalPrice = totalPrice + order.getListOfItemsOrdered().get(i).getPrice();
        }
        if (this.order.getCustomer().getMembershipStatus())
            finalPrice = totalPrice * (1 + serviceCharge) * (1 + gst) * (1 - memberDiscount);
        else
            finalPrice = totalPrice * (1 + serviceCharge) * (1 + gst);
    }

    /**
     * Return timestamp of this invoice.
     *
     * @return timestamp of this invoice
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
