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

}