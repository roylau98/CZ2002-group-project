import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Manages the {@link AlaCarteItem} and {@link PromotionalSet} of the {@link MenuMgr}.
 * <p>
 * This class provides printing of whole menu or individual MenuItem,
 * provide accessor (get methods) of individual MenuItem
 * and various methods to add,remove,update MenuItem in the menu.
 * <p>
 *
 * @since 2021-11-5
 */

public class MenuMgr implements Serializable {
    /**
     * ArrayList of MenuItem which consists of AlaCarteItem and PromotionalSet, implemented in {@link ArrayList} data structure.
     * Each entry consists of a reference to existing {@link AlaCarteItem}/{@link PromotionalSet}object.
     */
    private final ArrayList<MenuItem> listOfMenuItems;

    /**
     * Constructs an {@code Menu} object and
     * initialize the attributes {@code MenuItem} .
     */
    public MenuMgr() {
        this.listOfMenuItems = new ArrayList<>();
    }

    /**
     * Sorts all the items in the MenuItem ArrayList in order
     */
    public void sortListOfMenuItems() {
        // sort by class type
        listOfMenuItems.sort(Comparator.comparing(o -> o.getClass().getName()));

        ArrayList<AlaCarteItem> listOfAlaCarteItem = new ArrayList<>();
        for (MenuItem menuItem : listOfMenuItems) {
            if (menuItem instanceof AlaCarteItem) {
                listOfAlaCarteItem.add(((AlaCarteItem) menuItem));
            }
        }

        listOfMenuItems.removeIf(element -> element instanceof AlaCarteItem);

        // sort AlaCarteItem by AlaCarteItemType
        listOfAlaCarteItem.sort(Comparator.comparing(AlaCarteItem::getItemType));
        listOfMenuItems.addAll(listOfAlaCarteItem);
    }

    /**
     * Prints all the items in the Menu according to its order
     */
    public void printListOfMenuItems() {
        System.out.println("====== MENU ======");
        System.out.println();
        printMenuItemsByCat(new AlaCarteItem());
        printMenuItemsByCat(new PromotionalSet());

    }

    /**
     * Prints a certain type of AlaCarteItems in the Menu (Overload)
     *
     * @param alaCarteItemType the type of the AlaCarte
     */
    public void printMenuItemsByCat(AlaCarteItemType alaCarteItemType) {
        System.out.println("---" + alaCarteItemType + "---");
        for (int i = 0; i < listOfMenuItems.size(); i++) {
            try {
                if (((AlaCarteItem) listOfMenuItems.get(i)).getItemType() == alaCarteItemType) {
                    System.out.println("Index No.   : " + i);
                    listOfMenuItems.get(i).print();
                    System.out.println();
                }
            } catch (ClassCastException e) {
            }
        }
    }

    /**
     * Prints PromotionalSet in the Menu (Overload)
     *
     * @param item an empty variable used to initiate the function
     */
    public void printMenuItemsByCat(PromotionalSet item) {
        System.out.println("---PromotionalSets---");
        for (int i = 0; i < listOfMenuItems.size(); i++) {
            if (listOfMenuItems.get(i) instanceof PromotionalSet) {
                System.out.println("Index No.   : " + i);
                listOfMenuItems.get(i).print();
                System.out.println();
            }
        }
    }

    /**
     * Prints AlaCarteItem in the Menu (Overload)
     *
     * @param item an empty variable used to initiate the function
     */
    public void printMenuItemsByCat(AlaCarteItem item) {
        Arrays.asList(AlaCarteItemType.values()).forEach(
                alaCarteItemType -> printMenuItemsByCat(alaCarteItemType)
        );
    }

