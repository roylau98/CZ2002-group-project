import java.io.Serializable;

/**
 * Enumeration to indicate the type of {@link AlaCarteItem}
 *
 * @since 2021-11-5
 */
public enum AlaCarteItemType implements Serializable {
    /**
     * Main course type of {@link AlaCarteItem}.
     */
    MAIN_COURSE,
    /**
     * Appetizer type of {@link AlaCarteItem}.
     */
    APPETISER,
    /**
     * Drinks type of {@link AlaCarteItem}.
     */
    DRINKS,
    /**
     * Dessert type of {@link AlaCarteItem}.
     */
    DESSERT
}
