import java.util.*;
/**
 * Stores information about a AlaCarteItem inherit from{@link MenuItem} class to used in context of a menu.
 * This class stores the name, price, description and type of AlaCarteItem {@link Menu},
 * inherit various method from abstract class {@link MenuItem} such as get(),update(),
 * 
 * @author Chua Zi Jian
 * 
 */
public class AlaCarteItem extends MenuItem {
	
	/**
	 * Enumeration type {@link AlaCarteItemType}used to indicate type of the AlaCarteItem
	 */
	private AlaCarteItemType typeOfItem;
	
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
	@Override
	public void updateContents() {
		super.updateContents();

		Scanner sc = new Scanner(System.in);
		int choice=-1;



		while (choice !=1 || choice !=0) {
			System.out.println("Update Item Type? 1-Yes, 0-N");
			choice = sc.nextInt();

			int i=0;
			for (AlaCarteItemType type : AlaCarteItemType.values()) {
				System.out.println(i+") "+type);
				i++;
			}

			System.out.println("Type option: ");
			choice = sc.nextInt();

			switch (choice) {
				case 1:
					setItemType(AlaCarteItemType.MAIN_COURSE);
					break;
				case 2:
					setItemType(AlaCarteItemType.APPERTIZER);
					break;
				case 3:
					setItemType(AlaCarteItemType.DRINKS);
					break;
				case 4:
					setItemType(AlaCarteItemType.DESSERT);
					break;
				default:
					System.out.println("Wrong input. Try again");
			}

		}

	}

}
