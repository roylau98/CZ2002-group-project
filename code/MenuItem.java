public abstract class MenuItem {

	private String name;
	private String description;
	private double price;

	/**
	 * 
	 * @param name
	 * @param description
	 * @param price
	 */
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

}