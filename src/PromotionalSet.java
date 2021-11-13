import java.util.HashMap;

/**
 * Stores information about a {@code PromotionalSet} inherit from{@link MenuItem} class to used in context of a menu.
 * This class stores the name, price, description of {@code PromotionalSet} in {@link MenuMgr},
 * and uses HashMap to keep track of the items in {@code PromotionalSet}.
 * it also provides various method to add/remove/update the content of the {@code PromotionalSet}.
 *
 * @see HashMap
 * @since 2021-11-12
 */
public class PromotionalSet extends MenuItem {
    /**
     * {@link HashMap} that is used to keep track of the items in {@code PromotionalSet}
     */
    private final HashMap<String, Integer> items;

    /**
     * Constructs a {@code PromotionalSet} with default name, price, description and type.
     */
    public PromotionalSet() {
        super("unknown", "unknown", 0);
        items = new HashMap<>();
    }

    /**
     * Constructs a {@code PromotionalSet} with specific name, price, description and type.
     *
     * @param name        name of the promotional set
     * @param description description of the promotional set
     * @param price       price of the promotional set
     */
    public PromotionalSet(String name, String description, double price) {
        super(name, description, price);
        this.items = new HashMap<>();
    }

    /**
     * Check if the item is existed
     *
     * @param key the name of the {@code PromotionalSet}
     * @return true if the {@code PromotionalSet} false otherwise
     */
    public Boolean checkIfItemExists(String key) {
        return items.containsKey(key);
    }

    /**
     * Add an existing item in Menu into {@code PromotionalSet}
     *
     * @param itemName name of the existing item in Menu to be added into {@code PromotionalSet}
     * @param quantity The quantity of that certain item to be added in
     */
    public void addItemToPromotionalSet(String itemName, int quantity) {
        this.items.putIfAbsent(itemName, quantity);
        System.out.println("Item Added!");
    }

    /**
     * Remove a certain item from {@code PromotionalSet}
     *
     * @param itemName name of the item  to be removed from {@code PromotionalSet}
     */
    public void removeItemFromPromotionalSet(String itemName) {
        if (items.containsKey(itemName)) {
            items.remove(itemName);
            System.out.println("Item Removed!");
        } else {
            System.out.println("No such item in Promotional Set");
        }
    }

    /**
     * Update the quantity of certain item in {@code PromotionalSet}
     *
     * @param itemName        name of the item to be updated in {@code PromotionalSet}
     * @param updatedQuantity the updated quantity of the certain item in {@code PromotionalSet}
     */
    public void updateItemInPromotionalSet(String itemName, int updatedQuantity) {
        if (items.containsKey(itemName)) {
            items.replace(itemName, updatedQuantity);
            System.out.println("Item Updated!");
        } else {
            System.out.println("No such item in Promotional Set");
        }
    }

    /**
     * Print the items and their quantity in this {@code PromotionalSet}
     */
    private void printPromotionalSetListOfItems() {
        System.out.println("-Contents-");
        items.forEach((key, value) -> System.out.println(key + " " + value));
    }

    /**
     * Print all the items in this {@code PromotionalSet} which overrides the
     * method from the abstract class {@link MenuItem}
     */
    public void print() {
        System.out.println("Name        : " + getName());
        System.out.println("Description : " + getDescription());
        System.out.printf("Price       : $%.2f\n", getPrice());
        printPromotionalSetListOfItems();
    }
}
