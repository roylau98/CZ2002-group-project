/**
 * Stores information about a AlaCarteItem inherit from{@link MenuItem} class to used in context of a menu.
 * This class stores the name, price, description and type of AlaCarteItem {@link MenuMgr},
 * inherit various method from abstract class {@link MenuItem} such as get(),update(),
 *
 * @since 2021-11-5
 */
public class AlaCarteItem extends MenuItem {
    /**
     * Enumeration type {@link AlaCarteItemType}used to indicate type of the AlaCarteItem
     */
    private AlaCarteItemType typeOfItem;

    /**
     * Constructs an item with default name, price, description and type.
     */
    public AlaCarteItem() {
        super("unknown", "unknown", 0);
        typeOfItem = AlaCarteItemType.MAIN_COURSE;
    }

    /**
     * Constructs an item with specified name, price, description and type.
     *
     * @param name        name of this item.
     * @param price       price of this item.
     * @param description description of this item.
     * @param type        type of this item
     */
    public AlaCarteItem(String name, String description, double price, AlaCarteItemType type) {
        super(name, description, price);
        typeOfItem = type;
    }

    /**
     * Return the type of this AlaCarteItem.
     *
     * @return typeOfItem    The enumeration type of this AlaCarteItem object
     */
    public AlaCarteItemType getItemType() {
        return typeOfItem;
    }

    /**
     * Update the type of this AlaCarteItem.
     *
     * @param updatedAlaCarteItemType the type of this item to be updated
     */
    public void setItemType(AlaCarteItemType updatedAlaCarteItemType) {
        this.typeOfItem = updatedAlaCarteItemType;
    }

    /**
     * Print all the items in this AlaCarteItem which overrides the
     * method from the abstract class {@link MenuItem}
     */
    public void print() {
        System.out.println("Name        : " + getName());
        System.out.println("Description : " + getDescription());
        System.out.printf("Price       : $%.2f\n", getPrice());
    }
}
