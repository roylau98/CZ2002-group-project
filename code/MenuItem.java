import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
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
		System.out.println("Name        : "+getName());
		System.out.println("Description : "+getDescription());
		System.out.println("Price       : $ "+getPrice());
	}
	
	/**
	 * A function to update the content(name,description,price) of this MenuItem
	 *
	 */
	public void updateContents(String name, String description, double price) {
		updateName(name);
		updateDescription(description);
		updatePrice(price);
	}

}
