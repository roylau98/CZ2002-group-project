import java.io.Serializable;
import java.util.*;
/**
 * Manages the {@link AlaCarteItem} and {@link PromotionalSet} of the {@link MenuMgr}.
 * <p> 
 * This class provides printing of whole menu or individual MenuItem,
 * provide accessor (get methods) of individual MenuItem
 * and various methods to add,remove,update MenuItem in the menu.
 * <p>
 * @author 
 * 
 */

public class MenuMgr implements Serializable {

	private transient Scanner sc = new Scanner(System.in);
    /**
     * ArrayList of MenuItem which consists of AlaCarteItem and PromotionalSet, implemented in {@link ArrayList} data structure.
     * Each entry consists of a reference to existing {@link AlaCarteItem}/{@link PromotionalSet}object.
     */
	private ArrayList<MenuItem> listOfMenuItems;
    /**
     * Constructs an {@code Menu} object and
     * initialize the attributes {@code MenuItem} .
     */
	public MenuMgr() {
		listOfMenuItems = new ArrayList<MenuItem>();
	}

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
		System.out.println("====== MENU ======");
		System.out.println();
		printMenuItemsByCat(new AlaCarteItem());
		printMenuItemsByCat(new PromotionalSet());
		System.out.println();
	}
	/**
	 * Prints a certain type of AlaCarteItems in the Menu (Overload)
	 * @param alaCarteItemType	the type of the AlaCarte
	 */
	public void printMenuItemsByCat(AlaCarteItemType alaCarteItemType) {
		System.out.println("---"+ alaCarteItemType +"---");
		for (int i=0; i< listOfMenuItems.size(); i++) {
			try {
				if (((AlaCarteItem)listOfMenuItems.get(i)).getItemType() == alaCarteItemType) {
					System.out.println("Index No.   : "+i);
					listOfMenuItems.get(i).print();
					System.out.println();
				}
			}
			catch(ClassCastException e){}
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
				System.out.println("Index No.   : "+i);
				listOfMenuItems.get(i).print();
				System.out.println();
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
	/**
	 *  Function to create new AlaCarteItem
	 */
	public int createNewAlaCarteItem(String name, String description, double price, AlaCarteItemType type) {
		MenuItem newItem = new AlaCarteItem(name, description, price, type);
		listOfMenuItems.add(newItem);
		sortListOfMenuItems();
		return getIndexOfMenuItem(newItem);
	}
	/**
	 *  Function to create new PromotionalSet
	 */
	public int createNewPromoSetItem(String name, String description, double price) {
		MenuItem newItem = new PromotionalSet();
		newItem.updateContents(name,description,price);
		listOfMenuItems.add(newItem);
		sortListOfMenuItems();
		return getIndexOfMenuItem(newItem);
	}
	/**
	 *  Function to remove existing MenuItem
	 */
	public void removeMenuItem(int index) {
		listOfMenuItems.remove(index);
	}
	/**
	 * Update the details of existing items in Menu
	 */
	public void updateMenuItem(int index, String name, String description, double price) {
		MenuItem itemToBeUpdated = getMenuItem(index);
		itemToBeUpdated.updateContents(name, description, price);
		sortListOfMenuItems();
	}

	public void updateAlaCarteItemSpecificDetails(int index, AlaCarteItemType newType) {
		MenuItem item = getMenuItem(index);
		((AlaCarteItem)item).setItemType(newType);
	}
	public void addItemToPromoSetContent(int index,String name,int quantity) {
		MenuItem item = getMenuItem(index);
		((PromotionalSet)item).addItemToPromotionalSet(name,quantity);
	}
	public void updateItemToPromoSetContent(int index,String name,int quantity) {
		MenuItem item = getMenuItem(index);
		((PromotionalSet)item).updateItemInPromotionalSet(name,quantity);
	}

	public void removeItemToPromoSetContent(int index, String name) {
		MenuItem item = getMenuItem(index);
		((PromotionalSet)item).removeItemFromPromotionalSet(name);
	}

	/**
	 * Return a existing {@link MenuItem}object by using the INDEX(actual index plus 1) of ArrayList 
	 * 
	 * @param  indexNo   the INDEX(actual index plus 1) of alaCarte to be retrieved.
	 * @return {@link AlaCarteItem} object of given index.
	 */
	public MenuItem getMenuItem(int indexNo) {
		if (indexNo < 0 || indexNo >= listOfMenuItems.size()) {
			return null;
		}
		return listOfMenuItems.get(indexNo);
	}
	public int getIndexOfMenuItem(MenuItem item) {
		for (int i=0; i < listOfMenuItems.size(); i++) {
			if (listOfMenuItems.get(i).equals(item)) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * Return the index no. of a existing {@link MenuItem}object  
	 * 
	 * @param c	{@link AlaCarteItem} object to find its index.
	 * @return indexNo	the index no of MenuItem to be retrieved.
	 */
	public int searchIndexOfMenuItem(MenuItem c) {
		for (int i=0; i < listOfMenuItems.size(); i++) {
			if (listOfMenuItems.get(i).equals(c)) {
				return i;
			}
		}
		return -1;
	}
	public boolean validateMenuItemNo(int menuItemNo) {
		return menuItemNo >= 0 && menuItemNo < listOfMenuItems.size();
	}
	public int getNumberOfMenuItems() {
		return listOfMenuItems.size();
	}
	public AlaCarteItemType chooseAlaCarteItemType(int index) {
		switch (index) {
			case 1:
				return AlaCarteItemType.MAIN_COURSE;
			case 2:
				return AlaCarteItemType.APPETISER;
			case 3:
				return AlaCarteItemType.DRINKS;
			case 4:
				return AlaCarteItemType.DESSERT;
			default:
				return AlaCarteItemType.MAIN_COURSE;
		}
	}
}
