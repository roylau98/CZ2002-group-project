import java.util.*;

public class MenuApp {
    private transient Scanner sc;
    private MenuMgr menuMgr;
    public MenuApp () {
        menuMgr = new MenuMgr();
        sc = new Scanner(System.in);
    }
    public void menuOptions() {
        sc = new Scanner(System.in);
        int choice = 999;
        do {
            System.out.println("Please select one of the options below:\n" +
                    "(1) Print Menu\n" +
                    "(2) Add Menu Item\n" +
                    "(3) Remove Menu Item\n" +
                    "(4) Update Menu Item\n" +
                    "(5) Exit");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input received.");
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
                default:
                    System.out.println("There is only 5 options.");
                    break;
            }
        } while (choice != 5);
    }
    public void printMenu() {
        menuMgr.printListOfMenuItems();
    }
    public void addMenuItem() {
        sc = new Scanner(System.in);
        int choice=0;
        do {
            System.out.println("Adding Menu Item......");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
            System.out.println();
            System.out.println("Please select the type of item to add:");
            System.out.println("1) Ala Carte");
            System.out.println("2) Promotion Package");
            System.out.println("3) Exit");
            System.out.println("----------------------------------------");
            try {
                choice = sc.nextInt();
            }
            catch (InputMismatchException e) {
                sc.nextLine();
            }
            String name;
            String description;
            double price;
            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the new AlaCarteItem");
                    name = askUserForMenuItemStringInput();
                    System.out.println("Enter the description of the new AlaCarteItem");
                    description = askUserForMenuItemStringInput();
                    System.out.println("Enter the Price of the new AlaCarteItem");
                    price = askUserForMenuItemPrice();
                    int inputForAlaCarteItemType = askUserForAlaCarteItemType();
                    menuMgr.createNewAlaCarteItem(name,description,price,menuMgr.chooseAlaCarteItemType(inputForAlaCarteItemType));
                    return;
                case 2:
                    System.out.println("Enter the name of the new PromotionalSet");
                    name = askUserForMenuItemStringInput();
                    System.out.println("Enter the description of the new PromotionalSet");
                    description = askUserForMenuItemStringInput();
                    System.out.println("Enter the Price of the new PromotionalSet");
                    price = askUserForMenuItemPrice();
                    menuMgr.createNewPromoSetItem(name,description,price);

                    return;
                default:
                    System.out.println("Invalid input. Try again!");
            }
        } while (choice !=3);
    }
    public void removeMenuItem() {
        if (menuMgr.getNumberOfMenuItems() == 0) {
            System.out.println("There is no menuItems to remove");
            return;
        }
        printMenu();
        System.out.println("Please enter the index of the menuItem to be removed");
        int indexOfItemToBeRemoved = askUserForMenuItemIndex();
        if (indexOfItemToBeRemoved == -1) {
            return;
        }
        menuMgr.removeMenuItem(indexOfItemToBeRemoved);
    }
    public void updateMenuItem() {
        if (menuMgr.getNumberOfMenuItems() == 0) {
            System.out.println("There is no menuItems to update");
            return;
        }
        printMenu();
        System.out.println("Enter the index of MenuItem to be updated: ");
        int indexOfMenuItemToBeUpdated = askUserForMenuItemIndex();
        if (indexOfMenuItemToBeUpdated == -1) {
            return;
        }
        menuMgr.updateMenuItem(indexOfMenuItemToBeUpdated);
    }

    private int askUserForMenuItemIndex() {
        sc = new Scanner(System.in);
        System.out.print("Enter an index or -1 to Quit: ");
        int index;
        try {
            index = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input received.");
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

    private String askUserForMenuItemStringInput() {
        sc = new Scanner(System.in);
        String inputForName;
        try {
            inputForName = sc.nextLine();
        }
        catch (InputMismatchException e) {
            sc.nextLine();
            return askUserForMenuItemStringInput();
        }
        return inputForName;
    }
    private double askUserForMenuItemPrice() {
        sc = new Scanner(System.in);
        double inputForPrice;
        try {
            inputForPrice = sc.nextDouble();
        }
        catch (InputMismatchException e) {
            sc.nextLine();
            return askUserForMenuItemPrice();
        }
        return inputForPrice;
    }

    private int askUserForAlaCarteItemType() {
        sc = new Scanner(System.in);
        int inputForAlaCarteItemType;
        System.out.println("Please select the type of Ala Carte:");
        System.out.println("1) Main Course");
        System.out.println("2) Appertizer");
        System.out.println("3) Drinks");
        System.out.println("4) Desserts");
        System.out.println("------------------------------------");
        System.out.print("Enter Your Option: ");
        try {
            inputForAlaCarteItemType =sc.nextInt();
            return inputForAlaCarteItemType;
        }
        catch (InputMismatchException e) {
            sc.nextLine();
            return askUserForAlaCarteItemType();
        }
    }
    private Boolean yesOrNo (String UpdateThis) {
        sc = new Scanner(System.in);
        int choice=999;
        while (choice != 1 || choice != 0) {
            System.out.println(UpdateThis);
            try {
                choice = sc.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Wrong input type. Try again!");
            }
        }
        if (choice == 1) {
            return true;
        }
        else {
            return false;
        }
    }

}
