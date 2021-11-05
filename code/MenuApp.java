import java.util.*;
import java.io.Serializable;

public class MenuApp implements Serializable{
    private transient Scanner sc;
    private MenuMgr menuMgr;
    public MenuApp (MenuMgr menuMgr) {
        
    	this.menuMgr=menuMgr;
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
            int index;
            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the new AlaCarteItem");
                    name = askUserForMenuItemStringInput();
                    System.out.println("Enter the description of the new AlaCarteItem");
                    description = askUserForMenuItemStringInput();
                    System.out.println("Enter the Price of the new AlaCarteItem");
                    price = askUserForMenuItemPrice();
                    int inputForAlaCarteItemType = askUserForAlaCarteItemType();
                    index = menuMgr.createNewAlaCarteItem(name,description,price,menuMgr.chooseAlaCarteItemType(inputForAlaCarteItemType));
                    System.out.println("Index: "+index);
                    return;
                case 2:
                    System.out.println("Enter the name of the new PromotionalSet");
                    name = askUserForMenuItemStringInput();
                    System.out.println("Enter the description of the new PromotionalSet");
                    description = askUserForMenuItemStringInput();
                    System.out.println("Enter the Price of the new PromotionalSet");
                    price = askUserForMenuItemPrice();
                    index = menuMgr.createNewPromoSetItem(name,description,price);
                    updatePromoSetContents(index);
                    System.out.println("Index: "+index);
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
        String name;
        String description;
        double price;
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
        if (yesOrNo("Update Name Of MenuItem?")) {
            name = askUserForMenuItemStringInput();
        }
        else {
            name = menuMgr.getMenuItem(indexOfMenuItemToBeUpdated).getName();
        }
        if (yesOrNo("Update Description Of MenuItem?")) {
            description = askUserForMenuItemStringInput();
        }
        else {
            description = menuMgr.getMenuItem(indexOfMenuItemToBeUpdated).getName();
        }
        if (yesOrNo("Update Price Of MenuItem?")) {
            price = askUserForMenuItemPrice();
        }
        else {
            price = menuMgr.getMenuItem(indexOfMenuItemToBeUpdated).getPrice();
        }
        menuMgr.updateMenuItem(indexOfMenuItemToBeUpdated,name,description,price);

        if (menuMgr.getMenuItem(indexOfMenuItemToBeUpdated) instanceof AlaCarteItem) {
            if(yesOrNo("Update AlaCarteItemType?")) {
               menuMgr.updateAlaCarteItemSpecificDetails(indexOfMenuItemToBeUpdated,menuMgr.chooseAlaCarteItemType(askUserForAlaCarteItemType()));
            }
        }
        if (menuMgr.getMenuItem(indexOfMenuItemToBeUpdated) instanceof PromotionalSet) {
            updatePromoSetContents(indexOfMenuItemToBeUpdated);
        }
    }

    private void updatePromoSetContents(int indexOfMenuItemToBeUpdated) {
        String stringInput;
        int intInput;
        if (menuMgr.getMenuItem(indexOfMenuItemToBeUpdated) instanceof PromotionalSet) {
            int choice;
            do {
                choice = askUserForChoiceInUpdatingPromoSetContents();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the name of the promotional item to be added: ");
                        stringInput = askUserForMenuItemStringInput();
                        System.out.println("Enter the quantity of the promotional item to be added: ");
                        intInput = askUserForQuantity();
                        menuMgr.addItemToPromoSetContent(indexOfMenuItemToBeUpdated,stringInput,intInput);
                        break;
                    case 2:
                        System.out.println("Enter the name of the promotional item to be removed: ");
                        stringInput = askUserForMenuItemStringInput();
                        menuMgr.removeItemToPromoSetContent(indexOfMenuItemToBeUpdated, stringInput);
                        break;
                    case 3:
                        System.out.println("Enter the name of the promotional item to be updated: ");
                        stringInput = askUserForMenuItemStringInput();
                        System.out.println("Enter the quantity of the promotional item to be updated: ");
                        intInput = askUserForQuantity();
                        menuMgr.updateItemToPromoSetContent(indexOfMenuItemToBeUpdated,stringInput,intInput);
                        break;
                    case 4:
                        return;
                }
            } while (choice!=4);
        }
    }

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

    private String askUserForMenuItemStringInput() {
        sc = new Scanner(System.in);
        String inputForName;
        System.out.println("Enter your string input:");
        try {
            inputForName = sc.nextLine();
        }
        catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input type. Try again!");
            return askUserForMenuItemStringInput();
        }
        return inputForName;
    }
    private double askUserForMenuItemPrice() {
        sc = new Scanner(System.in);
        double inputForPrice;
        System.out.println("Enter your price input:");
        try {
            inputForPrice = sc.nextDouble();
        }
        catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input type. Try again!");
            return askUserForMenuItemPrice();
        }
        return inputForPrice;
    }
    private int askUserForQuantity() {
        sc = new Scanner(System.in);
        int input;
        System.out.println("Enter your input quantity:");
        try {
            input = sc.nextInt();
        }
        catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input type. Try again!");
            return askUserForQuantity();
        }
        return input;
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
            System.out.println("Invalid input type. Try again!");
            return askUserForAlaCarteItemType();
        }
    }
    private Boolean yesOrNo (String UpdateThis) {
        sc = new Scanner(System.in);
        int choice=999;

        System.out.println(UpdateThis+" 1-Yes, 0-No");
        try {
            choice = sc.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input type. Try again!");
        }
        if (choice == 1) {
            return true;
        }
        else if (choice == 0) {
            return false;
        }
        else {
            return yesOrNo(UpdateThis);
        }
    }

    private int askUserForChoiceInUpdatingPromoSetContents() {
        sc = new Scanner(System.in);
        int choice=999;
        System.out.println("(1) Add promotional Item");
        System.out.println("(2) Remove promotional Item");
        System.out.println("(3) Change promotional Item quantity");
        System.out.println("(4) Quit");
        System.out.println("------------------------------------");
        System.out.print("Enter Your Option: ");
        try {
            choice = sc.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input type. Try again!");
        }
        if (choice < 1 || choice > 4) {
            System.out.println("There are only 4 options. Try again!");
            return askUserForChoiceInUpdatingPromoSetContents();
        }
        return choice;

    }

}
