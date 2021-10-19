import java.util.HashMap;
import java.util.Scanner;

public class PromotionalSet extends MenuItem {

	private HashMap<String, Integer> items;

	PromotionalSet() {
		super("unknown", "unknown", 0);
	}

	PromotionalSet(String name, String description, double price) {
		super(name, description, price);
		items = new HashMap<>();
	}

	public void addItemToPromotionalSet(String itemName, int quantity) {
		items.putIfAbsent(itemName,quantity);
		System.out.println("item added!");
	}

	public void removeItemFromPromotionalSet(String itemName) {
		if (items.containsKey(itemName)) {
			items.remove(itemName);
			System.out.println("item removed!");
		}
		else {
			System.out.println("No such item in promotional set");
		}
	}

	public void updateItemInPromotionalSet(String itemName, int updatedQuantity) {
		if (items.containsKey(itemName)) {
			items.replace(itemName,updatedQuantity);
			System.out.println("item updated!");
		}
		else {
			System.out.println("No such item in promotional set");
		}
	}

	public void printPromotionalSetListOfItems() {
		items.entrySet().forEach(
				entry -> { System.out.println(entry.getKey() + " " + entry.getValue());
		});
	}

	@Override
	public void print() {
		super.print();
		printPromotionalSetListOfItems();
	}

	@Override
	public void updateContents() {
		super.updateContents();

		Scanner sc = new Scanner(System.in);
		int choice;
		String inputForString;
		int inputForInt;
		double inputForDouble;

		System.out.println("Update Set contents? 1-Yes, 0-N");
		choice = sc.nextInt();
		if (choice == 1) {
			System.out.println("1.) Add promotional Item");
			System.out.println("2.) Remove promotional Item");
			System.out.println("3.) Change promotional Item quantity");
			System.out.println("4.) Quit");

			choice = sc.nextInt();
			while(choice != 4) {
				switch (choice) {
					case 1:
						System.out.println("Enter the name of the item to add:");
						inputForString = sc.next();
						System.out.println("Enter the quantity:");
						inputForInt = sc.nextInt();
						addItemToPromotionalSet(inputForString,inputForInt);
						System.out.println("updated!");
						break;

					case 2:
						printPromotionalSetListOfItems();
						System.out.println("Type the name of the item:");
						inputForString = sc.next();
						removeItemFromPromotionalSet(inputForString);
						break;

					case 3:
						System.out.println("Type the name of the item:");
						inputForString = sc.next();
						System.out.println("Type the updated quantity:");
						inputForInt = sc.nextInt();
						updateItemInPromotionalSet(inputForString,inputForInt);
						break;

					default:
						System.out.println("Wrong choice");
				}
			}
		}
		else if (choice == 0) {
		}
		else {
			System.out.println("Wrong input, returning!");
			return;
		}
	}

}