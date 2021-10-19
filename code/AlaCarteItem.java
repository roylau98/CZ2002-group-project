import java.util.Scanner;

public class AlaCarteItem extends MenuItem implements Comparable<AlaCarteItem> {

	private ItemType typeOfItem;

	AlaCarteItem() {
		super("unknown", "unknown", 0);
		typeOfItem = ItemType.MAIN_COURSE;
	}

	AlaCarteItem(String name, String description, double price, ItemType type) {
		super(name,description,price);
		typeOfItem = type;
	}

	public ItemType getItemType() {
		return typeOfItem;
	}

	@Override
	public int compareTo(AlaCarteItem ac) {
		return this.getItemType().compareTo(ac.getItemType());
	}

}
