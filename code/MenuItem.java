import java.util.Scanner;

public abstract class MenuItem {

	private String name;
	private String description;
	private double price;

	public MenuItem(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public void updatePrice(double price) {
		this.price = price;
	}
	public void updateName(String name) {
		this.name = name;
	}
	public void updateDescription(String description) {
		this.description = description;
	}

	public void updateMenuItem() {
		Scanner sc = new Scanner(System.in);
		int choice;
		String inputForString;
		double inputForDouble;

		System.out.println("Update Item Name? 1-Yes, 0-N");
		choice = sc.nextInt();
		if (choice == 1) {
			inputForString = sc.next();
			updateName(inputForString);
		}
		else if (choice == 0) {
		}
		else {
			System.out.println("Wrong input, returning");
			return;
		}

		System.out.println("Update Item Description? 1-Yes, 0-N");
		choice = sc.nextInt();
		if (choice == 1) {
			inputForString = sc.next();
			updateDescription(inputForString);
		}
		else if (choice == 0) {
		}
		else {
			System.out.println("Wrong input, returning");
			return;
		}

		System.out.println("Update Item Price? 1-Yes, 0-N");
		choice = sc.nextInt();
		if (choice == 1) {
			inputForDouble = sc.nextDouble();
			updatePrice(inputForDouble);
		}
		else if (choice == 0) {
		}
		else {
			System.out.println("Wrong input, returning");
			return;
		}
	};

}