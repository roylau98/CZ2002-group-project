import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interface of the Order App which has the option of create/remove etc. order
 * <p>
 *
 * @since 2021-11-5
 */

public class OrderApp implements Serializable {
    /**
     * Object Manager of Order
     */
    private final OrderMgr orderMgr;
    /**
     * Object Manager of staff
     */
    private final StaffApp staffApp;
    /**
     * Manager of Sales Report
     */
    private final SalesReport salesReportApp;
    /**
     * Object Manager of MenuItem
     */
    private final MenuMgr menuMgrApp;
    /**
     * Manager of MenuItem
     */
    private final MenuApp menuApp;
    private transient Scanner sc = new Scanner(System.in);

    /**
     * Class Constructor
     *
     */
    public OrderApp() {
        orderMgr = new OrderMgr();
        staffApp = new StaffApp();
        salesReportApp = new SalesReport();
        menuApp = new MenuApp();
        menuMgrApp = menuApp.getMenuMgr();
    }

    /**
     * Open Sales Report options
     */
    public void salesReportOptions() {
        salesReportApp.options();
    }

    /**
     * Open Order App options
     */
    public void orderAppOptions(ReservationMgr reservationMgr) {
        sc = new Scanner(System.in);
        if (staffApp.getNoOfStaff()==0) {
            System.out.print("\nOrder App\n");
            System.out.println("No staff. Orders cannot be made");
            System.out.println("Returning back to main menu...");
            return;
        }
        if (menuMgrApp.getNumberOfMenuItems()==0) {
            System.out.print("Order App\n");
            System.out.println("No menu items. Orders cannot be made");
            System.out.println("Returning back to main menu...");
            return;
        }

        int choice = 0;
        do {
            System.out.print("\nOrder App\n" +
                    "Please select one of the options below:\n" +
                    "1. View an existing order\n" +
                    "2. Make a new order\n" +
                    "3. Cancel an existing order\n" +
                    "4. Update an existing order\n" +
                    "5. Make payment for an order\n" +
                    "6. Exit this application and return to the previous page\n" +
                    "Enter your choice: ");
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received.");
            }
            switch (choice) {
                case 1:
                    viewOrder();
                    break;
                case 2:
                    newOrder(reservationMgr);
                    break;
                case 3:
                    removeOrder();
                    break;
                case 4:
                    updateOrder();
                    break;
                case 5:
                    billOrder(reservationMgr);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid input received.");
                    break;
            }
        } while (choice != 6);
    }

    /**
     * View the specific order that has been created
     */
    private void viewOrder() {
        if (orderMgr.getTotalNoOfOrders() == 0) {
            System.out.println("No orders have been made.");
            return;
        }
        orderMgr.viewOrder(askUserForOrderID());
    }

    /**
     * Create a new order
     *
     * @param reservationMgr The reservation manager which would be informed about the customer's arrival.
     */
    private void newOrder(ReservationMgr reservationMgr) {
        System.out.println("Which table is this new order for? Enter -1 to Quit");
        reservationMgr.viewTablesWithReservationsNow();
        // update error checking e.g. throwable in viewTablesWithReservationsNow()....
        int tableNo;
        try {
            tableNo = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type received.");
            return;
        }
        if (tableNo == -1) {
            return;
        }
        Customer c = reservationMgr.getCustomerAt(tableNo);
        if (c == null) {
            System.out.println("Invalid input received.");
            return;
        }
        int newOrderID = orderMgr.createOrder(c, tableNo, staffApp.selectStaff());
        System.out.println("Order " + newOrderID + " has been created.");
        updateOrder(newOrderID);
    }

    /**
     * Remove a specific order from this app
     */
    private void removeOrder() {
        if (orderMgr.getTotalNoOfOrders() == 0) {
            System.out.println("No orders have been made.");
            return;
        }
        orderMgr.removeOrder(askUserForOrderID());
    }

    /**
     * Update a specific order from this app
     */
    private void updateOrder() {
        if (orderMgr.getTotalNoOfOrders() == 0) {
            System.out.println("No orders have been made.");
            return;
        }
        int orderID = askUserForOrderID();
        updateOrder(orderID);
    }

    /**
     * Update the details of a specific order from this app
     *
     * @param orderID the id of order to be updated
     */
    private void updateOrder(int orderID) {
        if (orderMgr.getOrder(orderID).isCompleted()) {
            System.out.println("Order " + orderID + " is complete and payment has been made.");
            return;
        }
        System.out.println("How would you like to update your order?\n" +
                "1. Add a menu item to the order\n" +
                "2. Remove an item from the order\n" +
                "3. Exit and return to the previous page");
        int choice;
        try {
            choice = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input received.");
            return;
        }
        switch (choice) {
            case 1:
                addToOrder(orderID);
                break;
            case 2:
                removeFromOrder(orderID);
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid input received.");
                break;
        }
    }

    /**
     * Add menu item to a specific order from this app
     *
     * @param orderID the id of order for menu item to be added
     */
    private void addToOrder(int orderID) {
        if (menuMgrApp.getNumberOfMenuItems() == 0) {
            System.out.println("No items are on the menu.");
            return;
        }
        menuMgrApp.printListOfMenuItems();
        System.out.println("Which item would you like to order?");
        orderMgr.addItemsInOrder(orderID, menuMgrApp.getMenuItem(askUserForMenuItemNo()));
        orderMgr.viewOrder(orderID);
    }

    /**
     * Remove menu item from a specific order in this app
     *
     * @param orderID the id of order for menu item to be removed
     */
    private void removeFromOrder(int orderID) {
        orderMgr.printItemsInOrder(orderID);
        if (orderMgr.getOrder(orderID).getNumberOfItemsOrdered() == 0) {
            System.out.println("No items ordered so far.");
            return;
        }
        System.out.println("Which item would you like to remove?");
        int itemNo;
        try {
            itemNo = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input received.");
            return;
        }
        if (itemNo < 0 || itemNo >= orderMgr.getOrder(orderID).getNumberOfItemsOrdered()) {
            System.out.println("Invalid input received.");
            return;
        }
        orderMgr.removeItemsInOrder(orderID, itemNo);
    }

    /**
     * Add invoice to Sales Report App
     *
     * @param reservationMgr The reservation manager which will be notified that an order has been completed.
     */
    private void billOrder(ReservationMgr reservationMgr) {
        if (orderMgr.getTotalNoOfOrders() == 0) {
            System.out.println("No orders have been made.");
            return;
        }
        salesReportApp.addInvoice(orderMgr.chargeBill(reservationMgr, askUserForOrderID()));
    }

    /**
     * Scanner to ask for user input(OrderID) with error checking
     */
    private int askUserForOrderID() {
        System.out.print("Enter an OrderID: ");
        int orderID;
        try {
            orderID = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input received.");
            return askUserForOrderID();
        }
        if (!orderMgr.validateOrderID(orderID)) {
            System.out.println("Invalid input received.");
            return askUserForOrderID();
        }
        return orderID;
    }

    /**
     * Scanner to ask for user input(MenuItemNo) with error checking
     */
    private int askUserForMenuItemNo() {
        System.out.print("Enter a menu item number: ");
        int menuItemNo;
        try {
            menuItemNo = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type received.");
            return askUserForMenuItemNo();
        }
        if (!menuMgrApp.validateMenuItemNo(menuItemNo)) {
            System.out.println("No such menu item.");
            return askUserForMenuItemNo();
        }
        return menuItemNo;
    }

    public void menuAppOptions() {
        menuApp.menuOptions();
    }

    public void staffAppOptions() {
        staffApp.options();
    }

}
