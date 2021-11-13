import java.io.Serializable;

/**
 * Enumeration to indicate the type of {@link AlaCarteItem}
 *
 * @since 2021-11-5
 */
public enum AlaCarteItemType implements Serializable {
    /**
     * Main course type of AlaCarte item
     */
    MAIN_COURSE,
    /**
     * Appetizer type of AlaCarte item
     */
    APPETISER,
    /**
     * Drinks type of AlaCarte item
     */
    DRINKS,
    /**
     * Dessert type of AlaCarte item
     */
    DESSERT
}
