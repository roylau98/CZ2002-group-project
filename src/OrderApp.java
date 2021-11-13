import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interface of the Order App which has the option of create/remove etc. {@link Order}
 * <p>
 *
 * @since 2021-11-5
 */

public class OrderApp implements Serializable, AppInterface {
    /**
     * Object Manager of {@link Order}
     */
    private final OrderMgr orderMgr;
    /**
     * {@link SalesReport} application
     */
    private final SalesReport salesReportApp;
    /**
     * Object Manager of {@link MenuItem}
     */
    private final MenuMgr menuMgr;
    /**
     * {@link Staff} application
     */
    private final StaffApp staffApp;
    /**
     * Object Manager of {@link Reservation}
     */
    private final ReservationMgr reservationMgr;
    /**
     * Scanner for the user input
     */
    private transient Scanner sc;

    /**
     * Class Constructor
     *
     * @param reservationMgrEx {@link ReservationMgr} manager of the app
     * @param orderMgrEx       {@link OrderMgr} manager of the app
     * @param menuMgrEx        {@link MenuMgr} manager of the app
     * @param salesReport      {@link SalesReport} application
     * @param staffAppEx       {@link StaffApp}
     */
    public OrderApp(ReservationMgr reservationMgrEx, OrderMgr orderMgrEx, MenuMgr menuMgrEx, SalesReport salesReport, StaffApp staffAppEx) {
        orderMgr = orderMgrEx;
        staffApp = staffAppEx;
        salesReportApp = salesReport;
        reservationMgr = reservationMgrEx;
        menuMgr = menuMgrEx;
    }

    /**
     * Open Order App options
     */
    public void openOptions() {
        sc = new Scanner(System.in);
        if (staffApp.getNoOfStaff() == 0) {
            System.out.print("\nOrder App\n");
            System.out.println("No staff. Orders cannot be made");
            System.out.println("Returning back to main menu...");
            return;
        }
        if (menuMgr.getNumberOfMenuItems() == 0) {
            System.out.print("Order App\n");
            System.out.println("No menu items. Orders cannot be made");
            System.out.println("Returning back to main menu...");
            return;
        }

        int choice = 0;
        do {
            System.out.print("\nOrder App\n" +
                    "Please select one of the options below:\n" +
                    "1. View existing order\n" +
                    "2. View all orders\n" +
                    "3. Make a new order\n" +
                    "4. Cancel an existing order\n" +
                    "5. Update an existing order\n" +
                    "6. Make payment for an order\n" +
                    "7. Exit this application and return to the previous page\n" +
                    "Enter your choice: ");
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
            }
            switch (choice) {
                case 1:
                    viewOrder();
                    break;
                case 2:
                    printAll();
                    break;
                case 3:
                    newOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    updateOrder();
                    break;
                case 6:
                    billOrder();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid input received.");
                    break;
            }
        } while (choice != 7);
    }

    /**
     * View the specific {@link Order} that has been created
     */
    public void printAll() {
        if (orderMgr.getTotalNoOfOrders() == 0) {
            System.out.println("There is no order at the moment.");
            return;
        }

        orderMgr.printAllOrders();
    }

    /**
     * Views all existing {@link Order}
     */
    private void viewOrder() {
        if (orderMgr.getTotalNoOfOrders() == 0) {
            System.out.println("No orders have been made.");
            return;
        }
        orderMgr.printAllOrderID();
        orderMgr.viewOrder(askUserForOrderID());
    }

    /**
     * Create a new {@link Order}
     */
    private void newOrder() {
        sc = new Scanner(System.in);
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
        if (orderMgr.checkforTableOrder(tableNo) == true) {
            System.out.println("The order for this table has been created.\n" + "If you intend to update contents of order, please proceed to Option 4");
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
     * Remove a specific {@link Order} from this app
     */
    private void removeOrder() {
        if (orderMgr.getTotalNoOfOrders() == 0) {
            System.out.println("No orders have been made.");
            return;
        }
        orderMgr.printAllOrders();
        orderMgr.removeOrder(askUserForOrderID());
    }

    /**
     * Update a specific {@link Order} from this app
     */
    private void updateOrder() {
        if (orderMgr.getTotalNoOfOrders() == 0) {
            System.out.println("No orders have been made.");
            return;
        }
        orderMgr.printAllOrders();
        int orderID = askUserForOrderID();
        updateOrder(orderID);
    }

    /**
     * Update the details of a specific {@link Order} from this app
     *
     * @param orderID the id of {@link Order} to be updated
     */
    private void updateOrder(int orderID) {
        sc = new Scanner(System.in);
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
                addMenuItemToOrder(orderID);
                break;
            case 2:
                removeMenuItemFromOrder(orderID);
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid input received.");
                break;
        }
    }

    /**
     * Add {@link MenuItem} to a specific {@link Order} from this app
     *
     * @param orderID the id of order for {@link MenuItem} to be added
     */
    private void addMenuItemToOrder(int orderID) {
        if (menuMgr.getNumberOfMenuItems() == 0) {
            System.out.println("No items are on the menu.");
            return;
        }
        menuMgr.printListOfMenuItems();
        System.out.println("Which item would you like to order?");
        orderMgr.addItemsInOrder(orderID, menuMgr.getMenuItem(askUserForMenuItemNo()));
        orderMgr.viewOrder(orderID);
    }

    /**
     * Remove {@link MenuItem} from a specific {@link Order} in this app
     *
     * @param orderID the id of {@link Order} for {@link MenuItem} to be removed
     */
    private void removeMenuItemFromOrder(int orderID) {
        sc = new Scanner(System.in);
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
     * Add {@link Invoice} to {@link SalesReport} application
     */
    private void billOrder() {
        int id;
        if (orderMgr.getTotalNoOfOrders() == 0) {
            System.out.println("No orders have been made.");
            return;
        }
        orderMgr.printAllOrderID();
        id = askUserForOrderID();
        salesReportApp.addInvoice(orderMgr.chargeBill(reservationMgr, id));
    }

    /**
     * Scanner to ask for user input(OrderID) with error checking
     *
     * @return orderID        order ID
     */
    private int askUserForOrderID() {
        sc = new Scanner(System.in);
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
     *
     * @return menuItemNo    the index no. of the {@link MenuItem}
     */
    private int askUserForMenuItemNo() {
        sc = new Scanner(System.in);
        System.out.print("Enter a menu item number: ");
        int menuItemNo;
        try {
            menuItemNo = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type received.");
            return askUserForMenuItemNo();
        }
        if (!menuMgr.validateMenuItemNo(menuItemNo)) {
            System.out.println("No such menu item.");
            return askUserForMenuItemNo();
        }
        return menuItemNo;
    }

    /**
     * Open Staff Option
     */
    public void staffAppOptions() {
        staffApp.openOptions();
    }

}

