import java.util.HashMap;

public class PromotionalSet extends MenuItem {

	private HashMap<AlaCarteItem, Integer> items;

	PromotionalSet() {
		super("unknown", "unknown", 0);
	}

	PromotionalSet(String name, String description, double price) {
		super(name, description, price);
		items = new HashMap<AlaCarteItem,Integer>();
	}

	public void addToPromotionalSet(AlaCarteItem item, int quantity) {
		items.put(item,quantity);
	}

	public void removeItemFromPromotionalSet(AlaCarteItem item) {
		items.remove(item);
	}

	public HashMap<AlaCarteItem, Integer> getPromotionalSet() {
		return items;
	}

	public void printPromotionalSet() {
		items.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " " + entry.getValue());
		});
	}

}