import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Stores all important information of an order created for a customer. This class stores
 * the ID, date and time of the {@code Order} object creation, the {@link Staff}
 * object who created the {@code Order}, and the details of menu item
 *
 * @since 2021-11-5
 */
public class Order implements Serializable {
    /**
     * Date time when the {@code Order} is created
     */
    private final LocalDateTime dateTimeOrderCreated;
    /**
     * Menu item ordered by {@link Customer}
     */
    private final ArrayList<MenuItem> itemsOrdered;
    /**
     * ID tracker for the {@code Order}
     */
    private int orderID;
    /**
     * Customer of this {@code Order}
     */
    private Customer customer;
    /**
     * Staff who serves this {@code Order}
     */
    private Staff orderCreatedBy;
    /**
     * Boolean to indicate whether {@code Order} is completed(true=completed)
     */
    private Boolean completedStatus;
    /**
     * Total price of the {@code Order}(taxes excluded)
     */
    private double totalPriceOfOrder;
    /**
     * Invoice of this {@code Order}
     */
    private Invoice orderInvoice;
    /**
     * Table no of this {@code Order}
     */
    private int assignedTable;

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
        this.itemsOrdered = new ArrayList<>();
        this.customer = customer;
        this.orderCreatedBy = orderCreatedBy;
        this.completedStatus = false;
        this.totalPriceOfOrder = 0;
        this.orderInvoice = null;
        this.assignedTable = assignedTable;
    }

    /**
     * Gets Date time when this {@code Order} is created
     *
     * @return dateTimeOrderCreated   Date time when this {@code Order}is created
     */
    public LocalDateTime getDateTimeOrderCreated() {
        return dateTimeOrderCreated;
    }

    /**
     * Return customer of this {@code Order}
     *
     * @return customer of this {@code Order}
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Update the customer of this {@code Order}
     *
     * @param    customer    the updated customer of this {@code Order}
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets Invoice for this {@code Order}
     *
     * @return orderInvoice        Invoice of this {@code Order}
     */
    public Invoice getInvoice() {
        return orderInvoice;
    }

    /**
     * Creates Invoice for this {@code Order}
     *
     * @param order the {@code Order} made by {@link Customer}
     */
    public void createInvoice(Order order) {
        orderInvoice = new Invoice();
        orderInvoice.setOrder(order);
    }

    /**
     * Gets order ID of this {@code Order}
     *
     * @return orderID        order ID of this {@code Order}
     */
    public int getOrderID() {
        return this.orderID;
    }

    /**
     * Update the order ID of this {@code Order}
     *
     * @param orderID updated order ID
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Return true if this {@code Order} is completed,false otherwise
     *
     * @return true if {@code Order} is completed,false otherwise
     */
    public Boolean isCompleted() {
        return completedStatus;
    }

    /**
     * Set this {@code Order} as complete
     */
    public void setOrderAsCompleted() {
        this.completedStatus = true;
    }

    /**
     * Gets the table no. of this {@code Order}
     *
     * @return assignedTable    table no. of this {@code Order}
     */
    public int getAssignedTable() {
        return assignedTable;
    }

    /**
     * Update the table no. of this {@code Order}
     *
     * @param assignedTable updated table no.
     */
    public void setAssignedTable(int assignedTable) {
        this.assignedTable = assignedTable;
    }

    /**
     * Gets staff that serves this table
     *
     * @return orderCreatedBy    staff that serves this {@code Order}
     */
    public Staff getStaff() {
        return orderCreatedBy;
    }

    /**
     * Update the staff that serves this {@code Order}
     *
     * @param s updated staff to serve this {@code Order}
     */
    public void setStaff(Staff s) {
        orderCreatedBy = s;
    }

    /**
     * Gets array list of {@link MenuItem} ordered by customer
     *
     * @return itemsOrdered    array list of item ordered by customer
     */
    public ArrayList<MenuItem> getListOfItemsOrdered() {
        return itemsOrdered;
    }

    /**
     * Calculate the total price of this {@code Order}(taxes excluded)
     */
    public void calculatePriceOfOrder() {
        totalPriceOfOrder = 0;
        for (MenuItem menuItem : itemsOrdered) {
            totalPriceOfOrder += menuItem.getPrice();
        }
    }

    /**
     * Gets total price of this {@code Order}(taxes excluded)
     *
     * @return  total price of this order(taxes excluded)
     */
    public double getTotalPriceOfOrder() {
        calculatePriceOfOrder();
        return totalPriceOfOrder;
    }

    /**
     * Add menu item to this {@code Order}
     *
     * @param itemToBeAdded {@link MenuItem} item to be added
     */
    public void addItemToOrder(MenuItem itemToBeAdded) {
        itemsOrdered.add(itemToBeAdded);
    }

    /**
     * Remove menu item from this {@code Order}
     *
     * @param index index no of the ordered item to be removed
     */
    public void removeItemFromOrder(int index) {
        itemsOrdered.remove(index);
    }

    /**
     * Gets the number of items in the {@code Order}
     *
     * @return the number of items in the {@code Order}
     */
    public int getNumberOfItemsOrdered() {
        return itemsOrdered.size();
    }
}
