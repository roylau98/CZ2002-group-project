import java.util.*;

/**
 * Manages the {@link AlaCarteItem} and {@link PromotionalSet} of the {@link Menu}. 
 * <p> 
 * This class provides printing of whole menu or individual MenuItem,
 * provide accessor (get methods) of individual MenuItem
 * and various methods to add,remove,update MenuItem in the menu.
 * <p>
 * @author Chua Zi Jian
 * 
 */

public class Menu {

	Scanner sc = new Scanner(System.in);

    /**
     * ArrayList of MenuItem which consists of AlaCarteItem and PromotionalSet, implemented in {@link ArrayList} data structure.
     * Each entry consists of a reference to existing {@link AlaCarteItem}/{@link PromotionalSet}object.
     */
	private ArrayList<MenuItem> listOfMenuItems;


	
    /**
     * Constructs an {@code Menu} object and
     * initialize the attributes {@code MenuItem} .
     */
	public Menu() {
		listOfMenuItems = new ArrayList<MenuItem>();
	}
	
    /**
     * A function used to do 
     */
	public void menuOptions() {
		System.out.println("What do you want to do?");
		System.out.println("1) print Menu");
		System.out.println("2) choose menu item");
		System.out.println("3) add menu item");
		System.out.println("4) remove menu item");
		System.out.println("5) update menu item");
		System.out.println("6) Exit");
		int choice = 0;
		choice = sc.nextInt();
		while (choice != 6 )
		switch (choice) {
			case 1:
				printListOfMenuItems();
				break;
			case 2:
				System.out.println("Type index of item");
				int indexNo = sc.nextInt();
				getMenuItem(indexNo-1);
			case 3:
				addMenuItem();
				break;
			case 4:
				removeMenuItem();
				break;
			case 5:
				updateMenuItem();
				break;

			case 6:
				System.out.println("Exited!");
				break;

			default:
				System.out.println("Invalid option");
		}

	}

	//-------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * Sorts all the items in the MenuItem ArrayList in order
	 */
	public void sortListOfMenuItems() {
		// sort by class type
		Collections.sort(listOfMenuItems,(o1, o2) -> o1.getClass().getName().compareTo(o2.getClass().getName()));


		ArrayList<AlaCarteItem> listOfAlaCarteItem = new ArrayList<>();
		for (int i=0; i<listOfMenuItems.size(); i++) {
			if ( listOfMenuItems.get(i) instanceof AlaCarteItem) {
				listOfAlaCarteItem.add(((AlaCarteItem)listOfMenuItems.get(i)));
			}
		}

		listOfMenuItems.removeIf(element -> element instanceof AlaCarteItem);

		// sort AlaCarteItem by AlaCarteItemType
		Collections.sort(listOfAlaCarteItem, (o1,o2) -> o1.getItemType().compareTo(o2.getItemType()));
		listOfMenuItems.addAll(listOfAlaCarteItem);

	}
	/**
	 * Prints all the items in the Menu according to its order
	 */
	public void printListOfMenuItems() {
		System.out.println("MENU ITEMS");
		printMenuItemsByCat(new AlaCarteItem());
		printMenuItemsByCat(new PromotionalSet());
	}
	
	/**
	 * Prints a certain type of AlaCarteItems in the Menu (Overload)
	 * @param alaCarteItemType	the type of the AlaCarte
	 */
	public void printMenuItemsByCat(AlaCarteItemType alaCarteItemType) {
		System.out.println("---"+ alaCarteItemType +"---");
		for (int i=0; i< listOfMenuItems.size(); i++) {
			if (((AlaCarteItem)listOfMenuItems.get(i)).getItemType() == alaCarteItemType) {
				System.out.println("Index: "+(i+1));
				listOfMenuItems.get(i).print();
			}
		}
	}
	/**
	 * Prints PromotionalSet in the Menu (Overload)
	 * @param item	an empty variable used to initiate the function
	 */
	public void printMenuItemsByCat(PromotionalSet item) {
		System.out.println("---PromotionalSets---");
		for (int i=0; i< listOfMenuItems.size(); i++) {
			if (listOfMenuItems.get(i) instanceof PromotionalSet) {
				System.out.println("Index: "+(i+1));
				listOfMenuItems.get(i).print();
			}
		}
	}
	
	public void printMenuItemsByCat(AlaCarteItem item) {
		Arrays.asList(AlaCarteItemType.values()).forEach(
				alaCarteItemType -> printMenuItemsByCat(alaCarteItemType)
		);
	}
	
