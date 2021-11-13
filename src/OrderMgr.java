import java.io.Serializable;
import java.util.ArrayList;

/**
 * Manages all the {@link Order} objects of the whole restaurants,
 * basically the "manager" of {@link RRPSS} to {@link Order} objects
 * <p>
 * This class provides various methods to create,update(add/remove {@link MenuItem}) of the {@link Order},
 * and view individual {@link Order} details and {@link Invoice}
 * <p>
 *
 * @since 2021-11-12
 */

public class OrderMgr implements Serializable {
    /**
     * List of {@link Order} implemented in {@link ArrayList} data structure.
     * Each entry consists of a reference to existing {@link Order}object.
     */
    private final ArrayList<Order> listOfOrders;

    /**
     * Used for tracking of {@link Order}
     */
    private int orderIDTracker;

    /**
     * Class Constructor
     */
    public OrderMgr() {
        listOfOrders = new ArrayList<>();
        orderIDTracker = 0;
    }

    /**
     * Check for validate of the orderID
     *
     * @param orderID order id to be checked
     * @return true if the orderID is valid,false otherwise
     */
    public boolean validateOrderID(int orderID) {
        for (Order o : listOfOrders) {
            if (o.getOrderID() == orderID)
                return true;
        }
        return false;
    }

    /**
     * Print all existing {@link Order}order and their details
     */
    public void printAllOrders() {
        System.out.println("Current Existing Order: ");
        for (Order order : getListOfOrder()) {
            System.out.println("Order " + order.getOrderID() + " : ");
            viewOrder(order.getOrderID());
        }
    }

    /**
     * Print all existing order id
     */
    public void printAllOrderID() {
        System.out.println("Current Existing Order: ");
        for (Order order : getListOfOrder()) {
            System.out.println("Order " + order.getOrderID());
        }
    }

    /**
     * Get total size of the list of {@link Order}
     *
     * @return total size of the list of {@link Order}
     */
    public int getTotalNoOfOrders() {
        return listOfOrders.size();
    }

    /**
     * Create a new {@link Order}
     *
     * @param customer customer that made the {@link Order}
     * @param tableNo  table no of this {@link Order}
     * @param staff    {@link Staff} that serves this table
     * @return order id
     */
    public int createOrder(Customer customer, int tableNo, Staff staff) {
        Order o = new Order(orderIDTracker++, customer, staff, tableNo);
        listOfOrders.add(o);
        return o.getOrderID();
    }

    /**
     * Get {@link Order} by orderID
     *
     * @param orderID The ID that is used to indicate existing {@link Order} object
     * @return selectedOrder    the {@link Order} object
     */
    public Order getOrder(int orderID) {
        Order selectedOrder = null;
        for (Order order : listOfOrders) {
            if (order.getOrderID() == orderID) {
                selectedOrder = order;
                break;
            }
        }
        return selectedOrder;
    }

    /**
     * Return list of {@link Order} made
     *
     * @return All {@link Order} made by customer
     */
    public ArrayList<Order> getListOfOrder() {
        return listOfOrders;
    }

    /**
     * Check whether order has been made in certain {@link Table}
     *
     * @param tableNo {@link Table} to be checked
     * @return true if order of this table has been made,false otherwise
     */
    public boolean checkforTableOrder(int tableNo) {
        for (Order order : getListOfOrder()) {
            if (order.getAssignedTable() == tableNo) {
                return true;
            }
        }
        return false;
    }

    /**
     * Print items of a specific {@link Order}
     *
     * @param orderID ID of the {@link Order}
     */
    public void printItemsInOrder(int orderID) {
        Order selectedOrder = getOrder(orderID);
        for (int i = 0; i < selectedOrder.getListOfItemsOrdered().size(); i++) {
            System.out.printf(i + ")" + selectedOrder.getListOfItemsOrdered().get(i).getName() + "/t$ " + selectedOrder.getListOfItemsOrdered().get(i).getPrice());
        }
    }

    /**
     * Add menu items into a specific {@link Order}
     *
     * @param orderID  ID of the {@link Order}
     * @param menuItem {@link MenuItem} to be added
     */
    public void addItemsInOrder(int orderID, MenuItem menuItem) {
        Order order = getOrder(orderID);
        if (menuItem == null) {
            return;
        }
        if (order == null) {
            return;
        }
        order.addItemToOrder(menuItem);
    }

    /**
     * Remove {@link MenuItem} from a specific {@link Order}
     *
     * @param orderID ID of the {@link Order}
     * @param index   index of ordered item to be removed
     */
    public void removeItemsInOrder(int orderID, int index) {
        Order selectedOrder = getOrder(orderID);
        if (selectedOrder == null) {
            return;
        }
        if (index < 0 || index >= selectedOrder.getListOfItemsOrdered().size()) {
            return;
        }
        selectedOrder.removeItemFromOrder(index);
    }

    //--------------------------------------------------------------------------------------------------------------------

    /**
     * Remove existing {@link Order} by orderID
     *
     * @param orderID The ID that is used to indicate existing {@link Order} object
     */
    public void removeOrder(int orderID) {
        Order currOrder = getOrder(orderID);
        if (currOrder == null) {
            System.out.println("No such order");
        } else {
            System.out.println("Order removed");
            for (int i = 0; i < listOfOrders.size(); i++)
                if (listOfOrders.get(i) == currOrder)
                    listOfOrders.remove(i);
        }
    }
    //-----------------------------------------------------------------------------------------------------------------------

    /**
     * A function that printout the existing items in {@link Order} by using orderID
     *
     * @param orderID The ID that is used to indicate existing {@link Order} object
     */

    public void viewOrder(int orderID) {
        Order selectedOrder = getOrder(orderID);
        if (selectedOrder == null) {
            System.out.println("No such order");
            return;
        }
        System.out.println("==============Your Current Order=============");
        System.out.println("No.\tItem\tPrice");
        for (int j = 0; j < selectedOrder.getListOfItemsOrdered().size(); j++) {
            MenuItem currItem = selectedOrder.getListOfItemsOrdered().get(j);
            System.out.printf((j + 1) + ")\t %s\t$%.2f\n", currItem.getName(), currItem.getPrice());
            //System.out.println((j + 1) + ")\t" + currItem.getName() + "\t$" + currItem.getPrice());
        }
        System.out.println("---------------------------------------------");
        System.out.printf("Total Price: $ %.2f\n", selectedOrder.getTotalPriceOfOrder());
        System.out.println("=============================================");
        System.out.println();
    }

    /**
     * A function that printout the bills in {@link Order} by using orderID
     *
     * @param reservationMgr The reservation manager of the app
     * @param orderID        The ID that is used to indicate existing {@link Order} object
     * @return The {@link Invoice} object for this {@link Order}
     */
    public Invoice chargeBill(ReservationMgr reservationMgr, int orderID) {
        Order selectedOrder = getOrder(orderID);
        if (selectedOrder == null) {
            System.out.println("No such order");
            return null;
        }
        selectedOrder.createInvoice(selectedOrder);
        Invoice bill = selectedOrder.getInvoice();
        bill.printInvoice();
        reservationMgr.removeReservationAfterPayment(selectedOrder.getAssignedTable());
        removeOrder(orderID);
        return bill;
    }
}
