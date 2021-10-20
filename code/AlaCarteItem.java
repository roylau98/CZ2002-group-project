import java.util.*;
public class AlaCarteItem extends MenuItem {

	private AlaCarteItemType typeOfItem;

	AlaCarteItem() {
		super("unknown", "unknown", 0);
		typeOfItem = AlaCarteItemType.MAIN_COURSE;
	}

	AlaCarteItem(String name, String description, double price, AlaCarteItemType type) {
		super(name,description,price);
		typeOfItem = type;
	}

	public AlaCarteItemType getItemType() {
		return typeOfItem;
	}

	public void setItemType(AlaCarteItemType updatedAlaCarteItemType) {
		this.typeOfItem = updatedAlaCarteItemType;
	}

	@Override
	public void updateContents() {
		super.updateContents();

		Scanner sc = new Scanner(System.in);
		int choice=-1;



		while (choice !=1 || choice !=0) {
			System.out.println("Update Item Type? 1-Yes, 0-N");
			choice = sc.nextInt();

			int i=0;
			for (AlaCarteItemType type : AlaCarteItemType.values()) {
				System.out.println(i+") "+type);
				i++;
			}

			System.out.println("Type option: ");
			choice = sc.nextInt();

			switch (choice) {
				case 1:
					setItemType(AlaCarteItemType.MAIN_COURSE);
					break;
				case 2:
					setItemType(AlaCarteItemType.APPERTIZER);
					break;
				case 3:
					setItemType(AlaCarteItemType.DRINKS);
					break;
				case 4:
					setItemType(AlaCarteItemType.DESSERT);
					break;
				default:
					System.out.println("Wrong input. Try again");
			}

		}

	}

}
