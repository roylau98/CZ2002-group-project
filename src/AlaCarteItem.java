import java.util.Scanner;
/**
 * Stores information about a AlaCarteItem inherit from{@link MenuItem} class to used in context of a menu.
 * This class stores the name, price, description and type of AlaCarteItem {@link MenuMgr},
 * inherit various method from abstract class {@link MenuItem} such as get(),update(),
 * 
 * @author 
 * @since 2021-10-22
 * 
 */
public class AlaCarteItem extends MenuItem {
	
	/**
	 * Enumeration type {@link AlaCarteItemType}used to indicate type of the AlaCarteItem
	 */
	private AlaCarteItemType typeOfItem;
	private transient Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructs a item with default name, price, description and type.
	 */
	AlaCarteItem() {
		super("unknown", "unknown", 0);
		typeOfItem = AlaCarteItemType.MAIN_COURSE;
	}
	
	/**
	 * Constructs a item with specified name, price, description and type.
	 * @param name          name of this item.
	 * @param price         price of this item.
	 * @param description   description of this item.
	 * @param type   	type of this item
	 */
	AlaCarteItem(String name, String description, double price, AlaCarteItemType type) {
		super(name,description,price);
		typeOfItem = type;
	}
	
	/**
	 * Return the type of this AlaCarteItem.
	 */
	public AlaCarteItemType getItemType() {
		return typeOfItem;
	}
	/**
	 * Update the type of this AlaCarteItem.
	 * @param updatedAlaCarteItemType   	the type of this item to be updated
	 *
	 */
	public void setItemType(AlaCarteItemType updatedAlaCarteItemType) {
		this.typeOfItem = updatedAlaCarteItemType;
	}
	/**
	 * A function to update the content(name,description,price and type) of this AlaCarteItem which overrides the
	 * method from the abstract class {@link MenuItem}
	 *
	 */
	public void updateContents(String name, String description, double price, AlaCarteItemType type) {
		super.updateContents(name,description,price);
		setItemType(type);
	}

}
