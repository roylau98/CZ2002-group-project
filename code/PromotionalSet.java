import java.util.HashMap;
import java.util.Scanner;
/**
 * Stores information about a PromotionalSet inherit from{@link MenuItem} class to used in context of a menu.
 * This class stores the name, price, description of PromotionalSet {@link Menu},
 * and uses HashMap to keep track of the items in PromotionalSet.
 * it also provide various method to add/remove/update the content of the PromotionalSet.
 *
 *@see         HashMap
 */
public class PromotionalSet extends MenuItem {
	
	/**
	 * HashMap that is used to keep track of the items in PromotionalSet
	 */
	private HashMap<String, Integer> items;
	
	/**
	 * Constructs a PromotionalSet with default name, price, description and type.
	 */
	PromotionalSet() {
		super("unknown", "unknown", 0);
	}
	
	/**
	 * Constructs a PromotionalSet with specific name, price, description and type.
	 */
	PromotionalSet(String name, String description, double price) {
		super(name, description, price);
		items = new HashMap<>();
	}
	
	/**
	 * Add an existing item in Menu into PromotionaLSet
	 *
	 * @param itemName	name of the existing item in Menu to be added into PromotionalSet
	 * @param quantity	The quantity of that certain item to be added in
	 */
	public void addItemToPromotionalSet(String itemName, int quantity) {
		items.putIfAbsent(itemName,quantity);
		System.out.println("item added!");
	}
	
	/**
	 * Remove a certain item from PromotionaLSet
	 *
	 * @param itemName	name of the item  to be removed from PromotionalSet
	 */
	public void removeItemFromPromotionalSet(String itemName) {
		if (items.containsKey(itemName)) {
			items.remove(itemName);
			System.out.println("item removed!");
		}
		else {
			System.out.println("No such item in promotional set");
		}
	}
	
	/**
	 * Update the quantity of certain item in PromotionaLSet
	 *
	 * @param itemName		name of the item to be updated in PromotionalSet
	 * @param updatedQuantity 	the updated quantity of the certain item in PromotionalSet
	 */
	public void updateItemInPromotionalSet(String itemName, int updatedQuantity) {
		if (items.containsKey(itemName)) {
			items.replace(itemName,updatedQuantity);
			System.out.println("item updated!");
		}
		else {
			System.out.println("No such item in promotional set");
		}
	}
	/**
	 * Print the items and their quantity in this PromotionalSet
	 */
	public void printPromotionalSetListOfItems() {
		items.entrySet().forEach(
				entry -> { System.out.println(entry.getKey() + " " + entry.getValue());
		});
	}
	/**
	 * Print all the items in this PromotionalSet which overrides the
	 * method from the abstract class {@link MenuItem}
	 */
	@Override
	public void print() {
		super.print();
		printPromotionalSetListOfItems();
	}
	/**
	 * A function to update the content(name,description,price,quantity) of this PromotionalSet which overrides the
	 * method from the abstract class {@link MenuItem}
	 *
	 */
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
						System.out.println("Wrong choice. Try again!");
				}
			}
		}
		else if (choice == 0) {
		}
		else {
			System.out.println("Wrong input, returning!");
		}
	}

}
