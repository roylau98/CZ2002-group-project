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
 * @since 2021-11-12
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
    private void sortListOfMenuItems() {
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
        printMenuItemsByCat(AlaCarteItem.class);
        printMenuItemsByCat(PromotionalSet.class);

    }

    /**
     * Prints a certain type of AlaCarteItems in the Menu (Overload)
     *
     * @param alaCarteItemType the type of the AlaCarte
     */
    private void printMenuItemsByCat(AlaCarteItemType alaCarteItemType) {
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
     * @param c class of object
     */
    private void printMenuItemsByCat(Class c) {
        if (c == PromotionalSet.class) {
            System.out.println("---PromotionalSets---");
            for (int i = 0; i < listOfMenuItems.size(); i++) {
                if (listOfMenuItems.get(i) instanceof PromotionalSet) {
                    System.out.println("Index No.   : " + i);
                    listOfMenuItems.get(i).print();
                    System.out.println();
                }
            }
        } else if (c == AlaCarteItem.class) {
            Arrays.asList(AlaCarteItemType.values()).forEach(
                    alaCarteItemType -> printMenuItemsByCat(alaCarteItemType)
            );
        } else {
        }

    }

    /**
     * Return true/false of the existence of certain MenuItem
     *
     * @param name name of menu item to be checked whether it is inside the menu
     * @return the existence(boolean) of certain MenuItem
     */
    public Boolean isMenuItemNameExist(String name) {
        for (MenuItem curr : listOfMenuItems) {
            if (curr.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true/false of the existence of certain MenuItem
     *
     * @param description description of menu item to be checked whether it is inside the menu
     * @return the existence(boolean) of certain MenuItem
     */
    public Boolean isMenuItemDescriptionExist(String description) {
        for (MenuItem curr : listOfMenuItems) {
            if (curr.getDescription().equals(description)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true/false of the existence of certain Promotional set
     *
     * @param index index of the promotional item
     * @param key   name of promotional item
     * @return the existence(boolean) of certain MenuItem
     */
    public Boolean isPromoSetContentItemExist(int index, String key) {
        MenuItem curr = getMenuItem(index);
        if (curr instanceof PromotionalSet) {
            return ((PromotionalSet) curr).checkIfItemExists(key);
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
        return listOfMenuItems.indexOf(newItem);
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
        MenuItem newItem = new PromotionalSet(name, description, price);
        listOfMenuItems.add(newItem);
        sortListOfMenuItems();
        return listOfMenuItems.indexOf(newItem);
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
     * @param index index of the menu item to be updated
     * @param name  updated name
     */
    public void updateMenuItemName(int index, String name) {
        MenuItem itemToBeUpdated = getMenuItem(index);
        itemToBeUpdated.setName(name);
        sortListOfMenuItems();
    }

    /**
     * Update the details of existing items in Menu
     *
     * @param index       index of the menu item to be updated
     * @param description updated description
     */
    public void updateMenuItemDescription(int index, String description) {
        MenuItem itemToBeUpdated = getMenuItem(index);
        itemToBeUpdated.setDescription(description);
        sortListOfMenuItems();
    }

    /**
     * Update the details of existing items in Menu
     *
     * @param index index of the menu item to be updated
     * @param price updated price
     */
    public void updateMenuItemPrice(int index, double price) {
        MenuItem itemToBeUpdated = getMenuItem(index);
        itemToBeUpdated.setPrice(price);
        sortListOfMenuItems();
    }

    /**
     * Update the enumeration type of existing items in Menu
     *
     * @param index   index of the menu item to be updated
     * @param newType updated enumeration type
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
     * @param	index	the choice of the AlaCarte type
     * @return 			the enumeration type of AlaCarteItem
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
