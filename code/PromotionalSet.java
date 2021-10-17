import java.util.ArrayList;
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

	public void addToPromotionalSet(String itemName, int quantity) {
		items.put(itemName,quantity);
	}

	public void removeItemFromPromotionalSet(String itemName) {
		items.remove(itemName);
	}

	public HashMap<String, Integer> getPromotionalSet() {
		return items;
	}

	public void printPromotionalSet() {
		items.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " " + entry.getValue());
		});
	}

	@Override
	public void update(ArrayList<MenuItem> menuItems) {
		super.update();

		Scanner sc = new Scanner(System.in);
		int choice;
		String inputForString;
		int inputForInt;
		double inputForDouble;

		System.out.println("Update Promo contents? 1-Yes, 0-N");
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
						System.out.println();
						for (int i=0; i<menuItems.size(); i++) {
							System.out.println((i+1)+".) "+menuItems.get(i).getName());
						}
						System.out.println("Enter the index of the menu item to add:");
						choice = sc.nextInt()-1;
						System.out.println("Enter the quantity:");
						inputForInt = sc.nextInt();

						addToPromotionalSet(menuItems.get(choice-1).getName(),inputForInt);
						System.out.println("updated!");
						break;

					case 2:
						printPromotionalSet();
						System.out.println("Type the name of the item:");
						inputForString = sc.next();
						System.out.println("updated!");

						if (items.containsKey(inputForString)) {
							removeItemFromPromotionalSet(inputForString);
						}
						else {
						System.out.println("No such item in promotional set");
						}
						break;

					case 3:
						System.out.println("Type the name of the item:");
						inputForString = sc.next();
						if (items.containsKey(inputForString)) {
							inputForInt = sc.nextInt();
							items.replace(inputForString,inputForInt);
							System.out.println("updated!");
						}
						else {
							System.out.println("No such item in promotional set");
						}
						break;

					default:
						System.out.println("Wrong choice");

				}
			}


		}
		else if (choice == 0) {
		}
		else {
			System.out.println("Wrong input, returning");
			return;
		}

		System.out.println();

	}
}