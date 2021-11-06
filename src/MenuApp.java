import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interface of the Menu App which has the option of add/remove etc. MenuItem
 * <p>
 *
 * @since 2021-11-5
 */
public class MenuApp implements Serializable {
    /**
     * Object Manager of the menu
     */
    private final MenuMgr menuMgr;
    private transient Scanner sc;

    /**
     * Constructs an {@code MenuApp} object with known menuMgr
     */
    public MenuApp() {
        menuMgr = new MenuMgr();
        sc = new Scanner(System.in);
    }

    /**
     * Interface of the MenuApp with several options available
     */
    public void menuOptions() {
        sc = new Scanner(System.in);
        int choice = 999;
        while (choice != 5) {
            System.out.print("\nMenu App\n" +
                    "Please select one of the options below:\n" +
                    "1. View menu\n" +
                    "2. Add a menu item\n" +
                    "3. Remove a menu item\n" +
                    "4. Update a menu item\n" +
                    "5. Exit this application and return to the previous page\n" +
                    "Enter your choice: ");
            try {
                sc = new Scanner(System.in);
                choice = sc.nextInt();
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }

            switch (choice) {
                case 1:
                    printMenu();
                    break;
                case 2:
                    addMenuItem();
                    break;
                case 3:
                    removeMenuItem();
                    break;
                case 4:
                    updateMenuItem();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("There is only 5 options.");

            }
        }

    }

    /**
     * Print the latest Menu in the restaurant
     */
    public void printMenu() {
        menuMgr.printListOfMenuItems();
    }

