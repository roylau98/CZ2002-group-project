import java.io.Serializable;
import java.util.Scanner;
import java.io.*;
/**
 * An abstract class for {@link AlaCarteItem} and {@link PromotionalSet} used to store information about an item 
 *
 * @author 
 */
public abstract class MenuItem implements Serializable {
	
	/**
	 * Name of item
	 */
	private String name;
	
	/**
	 * Description of item
	 */
	
	private String description;
	/**
	 * Price of item
	 */
	private double price;

	private transient Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructs a item with specified name, price, description.
	 * @param name          name of this item.
	 * @param price         price of this item.
	 * @param description   description of this item.
	 * 
	 */
	public MenuItem(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	/**
	 * Return price of this MenuItem
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Return name of this MenuItem
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Return description of this MenuItem
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Update price of this MenuItem
	 *
	 *@param price         updated price of this item.
	 */
	public void updatePrice(double price) {
		this.price = price;
	}
	
	/**
	 * Update name of this MenuItem
	 *
	 *@param name         updated name of this item.
	 */
	public void updateName(String name) {
		this.name = name;
	}
	
	/**
	 * Update description of this MenuItem
	 *
	 *@param description         updated description of this item.
	 */
	public void updateDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Print the details(name,description,price) of this MenuItem
	 */
	public void print() {
		System.out.println("Name: "+getName());
		System.out.println("Description: "+getDescription());
		System.out.println("Price: "+getPrice());
	}
	
	/**
	 * A function to update the content(name,description,price) of this MenuItem
	 *
	 */
	public void updateContents() {
		sc = new Scanner(System.in);
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

		while (choice !=0) {
			System.out.println("Update Item Price? 1-Yes, 0-N");
			choice = sc.nextInt();
			if (choice == 1) {
				inputForDouble = sc.nextDouble();
				updatePrice(inputForDouble);
				try {
					inputForDouble = sc.nextDouble();
					if (inputForDouble<=0)
						throw new Exception("Error: price must not lower or equal to 0!" );
					updatePrice(inputForDouble);
					break;
				}
				catch (Exception e) 
				{
					System.out.println( e.getMessage() );
				}
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
