import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;

public class Menu {
	
	Scanner sc = new Scanner(System.in);

	private List<AlaCarteItem> listOfAlaCartesItems;
	private ArrayList<PromotionalSet> listOfPromoSetItems;


	public Menu(){

		listOfAlaCartesItems = new ArrayList<AlaCarteItem>();
		listOfPromoSetItems = new ArrayList<PromotionalSet>();
		
	}

	public void menuOptions() {

	}

	//-------------------------------------------------------------------------------------------------------------
	public void sortListOfAlaCarteItem(List<AlaCarteItem> listOfAlaCartesItems) {
		Collections.sort(listOfAlaCartesItems);
	}
	public void printAlaCarteItems() {

		printMenuItemsWithSelectedItemType(ItemType.MAIN_COURSE);
		printMenuItemsWithSelectedItemType(ItemType.APPERTIZER);
		printMenuItemsWithSelectedItemType(ItemType.DRINKS);
		printMenuItemsWithSelectedItemType(ItemType.DESSERT);
		
	}
	public void printMenuItemsWithSelectedItemType(ItemType selectedItemType) {
		System.out.println("--------------"+selectedItemType+"---------------");
		for (int i = 0; i < listOfAlaCartesItems.size(); i++)
		{
			if(listOfAlaCartesItems.get(i).getItemType() == selectedItemType)
			{
				System.out.println(i+1+")");
				System.out.println();
				System.out.println("Name       : "+ listOfAlaCartesItems.get(i).getName());
				System.out.println("Price      : "+ listOfAlaCartesItems.get(i).getPrice());
				System.out.println("Description: "+ listOfAlaCartesItems.get(i).getDescription());
				System.out.println();
			}
		}
		System.out.println("----------------------------------------");
		}
		
	//-----------------------------------------------------------------------------------------------------------------
	public void addMenuItem() {
		
		int choice;
		String name;
		String description;
		double price;
		AlaCarteItem temp;
		Itemtype type;
		PromotionalSet temp1;
		
		do {
			System.out.println("Please select the type of item to add:");
			System.out.println("1) Ala Carte");
			System.out.println("2) Promotion Package");
			System.out.println("3) Exit");
			
			choice=sc.nextInt();
			
				switch(choice) {
				
					case 1:
						System.out.println("Please select :");
						System.out.println();
						System.out.println("1) Main Course");
						System.out.println("2) Appertizer");
						System.out.println("3) Drinks");
						System.out.println("4) Desserts");
						int c =sc.nextInt();
						switch(c) {
							case 1:
								type=ItemType.MAIN_COURSE;
								break;
							case 2:
								type=ItemType.APPERTIZER;
								break;
							case 3:
								type=ItemType.DRINKS;
								break;
							case 4:
								type=ItemType.DESSERT;
								break;
						}
						
						
						System.out.println("Enter the new name :");
						name = sc.nextLine();
						
						System.out.println("Enter the new price:");
						price = sc.nextDouble();
						
						System.out.println("Enter the new description:");
						description = sc.nextLine();
						
						temp= new AlaCarte(name,description,price,type);
						listOfAlaCartesItems.add(temp);
						break;
						
					case 2:
						
						temp1=new PromotionalSet();
						temp1.update(food);
						listOfPromoSetItems.add(temp1);
						break;
						
					case 3:
						Collections.sort(listOfAlaCartesItems);
						break;
						
					default:
						System.out.println("Wrong Option!!!!!");
				
				}
			}while(choice!=3);
			
		}
	//----------------------------------------------------------------------------------------------------------
	public void removeItem() {
		
		int choice;
		String inputForString;

		System.out.println("Please type the name of the menu item to be removed:");
		inputForString = sc.next();

		for (int i=0; i < listOfAlaCartesItems.size(); i++) {
			if (listOfAlaCartesItems.get(i).getName() == inputForString) {
				System.out.println("Found!");
				System.out.println(listOfAlaCartesItems.get(i).getName());
				System.out.println(listOfAlaCartesItems.get(i).getName());
				System.out.println("Confirm delete? 1-Yes, 0-No");
				while (choice != 0 || choice !=1) {
					choice = sc.nextInt();
				}
				if (choice==1) {
					listOfAlaCartesItems.remove(i);
					System.out.println("Menu Item Removed");
				}
				else {}
			}
			else {
				System.out.println("Menu Item not found in AlaCartes!");
			}
		}
		for (int i=0; i < listOfPromoSetItems.size(); i++) {
			if (listOfPromoSetItems.get(i).getName() == inputForString) {
				System.out.println("Found!");
				System.out.println(listOfPromoSetItems.get(i).getName());
				System.out.println(listOfPromoSetItems.get(i).getName());
				System.out.println("Confirm delete? 1-Yes, 0-No");
				while (choice != 0 || choice !=1) {
					choice = sc.nextInt();
				}
				if (choice==1) {
					listOfPromoSetItems.remove(i);
					System.out.println("Menu Item Removed");
				}
				else {}
			}
			else {
				System.out.println("Menu Item not found Promos!");
			}
		}

	}
	//---------------------------------------------------------------------------------------------------------------------
	public void updateMenuItem() {
		
		int choice;
		int no;
		String name;
		double price;
		
		do {
			System.out.println("Please select the type of item to update:");
			System.out.println();
			System.out.println("1) Ala Carte");
			System.out.println("2) Promotion Package");
			System.out.println("3) Exit");
			choice=sc.nextInt();
			
			
			switch(choice) {
			
			case 1:
				printAlaCarteItems();
				
				System.out.println("Please enter the index no. of Ala Carte to update:");
				no=sc.nextInt();
				
				if(no> listOfAlaCartesItems.size()||no<=0)
					System.out.println("The index is incorrect");
				else
					listOfAlaCartesItems.get(no-1).update();
				break;
				
				
			case 2:
				for(int i = 0; i< listOfPromoSetItems.size(); i++)
				{
					listOfPromoSetItems.get(i).printPromotionalSetListOfItems();
				}
				
				System.out.println("Please enter the index no. of Promotion to update:");
				no=sc.nextInt();
				
				if(no> listOfPromoSetItems.size()||no<=0)
					System.out.println("The index is incorrect");
				else
					listOfPromoSetItems.get(no-1).updateContents();
				break;
				
				
			case 3:
				break;
				
			default:
				System.out.println("Wrong Choice!!!");
				
			}
		}while(choice!=3);
	}
	
	//---------------------------------------------------------------------------------------------
	
	public AlaCarteItem getMenuItem(int index) {
			if(index> listOfAlaCartesItems.size()||index<=0) {
				System.out.println("The index is incorrect");
				return null;
			}
			else {
				return listOfAlaCartesItems.get(index - 1);
			}
	}
		
	
	public PromotionalSet getPromoItem(int index) {
		
		if(index> listOfPromoSetItems.size()||index<=0){
			System.out.println("The index is incorrect");
			return null;
		}

		else {
			return listOfPromoSetItems.get(index - 1);
		}
	}
	
}
