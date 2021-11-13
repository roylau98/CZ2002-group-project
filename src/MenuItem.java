import java.io.Serializable;

/**
 * An abstract class for {@link AlaCarteItem} and {@link PromotionalSet} used to store information about an item
 *
 * @since 2021-11-5
 */
public abstract class MenuItem implements Serializable {
    /**
     * Name of item
     */
    private String name;
    /**
     * Description of item
     */
    private String description;
    /**
     * Price of item
     */
    private double price;

    /**
     * Class Constructor
     *
     * @param name        name of this item.
     * @param price       price of this item.
     * @param description description of this item.
     */
    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Return price of this {@code MenuItem}
     *
     * @return price of the {@code MenuItem}
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Update price of this {@code MenuItem}
     *
     * @param price updated price of this item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Return name of this {@code MenuItem}
     *
     * @return name of the {@code MenuItem}
     */
    public String getName() {
        return this.name;
    }

    /**
     * Update name of this {@code MenuItem}
     *
     * @param name updated name of the {@code MenuItem}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return description of this {@code MenuItem}
     *
     * @return description of the {@code MenuItem}
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Update description of this {@code MenuItem}
     *
     * @param description updated description of the {@code MenuItem}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Print the details(name,description,price) of the {@code MenuItem}
     */
    public abstract void print();

}
