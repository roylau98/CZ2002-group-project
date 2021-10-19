import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;
/**
 * Manages the {@link AlaCarteItem} and {@link PromotionalSet} of the {@link Menu}. 
 * <p> 
 * This class provides printing of whole menu or individual AlaCarteItems based on Itemtype and PromotionalSet,
 * provide accessor (get methods) of individual AlaCarteItem & PromotionalSet
 * and various methods to add,remove,update AlaCarteItem/PromotionalSet in the menu.
 * <p>
 * @author Chua Zi Jian
 * 
 */

public class Menu {
	
	Scanner sc = new Scanner(System.in);
	
    /**
     * List of AlaCarteItem and PromotionalSet, implemented respectively in {@link List} and {@link ArrayList} data structure.
     * Each entry consists of a reference to existing {@link AlaCarteItem}/{@link PromotionalSet}object.
     */
	private List<AlaCarteItem> alaCartes;
	private ArrayList<PromotionalSet> promo;
	
	
    /**
     * Constructs an {@code Menu} object and
     * initialize the attributes {@code AlaCarteItem}/{@code PromotionalSet} .
     */
	public Menu(){
		
		 alaCartes = new ArrayList<AlaCarteItem>();
		 promo = new ArrayList<PromotionalSet>();
		
	}
	//-------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * Prints all the items in the AlaCarteItem List with the order of(1.Main Course 2.Appertizer 3.Drinks 4.Dessert)
	 */
	public void printAlaCarte() {
		
		printMenuItem(ItemType.MAIN_COURSE);
		printMenuItem(ItemType.APPERTIZER);
		printMenuItem(ItemType.DRINKS);
		printMenuItem(ItemType.DESSERT);
		
	}
	
	
	
	/**
	 * Prints the items in the AlaCarteItem List of specific ItemType (1.Main Course 2.Appertizer 3.Drinks 4.Dessert) 
	 * in a certain format
	 * 
	 * @param  type  an enumeration{@link ItemType} which is used to indicate the type of AlaCarteItem
	 */
	public void printMenuItem(ItemType type) {
		System.out.println("--------------"+type+"---------------");
		for (int i = 0; i < alaCartes.size(); i++)
		{
			if(alaCartes.get(i).getItemType()==type)
			{
				System.out.println(i+1+")");
				System.out.println();
				System.out.println("Name       : "+alaCartes.get(i).getName());
				System.out.println("Price      : "+alaCartes.get(i).getPrice());
				System.out.println("Description: "+alaCartes.get(i).getDescription());
				System.out.println();
			}
		}
		System.out.println("----------------------------------------");
		}
	
	
	/**
	 * Prints all the items in the List of PromotionalSet 
	 */
	public void printPromotion() {
		for(int i = 0;i<promo.size();i++)
		{
			promo.get(i).printPromotionalSet();
		}
	}
	//-----------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * A Do-While loop to add new items of AlaCarteItem and add new promotion to PromotionalSet with existing AlaCarteItems 
	 * 
	 */
	public void addMenuItem() {
		
		int choice;
		String name=new String();
		String description=new String();
		double price;
		AlaCarteItem temp=new AlaCarteItem();
		ItemType type = null;
		PromotionalSet temp1;
		int n;
		
		do {
			
			System.out.println("Please select the type of item to add:");
			System.out.println();
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
						
						//buffer
						sc.nextLine();
						
						
						System.out.println("Enter the new name :");
						name=sc.nextLine();
						
						System.out.println("Enter the new price:");
						price = sc.nextDouble();
						
						//buffer
						sc.nextLine();
						System.out.println("Enter the new description:");
						description = sc.nextLine();
						
						temp= new AlaCarteItem(name,description,price,type);
						alaCartes.add(temp);
						break;
						
					case 2:
						
						temp1=new PromotionalSet();
						temp1.update(alaCartes);
						promo.add(temp1);
						break;
						
					case 3:
						Collections.sort(alaCartes);
						break;
						
					default:
						System.out.println("Wrong Option!!!!!");
				
				}
			}while(choice!=3);
			
		}
	//----------------------------------------------------------------------------------------------------------
	
	
	/**
	 * A Do-While loop to remove existing items of AlaCarteItem and PromotionalSet 
	 * 
	 */
	public void removeItem() {
		
		int choice;
		int no;
		
		do {
		System.out.println("Please select the type of item to remove:");
		System.out.println();
		System.out.println("1) Ala Carte");
		System.out.println("2) Promotion Package");
		System.out.println("3) Exit");
		choice=sc.nextInt();
		
		
		switch(choice) {
		
			case 1:
				
				printAlaCarte();
				
				
				System.out.println("Please enter the index no. of Ala Carte to remove:");
				no=sc.nextInt();
				
				if(alaCartes.size()==0)
					System.out.println("Ala Carte List is empty");
				else if(no>alaCartes.size()||no<=0)
					System.out.println("The index is incorrect");
				else
					alaCartes.remove(no-1);
				break;
				
				
			case 2:
				
				printPromotion();
				
				System.out.println("Please enter the index no. of Promotion to remove:");
				no=sc.nextInt();
				
				if(promo.size()==0)
					System.out.println("Promotion List is empty");
				else if(no>promo.size()||no<=0)
					System.out.println("The index is incorrect");
				else
					promo.remove(no-1);
				break;
				
				
			case 3:
				break;
				
			default:
				System.out.println("Wrong Choice!!!");
			
		}	
		}while(choice!=3);
	}
	//---------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * A Do-While loop to update the parameter(name,price,description) of existing items of AlaCarteItem and PromotionalSet 
	 * 
	 */
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
				printAlaCarte();
				
				System.out.println("Please enter the index no. of Ala Carte to update:");
				no=sc.nextInt();
				
				if(alaCartes.size()==0)
					System.out.println("Ala Carte List is empty");
				else if(no>alaCartes.size()||no<=0)
					System.out.println("The index is incorrect");
				else
					alaCartes.get(no-1).update();
				break;
				
				
			case 2:
				printPromotion();
				
				System.out.println("Please enter the index no. of Promotion to update:");
				no=sc.nextInt();
				
				if(promo.size()==0)
					System.out.println("Promotion List is empty");
				else if(no>promo.size()||no<=0)
					System.out.println("The index is incorrect");
				else
					promo.get(no-1).update(alaCartes);
				break;
				
				
			case 3:
				break;
				
			default:
				System.out.println("Wrong Choice!!!");
				
			}
		}while(choice!=3);
	}
	
	//---------------------------------------------------------------------------------------------
	
	
	/**
	 * Return a existing {@link AlaCarteItem}object by using the INDEX(actual index plus 1) of ArrayList 
	 * 
	 * @param  index   the INDEX(actual index plus 1) of alaCarte to be retrieved.
         * @return {@link AlaCarteItem} object of given index.
	 */
	public AlaCarteItem getMenuItem(int index) {
	
				return alaCartes.get(index-1);		
	}
		
	
	/**
	 * Return a existing {@link PromotionalSet}object by using the INDEX(actual index plus 1) of ArrayList 
	 * 
	 * @param  index   the INDEX(actual index plus 1) of alaCarte to be retrieved.
         * @return {@link PromotionalSet} object of given index.
	 */
	public PromotionalSet getPromoItem(int index) {
		
			return promo.get(index-1);
		
			
		
	}
	
}
