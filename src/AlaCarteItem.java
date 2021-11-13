/**
 * Stores information about an {@code AlaCarteItem} to be used in context of a menu such as its name, price, description and
 * the type of {@code AlaCarteItem} it is.
 * Inherits various methods from the abstract class {@link MenuItem}.
 *
 * @since 2021-11-5
 */
public class AlaCarteItem extends MenuItem {
    /**
     * Enumeration type {@link AlaCarteItemType} used to indicate type of the {@code AlaCarteItem}.
     */
    private AlaCarteItemType typeOfItem;

    /**
     * Class constructor.
     */
    public AlaCarteItem() {
        super("unknown", "unknown", 0);
        typeOfItem = AlaCarteItemType.MAIN_COURSE;
    }

    /**
     * Class constructor.
     *
     * @param name        name of the item
     * @param price       price of the item
     * @param description description of the item
     * @param type        type of the item
     */
    public AlaCarteItem(String name, String description, double price, AlaCarteItemType type) {
        super(name, description, price);
        typeOfItem = type;
    }

    /**
     * Gets the type of this {@code AlaCarteItem}.
     *
     * @return The enumeration type of this {@link AlaCarteItem} object
     */
    public AlaCarteItemType getItemType() {
        return typeOfItem;
    }

    /**
     * Sets the type of this {@code AlaCarteItem}.
     *
     * @param updatedAlaCarteItemType the type of this item to be updated
     */
    public void setItemType(AlaCarteItemType updatedAlaCarteItemType) {
        this.typeOfItem = updatedAlaCarteItemType;
    }

    /**
     * Print all the items in this {@code AlaCarteItem} which overrides the
     * method from the abstract class {@link MenuItem}
     */
    public void print() {
        System.out.println("Name        : " + getName());
        System.out.println("Description : " + getDescription());
        System.out.printf("Price       : $%.2f\n", getPrice());
    }
}
