import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Stores all important information of an order created for a customer. This class stores
 * the ID, date and time of the {@link Order} object creation, the {@link Staff}
 * object who created the {@link Order}, and the details of menu item
 *
 * @author
 * @since 2021-11-5
 */
public class Order implements Serializable {
    /**
     * Date time when the order is created
     */
    private final LocalDateTime dateTimeOrderCreated;
    /**
     * Menu item ordered by customer
     */
    private final ArrayList<MenuItem> itemsOrdered;
    /**
     * ID tracker for the order
     */
    private int orderID;
    /**
     * Customer of this order
     */
    private Customer customer;
    /**
     * Staff who serves this order
     */
    private Staff orderCreatedBy;
    /**
     * Boolean of the completeness of order(true=completed)
     */
    private Boolean completedStatus;
    /**
     * Total price of the order(taxes excluded)
     */
    private double totalPriceOfOrder;
    /**
     * Invoice of this order
     */
    private Invoice orderInvoice;
    /**
     * Table no of this order
     */
    private int assignedTable;

    /**
     * Class constructor with default settings
     */
    public Order() {
        orderID = 9999;
        dateTimeOrderCreated = LocalDateTime.now();
        itemsOrdered = new ArrayList<MenuItem>();
        customer = null;
        orderCreatedBy = null;
        completedStatus = false;
        totalPriceOfOrder = 0;
        orderInvoice = null;
        assignedTable = -1;
    }

    /**
     * Class constructor
     *
     * @param orderID        ID of this order
     * @param customer       customer of this order
     * @param orderCreatedBy staff who serves this order
     * @param assignedTable  table no of this order
     */
    public Order(int orderID, Customer customer, Staff orderCreatedBy, int assignedTable) {
        this.orderID = orderID;
        this.dateTimeOrderCreated = LocalDateTime.now();
        this.itemsOrdered = new ArrayList<MenuItem>();
        this.customer = customer;
        this.orderCreatedBy = orderCreatedBy;
        this.completedStatus = false;
        this.totalPriceOfOrder = 0;
        this.orderInvoice = null;
        this.assignedTable = assignedTable;
    }

    /**
     * Gets Date time when this order is created
     *
     * @return dateTimeOrderCreated   Date time when this order is created
     */
    public LocalDateTime getDateTimeOrderCreated() {
        return dateTimeOrderCreated;
    }

    /**
     * Return customer of this order
     *
     * @return customer    customer of this order
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Update the customer of this order
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets Invoice of this order
     *
     * @return orderInvoice        Invoice of this order
     */
    public Invoice getInvoice() {
        return orderInvoice;
    }

    /**
     * Creates Invoice for this order
     *
     * @param order the order made by customer
     */
    public void createInvoice(Order order) {
        orderInvoice = new Invoice();
        orderInvoice.setOrder(order);
    }

    /**
     * Gets order ID of this order
     *
     * @return orderID        order ID of this order
     */
    public int getOrderID() {
        return this.orderID;
    }

    /**
     * Update the order ID of this order
     *
     * @param orderID updated order ID
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Return true if this order is completed,false otherwise
     *
     * @return true if order is completed,false otherwise
     */
    public Boolean isCompleted() {
        return completedStatus;
    }

    /**
     * Set this order as complete
     */
    public void setOrderAsCompleted() {
        this.completedStatus = true;
    }

    /**
     * Gets the table no. of this order
     *
     * @return assignedTable    table no. of this order
     */
    public int getAssignedTable() {
        return assignedTable;
    }

    /**
     * Update the table no. of this order
     *
     * @param assignedTable updated table no.
     */
    public void setAssignedTable(int assignedTable) {
        this.assignedTable = assignedTable;
    }

    /**
     * Gets staff that serves this table
     *
     * @return orderCreatedBy    staff that serves this order
     */
    public Staff getStaff() {
        return orderCreatedBy;
    }

    /**
     * Update the staff that serves this order
     *
     * @param s updated staff to serve this order
     */
    public void setStaff(Staff s) {
        orderCreatedBy = s;
    }

    /**
     * Gets menu items ordered by customer
     *
     * @return itemsOrdered    array list of item ordered by customer
     */
    public ArrayList<MenuItem> getListOfItemsOrdered() {
        return itemsOrdered;
    }

    /**
     * Calculate the total price of this order(taxes excluded)
     */
    public void calculatePriceOfOrder() {
        totalPriceOfOrder = 0;
        for (int i = 0; i < itemsOrdered.size(); i++) {
            totalPriceOfOrder = totalPriceOfOrder + itemsOrdered.get(i).getPrice();
        }
    }

    /**
     * Gets total price of this order(taxes excluded)
     *
     * @return totalPriceOfOrder    total price of this order(taxes excluded)
     */
    public double getTotalPriceOfOrder() {
        calculatePriceOfOrder();
        return totalPriceOfOrder;
    }

    /**
     * Add menu item to this order
     *
     * @param itemToBeAdded menu item to be added
     */
    public void addItemToOrder(MenuItem itemToBeAdded) {
        itemsOrdered.add(itemToBeAdded);
    }

    /**
     * Remove menu item from this order
     *
     * @param index index no of the ordered item to be removed
     */
    public void removeItemFromOrder(int index) {
        itemsOrdered.remove(index);
    }

    /**
     * Gets the number of items in the order
     *
     * @return the number of items in the order
     */
    public int getNumberOfItemsOrdered() {
        return itemsOrdered.size();
    }
}