    /**
     * Add new {@code MenuItem} object into the Menu
     */
    public void addMenuItem() {
        sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("What type of menu item would you like to add?");
            System.out.println("1. Ala Carte");
            System.out.println("2. Promotion Package");
            System.out.println("3. Exit");
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
            }
            String name;
            String description;
            double price;
            int index;
            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the new AlaCarteItem: ");
                    name = askUserForMenuItemStringInput();
                    System.out.print("Enter the description of the new AlaCarteItem: ");
                    description = askUserForMenuItemStringInput();
                    System.out.print("Enter the Price of the new AlaCarteItem: ");
                    price = askUserForMenuItemPrice();
                    int inputForAlaCarteItemType = askUserForAlaCarteItemType();
                    index = menuMgr.createNewAlaCarteItem(name, description, price, menuMgr.chooseAlaCarteItemType(inputForAlaCarteItemType));
                    System.out.println("Index: " + index);
                    return;
                case 2:
                    System.out.print("Enter the name of the new PromotionalSet: ");
                    name = askUserForMenuItemStringInput();
                    System.out.print("Enter the description of the new PromotionalSet: ");
                    description = askUserForMenuItemStringInput();
                    System.out.print("Enter the Price of the new PromotionalSet: ");
                    price = askUserForMenuItemPrice();
                    index = menuMgr.createNewPromoSetItem(name, description, price);
                    updatePromoSetContents(index);
                    System.out.println("Index: " + index);
                    return;
                default:
                    System.out.println("Invalid input. Try again!");
            }
        } while (choice != 3);
    }

    /**
     * Remove {@code MenuItem} object from the Menu
     */
    public void removeMenuItem() {
        if (menuMgr.getNumberOfMenuItems() == 0) {
            System.out.println("There are no menu items.");
            return;
        }
        printMenu();
        System.out.print("Enter the index of the menu item you would like to remove: ");
        int indexOfItemToBeRemoved = askUserForMenuItemIndex();
        if (indexOfItemToBeRemoved == -1) {
            return;
        }
        menuMgr.removeMenuItem(indexOfItemToBeRemoved);
    }

    /**
     * Update the details of existing {@code MenuItem} in the Menu
     */
    public void updateMenuItem() {
        String name;
        String description;
        double price;
        if (menuMgr.getNumberOfMenuItems() == 0) {
            System.out.println("There are no menu items.");
            return;
        }
        printMenu();
        System.out.print("Enter the index of the menu item you would like to update: ");
        int indexOfMenuItemToBeUpdated = askUserForMenuItemIndex();
        if (indexOfMenuItemToBeUpdated == -1) {
            return;
        }
        if (yesOrNo("Update Name Of MenuItem?")) {
            name = askUserForMenuItemStringInput();
        } else {
            name = menuMgr.getMenuItem(indexOfMenuItemToBeUpdated).getName();
        }
        if (yesOrNo("Update Description Of MenuItem?")) {
            description = askUserForMenuItemStringInput();
        } else {
            description = menuMgr.getMenuItem(indexOfMenuItemToBeUpdated).getName();
        }
        if (yesOrNo("Update Price Of MenuItem?")) {
            price = askUserForMenuItemPrice();
        } else {
            price = menuMgr.getMenuItem(indexOfMenuItemToBeUpdated).getPrice();
        }
        menuMgr.updateMenuItem(indexOfMenuItemToBeUpdated, name, description, price);

        if (menuMgr.getMenuItem(indexOfMenuItemToBeUpdated) instanceof AlaCarteItem) {
            if (yesOrNo("Update AlaCarteItemType?")) {
                menuMgr.updateAlaCarteItemSpecificDetails(indexOfMenuItemToBeUpdated, menuMgr.chooseAlaCarteItemType(askUserForAlaCarteItemType()));
            }
        }
        if (menuMgr.getMenuItem(indexOfMenuItemToBeUpdated) instanceof PromotionalSet) {
            updatePromoSetContents(indexOfMenuItemToBeUpdated);
        }
    }

    /**
     * Add new {@code PromotionalSets} object into the Menu
     */
    private void updatePromoSetContents(int indexOfMenuItemToBeUpdated) {
        String stringInput;
        int intInput;
        PromotionalSet temp = new PromotionalSet();
        if (menuMgr.getMenuItem(indexOfMenuItemToBeUpdated) instanceof PromotionalSet) {
            int choice;
            while (true) {
                choice = askUserForChoiceInUpdatingPromoSetContents();
                menuMgr.printMenuItemsByCat(temp);
                switch (choice) {
                    case 1:
                        System.out.print("Enter the name of the promotional item to be added: ");
                        stringInput = askUserForMenuItemStringInput();
                        System.out.print("Enter the quantity of the promotional item to be added: ");
                        intInput = askUserForQuantity();
                        menuMgr.addItemToPromoSetContent(indexOfMenuItemToBeUpdated, stringInput, intInput);
                        break;
                    case 2:
                        System.out.print("Enter the name of the promotional item to be removed: ");
                        stringInput = askUserForMenuItemStringInput();
                        menuMgr.removeItemToPromoSetContent(indexOfMenuItemToBeUpdated, stringInput);
                        break;
                    case 3:
                        System.out.print("Enter the name of the promotional item to be updated: ");
                        stringInput = askUserForMenuItemStringInput();
                        System.out.print("Enter the quantity of the promotional item to be updated: ");
                        intInput = askUserForQuantity();
                        menuMgr.updateItemToPromoSetContent(indexOfMenuItemToBeUpdated, stringInput, intInput);
                        break;
                    case 4:
                        return;
                }
            }
        }
    }

    /**
     * Scanner to ask for user input(MenuItemIndex) with error checking
     */
    private int askUserForMenuItemIndex() {
        sc = new Scanner(System.in);
        System.out.print("Enter an index or -1 to Quit: ");
        int index;
        try {
            index = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type received.");
            return askUserForMenuItemIndex();
        }
        if (index == -1) {
            return -1;
        }
        if (!menuMgr.validateMenuItemNo(index)) {
            System.out.println("Invalid input received.");
            return askUserForMenuItemIndex();
        }
        return index;
    }

    /**
     * Scanner to ask for user input(MenuItemStringInput) with error checking
     */
    private String askUserForMenuItemStringInput() {
        sc = new Scanner(System.in);
        String inputForName;
        System.out.println("Enter your string input:");
        try {
            inputForName = sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input type. Try again!");
            return askUserForMenuItemStringInput();
        }
        return inputForName;
    }

    /**
     * Scanner to ask for user input(MenuItemPrice) with error checking
     */
    private double askUserForMenuItemPrice() {
        sc = new Scanner(System.in);
        double inputForPrice;

        while (true) {
            try {
                System.out.println("Enter your price input:");
                inputForPrice = sc.nextDouble();
                if (inputForPrice <= 0) {
                    System.out.println("Invalid input type. Try again!");
                } else
                    break;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input type. Try again!");
                return askUserForMenuItemPrice();
            }
        }
        return inputForPrice;
    }

    /**
     * Scanner to ask for user input(Quantity) with error checking
     */
    private int askUserForQuantity() {
        sc = new Scanner(System.in);
        int input;
        System.out.println("Enter your input quantity:");
        try {
            input = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input type. Try again!");
            return askUserForQuantity();
        }
        return input;
    }

    /**
     * Scanner to ask for user input(AlaCarteItemType) with error checking
     */
    private int askUserForAlaCarteItemType() {
        sc = new Scanner(System.in);
        int inputForAlaCarteItemType;
        while (true) {
            System.out.println("Please select the type of Ala Carte:");
            System.out.println("1) Main Course");
            System.out.println("2) Appetiser");
            System.out.println("3) Drinks");
            System.out.println("4) Desserts");
            System.out.println("------------------------------------");
            System.out.print("Enter Your Option: ");
            try {
                inputForAlaCarteItemType = sc.nextInt();
                if (inputForAlaCarteItemType <= 0) {
                    System.out.println("Invalid input type. Try again!");
                } else
                    return inputForAlaCarteItemType;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input type. Try again!");
                return askUserForAlaCarteItemType();
            }
        }
    }

    /**
     * Scanner to ask for user input(Boolean) with error checking
     */
    private Boolean yesOrNo(String UpdateThis) {
        sc = new Scanner(System.in);
        int choice = 999;

        System.out.println(UpdateThis + " 1-Yes, 0-No");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Try again!");
        }
        if (choice == 1) {
            return true;
        } else if (choice == 0) {
            return false;
        } else {
            return yesOrNo(UpdateThis);
        }
    }

    /**
     * Scanner to ask for user input for UpdatingPromoSetContents() with error checking
     */
    private int askUserForChoiceInUpdatingPromoSetContents() {
        sc = new Scanner(System.in);
        int choice = 999;
        System.out.println("(1) Add promotional Item");
        System.out.println("(2) Remove promotional Item");
        System.out.println("(3) Change promotional Item quantity");
        System.out.println("(4) Quit");
        System.out.println("------------------------------------");
        System.out.print("Enter Your Option: ");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Try again!");
        }
        if (choice < 1 || choice > 4) {
            System.out.println("There are only 4 options. Try again!");
            return askUserForChoiceInUpdatingPromoSetContents();
        }
        return choice;

    }

    /**
     * Return Menu Manager of the MenuApp
     *
     * @return menuMgr the object manager of menu item
     */
    public MenuMgr getMenuMgr() {
        return menuMgr;
    }

}
