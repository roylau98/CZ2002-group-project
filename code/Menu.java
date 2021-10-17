import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Menu {
	
	Scanner sc = new Scanner(System.in);
	
	private List<AlaCarteItem> alaCartes;
	private ArrayList<PromotionalSet> promo;
	
	
	public Menu(){
		
		List<AlaCarteItem> alaCartes = new ArrayList<AlaCarteItem>();
		ArrayList<PromotionalSet> promo = new ArrayList<PromotionalSet>();
		
	}
	//-------------------------------------------------------------------------------------------------------------
	
	public void printAlaCarte() {
		
		printMenuItem(ItemType.MAIN_COURSE);
		printMenuItem(ItemType.APPERTIZER);
		printMenuItem(ItemType.DRINKS);
		printMenuItem(ItemType.DESSERT);
		
	}
	public void printMenuItem(ItemType type) {
		System.out.println("--------------"+type+"---------------");
		for (int i = 0; i < food.size(); i++)
		{
			if(alaCartes.get(i).getItemType==type)
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
						
						
						System.out.println("Enter the new name :");
						name = sc.nextLine();
						
						System.out.println("Enter the new price:");
						price = sc.nextDouble();
						
						System.out.println("Enter the new description:");
						description = sc.nextLine();
						
						temp= new AlaCarte(name,description,price,type);
						alaCartes.add(temp);
						break;
						
					case 2:
						
						temp1=new PromotionalSet();
						temp1.update(food);
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
				
				if(no>alaCartes.size()||no<=0)
					System.out.println("The index is incorrect");
				else
					alaCartes.remove(no-1);
				break;
				
				
			case 2:
				
				for(int i =0;i<promo.size();i++)
				{
					promo.get(i).printPromotionalSet();
				}
				
				
				System.out.println("Please enter the index no. of Promotion to remove:");
				no=sc.nextInt();
				
				if(no>promo.size()||no<=0)
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
				
				if(no>alaCartes.size()||no<=0)
					System.out.println("The index is incorrect");
				else
					alaCartes.get(no-1).update();
				break;
				
				
			case 2:
				for(int i =0;i<promo.size();i++)
				{
					promo.get(i).printPromotionalSet();
				}
				
				System.out.println("Please enter the index no. of Promotion to update:");
				no=sc.nextInt();
				
				if(no>promo.size()||no<=0)
					System.out.println("The index is incorrect");
				else
					promo.get(no-1).update();
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
			if(index>alaCartes.size()||index<=0)
				System.out.println("The index is incorrect");
			else
				return alaCartes.get(index-1);		
	}
		
	
	public PromotionalSet getPromoItem(int index) {
		
		if(index>promo.size()||index<=0)
			System.out.println("The index is incorrect");
		else
			return promo.get(index-1);
		break;
			
		
	}
	
}
