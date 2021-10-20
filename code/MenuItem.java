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

	public void print() {
		System.out.println("Name: "+getName());
		System.out.println("Description: "+getDescription());
		System.out.println("Price: "+getPrice());
	}

	public void updateContents() {
		Scanner sc = new Scanner(System.in);
		int choice=-1;
		String inputForString;
		double inputForDouble;

		while (choice !=1 || choice !=0) {
			System.out.println("Update Item Name? 1-Yes, 0-N");
			choice = sc.nextInt();
			if (choice == 1) {
				inputForString = sc.next();
				updateName(inputForString);
				break;
			}
			else if (choice == 0) {
				break;
			}
			else {
				System.out.println("Wrong input. Try again");
			}
		}

		while (choice !=1 || choice !=0) {
			System.out.println("Update Item Description? 1-Yes, 0-N");
			choice = sc.nextInt();
			if (choice == 1) {
				inputForString = sc.next();
				updateDescription(inputForString);
				break;
			}
			else if (choice == 0) {
				break;
			}
			else {
				System.out.println("Wrong input. Try again");
			}
		}

		while (choice !=1 || choice !=0) {
			System.out.println("Update Item Price? 1-Yes, 0-N");
			choice = sc.nextInt();
			if (choice == 1) {
				inputForDouble = sc.nextDouble();
				updatePrice(inputForDouble);
				break;
			}
			else if (choice == 0) {
				break;
			}
			else {
				System.out.println("Wrong input. Try again");
			}
		}


	}

}