	/**
	 * Return true/false of the existence of certain MenuItem
	 *
	 * @param menuItem	menu item to be checked whether it is inside the menu
	 * @return true/false	the existence(boolean) of certain MenuItem
	 */
	public Boolean isMenuItemExist(MenuItem menuItem) {
		for (int i=0; i < listOfMenuItems.size(); i++) {
			MenuItem curr = listOfMenuItems.get(i);
			if (curr.getName() == menuItem.getName()) {
				if (curr.getDescription() == menuItem.getDescription()) {
					return true;
				}
			}
		}
		return false;
	}

	//-----------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * A Do-While loop to add new items of AlaCarteItem and add new promotion to PromotionalSet with existing AlaCarteItems 
	 * 
	 */
	public void addMenuItem() {
		
		int choice=0;
		String name;
		String description;
		double price;

		AlaCarteItemType type = null;
		
		do {
			System.out.println("Please select the type of item to add:");
			System.out.println("1) Ala Carte");
			System.out.println("2) Promotion Package");
			System.out.println("3) Exit");
			
			choice=sc.nextInt();
			
				switch(choice) {
				
					case 1:
						System.out.println("Please select :");
						System.out.println("1) Main Course");
						System.out.println("2) Appertizer");
						System.out.println("3) Drinks");
						System.out.println("4) Desserts");
						int c =sc.nextInt();
						switch(c) {
							case 1:
								type= AlaCarteItemType.MAIN_COURSE;
								break;
							case 2:
								type= AlaCarteItemType.APPERTIZER;
								break;
							case 3:
								type= AlaCarteItemType.DRINKS;
								break;
							case 4:
								type= AlaCarteItemType.DESSERT;
								break;
						}
						sc.nextLine(); //buffer
						
						
						System.out.println("Enter the new name :");
						name=sc.nextLine();
						
						System.out.println("Enter the new price:");
						price = sc.nextDouble();
						
						//buffer
						sc.nextLine();
						System.out.println("Enter the new description:");
						description = sc.nextLine();
						
						AlaCarteItem temp = new AlaCarteItem(name,description,price,type);

						if (isMenuItemExist(temp) == true) {
							System.out.println("Item already exists");
						}
						else {
							listOfMenuItems.add(temp);
							sortListOfMenuItems();
							System.out.println("Added!");
						}
						break;
						
					case 2:
						PromotionalSet tempP = new PromotionalSet();
						tempP.updateContents();
						listOfMenuItems.add(tempP);
						System.out.println("Added!");
						break;
						
					case 3:
						System.out.println("Exited!");
						break;
						
					default:
						System.out.println("Wrong Option!!!!!");
				
				}
			}while(choice!=3);
			
		}
	//----------------------------------------------------------------------------------------------------------
	
	
	/**
	 *  Function to remove existing MenuItems
	 * 
	 */
	public void removeMenuItem() {
		
		int choice=0;
		String name;
		int indexNo;

		printListOfMenuItems();
		System.out.println("Please type index of menu item to remove:");
		indexNo = sc.nextInt();
		if (indexNo > listOfMenuItems.size()) {
			System.out.println("No such item");
		}
		else {
			listOfMenuItems.remove(indexNo-1);
			sortListOfMenuItems();
			System.out.println("item removed");
		}

	}
	//---------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * Update the details(name,price,description) of existing items in Menu
	 * 
	 */
	public void updateMenuItem() {

		int indexNo;
		System.out.println("Please type index of item to be updated:");
		indexNo = sc.nextInt();

		if (indexNo > listOfMenuItems.size()) {
			System.out.println("No such item in Menu");
		}
		else {
			listOfMenuItems.get(indexNo-1).updateContents();
			sortListOfMenuItems();
		}

	}
	
	//---------------------------------------------------------------------------------------------
	
	
	/**
	 * Return a existing {@link MenuItem}object by using the INDEX(actual index plus 1) of ArrayList 
	 * 
	 * @param  indexNo   the INDEX(actual index plus 1) of alaCarte to be retrieved.
         * @return {@link AlaCarteItem} object of given index.
	 */
	public MenuItem getMenuItem(int indexNo) {
		return listOfMenuItems.get(indexNo-1);
	}
	
	/**
	 * Return the index no. of a existing {@link MenuItem}object  
	 * 
	 * @param c		{@link AlaCarteItem} object to find its index.
         * @return indexNo	the index no of MenuItem to be retrieved.
	 */
	public int searchMenuItemIndex(MenuItem c) {
		for (int i=0; i < listOfMenuItems.size(); i++) {
			if (listOfMenuItems.get(i).equals(c)) {
				return i;
			}
		}
		return -1;
	}

}