    /**
     * Return true/false of the existence of certain MenuItem
     *
     * @param menuItem menu item to be checked whether it is inside the menu
     * @return the existence(boolean) of certain MenuItem
     */
    public Boolean isMenuItemExist(MenuItem menuItem) {
        for (MenuItem curr : listOfMenuItems) {
            if (curr.getName().equals(menuItem.getName()) && curr.getDescription().equals(menuItem.getDescription())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function to create new AlaCarteItem
     *
     * @param name        name of new AlaCarteItem
     * @param description description of new AlaCarteItem
     * @param price       price of new AlaCarteItem
     * @param type        the enumeration type of new AlaCarteItem
     * @return the index of this new AlaCarteItem
     */
    public int createNewAlaCarteItem(String name, String description, double price, AlaCarteItemType type) {
        MenuItem newItem = new AlaCarteItem(name, description, price, type);
        listOfMenuItems.add(newItem);
        sortListOfMenuItems();
        return getIndexOfMenuItem(newItem);
    }

    /**
     * Function to create new PromotionalSet
     *
     * @param name        name of new PromotionalSet
     * @param description description of new PromotionalSet
     * @param price       price of new PromotionalSet
     * @return the index of this new PromotionalSet
     */
    public int createNewPromoSetItem(String name, String description, double price) {
        MenuItem newItem = new PromotionalSet();
        newItem.updateContents(name, description, price);
        listOfMenuItems.add(newItem);
        sortListOfMenuItems();
        return getIndexOfMenuItem(newItem);
    }

    /**
     * Function to remove existing MenuItem
     *
     * @param index index of menu item to be removed
     */
    public void removeMenuItem(int index) {
        listOfMenuItems.remove(index);
    }

    /**
     * Update the details of existing items in Menu
     *
     * @param index       index of the menu item to be updated
     * @param name        updated name
     * @param description updated description
     * @param price       updated price
     */
    public void updateMenuItem(int index, String name, String description, double price) {
        MenuItem itemToBeUpdated = getMenuItem(index);
        itemToBeUpdated.updateContents(name, description, price);
        sortListOfMenuItems();
    }

    /**
     * Update the enumeration type of existing items in Menu
     *
     * @param index index of the menu item to be updated
     * @param newType  updated enumeration type
     */
    public void updateAlaCarteItemSpecificDetails(int index, AlaCarteItemType newType) {
        MenuItem item = getMenuItem(index);
        ((AlaCarteItem) item).setItemType(newType);
    }

    /**
     * Function to add a PromoSetContent
     *
     * @param index    index of the menu item to be added to PromoSet
     * @param name     name of new PromoSetContent
     * @param quantity quantity of the certain menu item
     */
    public void addItemToPromoSetContent(int index, String name, int quantity) {
        MenuItem item = getMenuItem(index);
        ((PromotionalSet) item).addItemToPromotionalSet(name, quantity);
    }

    /**
     * Function to update a PromoSetContent
     *
     * @param index    index of the menu item to be updated to PromoSet
     * @param name     name of new PromoSetContent
     * @param quantity quantity of the certain menu item
     */
    public void updateItemToPromoSetContent(int index, String name, int quantity) {
        MenuItem item = getMenuItem(index);
        ((PromotionalSet) item).updateItemInPromotionalSet(name, quantity);
    }

    /**
     * Function to remove a PromoSetContent
     *
     * @param index index of the menu item to be removed in PromoSet
     * @param name  name of PromoSetContent
     */
    public void removeItemToPromoSetContent(int index, String name) {
        MenuItem item = getMenuItem(index);
        ((PromotionalSet) item).removeItemFromPromotionalSet(name);
    }

    /**
     * Return an existing {@link MenuItem}object by using the INDEX(actual index plus 1) of ArrayList
     *
     * @param indexNo the index of menu item to be retrieved.
     * @return {@link MenuItem} object of given index.
     */
    public MenuItem getMenuItem(int indexNo) {
        if (indexNo < 0 || indexNo >= listOfMenuItems.size()) {
            return null;
        }
        return listOfMenuItems.get(indexNo);
    }

    /**
     * Return the index no. of existing {@link MenuItem}object
     *
     * @param item {@link MenuItem} object of given index.
     * @return the index of menu item to be retrieved.
     */
    public int getIndexOfMenuItem(MenuItem item) {
        for (int i = 0; i < listOfMenuItems.size(); i++) {
            if (listOfMenuItems.get(i).equals(item)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Return the index no. of an existing {@link MenuItem}object
     *
     * @param c {@link AlaCarteItem} object to find its index.
     * @return indexNo    the index no of MenuItem to be retrieved.
     */
    public int searchIndexOfMenuItem(MenuItem c) {
        for (int i = 0; i < listOfMenuItems.size(); i++) {
            if (listOfMenuItems.get(i).equals(c)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Return the true if it is a valid menu item no.
     *
     * @param menuItemNo index of the menu item
     * @return true if it is a valid menu item no., false otherwise
     */
    public boolean validateMenuItemNo(int menuItemNo) {
        return menuItemNo >= 0 && menuItemNo < listOfMenuItems.size();
    }

    /**
     * Return the size of menu
     *
     * @return size of the menu
     */
    public int getNumberOfMenuItems() {
        return listOfMenuItems.size();
    }

    /**
     * Function for choosing enumeration type of AlaCarteItem
     *
     * @return the enumeration type of AlaCarteItem
     */
    public AlaCarteItemType chooseAlaCarteItemType(int index) {
        switch (index) {
            case 2:
                return AlaCarteItemType.APPETISER;
            case 3:
                return AlaCarteItemType.DRINKS;
            case 4:
                return AlaCarteItemType.DESSERT;
            default:
                return AlaCarteItemType.MAIN_COURSE;
        }
    }
}
