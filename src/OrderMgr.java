import java.io.Serializable;
import java.util.ArrayList;

/**
 * Manages all the {@link Order} objects of the whole restaurants,
 * basically the "manager" of {@link RRPSS} to {@link Order} objects
 * <p>
 * This class provides various methods to create,update(add/remove MenuItem) of the order,
 * and view individual order details and bills.
 * <p>
 *
 * @since 2021-11-5
 */

public class OrderMgr implements Serializable {
    /**
     * List of Order implemented in {@link ArrayList} data structure.
     * Each entry consists of a reference to existing {@link Order}object.
     */
    private final ArrayList<Order> listOfOrders;
    private int orderIDTracker;

    /**
     * Constructs an {@code OrderApp} object and
     * initialize the attributes {@code Order}/{@code }/{@code } .
     */
    public OrderMgr() {
        listOfOrders = new ArrayList<>();
        orderIDTracker = 0;
    }

    public boolean validateOrderID(int orderID) {
        for (Order o : listOfOrders) {
            if (o.getOrderID() == orderID)
                return true;
        }
        return false;
    }

    public int getTotalNoOfOrders() {
        return listOfOrders.size();
    }

    public int createOrder(Customer customer, int tableNo, Staff staff) {
        Order o = new Order(orderIDTracker++, customer, staff, tableNo);
        listOfOrders.add(o);
        return o.getOrderID();
    }

    /**
     * A Do-While loop to update existing order (adding more items of AlaCarteItem and add PromotionalSet to it) by orderID
     *
     * @param orderID The ID that is used to indicate existing {@link Order} object
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

    public void printItemsInOrder(int orderID) {
        Order selectedOrder = getOrder(orderID);
        for (int i = 0; i < selectedOrder.getListOfItemsOrdered().size(); i++) {
            System.out.println(i + ") " + selectedOrder.getListOfItemsOrdered().get(i).getName() + selectedOrder.getListOfItemsOrdered().get(i).getPrice());
        }
    }

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
     * A Do-While loop to update existing order (removing more items of AlaCarteItem and add PromotionalSet to it) by orderID
     *
     * @param orderID The ID that is used to indicate existing {@link Order} object
     */
    public void removeOrder(int orderID) {
        Order currOrder = getOrder(orderID);
        if (currOrder == null) {
            System.out.println("No such order");
        } else {
            System.out.println("Order removed");
        }
    }
    //-----------------------------------------------------------------------------------------------------------------------

    /**
     * A function that printout the existing items in order by using orderID
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
            System.out.println((j + 1) + ")\t" + currItem.getName() + "\t$" + currItem.getPrice());
        }
        System.out.println("---------------------------------------------");
        System.out.println("Total Price: $ " + selectedOrder.getTotalPriceOfOrder());
        System.out.println("=============================================");
        System.out.println();
    }

    /**
     * A function that printout the bills in order by using orderID
     *
     * @param orderID The ID that is used to indicate existing {@link Order} object
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
        return bill;
    }
}
