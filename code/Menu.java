import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	//Pls write these function for both menuitem and promotion class(or i can add after u finish the class)
	//void updateName(string name);
	//void updatePrice(double price);
	//void updateDescription(string description);

	//**There is a in-built function called MenuItem i think so i changed the class name to MenuItems**

	//i am not sure how u implement the promotion class so i just leave it as for now

	//all my add/remove/update Menu Items is in do while loop so if u dont select exit it will keep continuing the func until
	//u finish all the things u wan to update

	//add/remove/update have error detection
	//do u wan to add error detection for price to make sure it is positive float??


	Scanner sc = new Scanner(System.in);

	private ArrayList<MenuItem> main;
	private ArrayList<MenuItem> appertizer;
	private ArrayList<MenuItem> drink;
	private ArrayList<MenuItem> dessert;
	private ArrayList<PromotionalSet> promo;


	public Menu(){

		main = new ArrayList<MenuItem>();
		appertizer = new ArrayList<MenuItem>();
		drink = new ArrayList<MenuItem>();
		dessert = new ArrayList<MenuItem>();
		promo = new ArrayList<PromotionalSet>();

	}
	//-------------------------------------------------------------------------------------------------------------
	public void printInstruction() {


		System.out.println("=============THE KRUSTY KRAB=============");
		System.out.println("1)   Main Course");
		System.out.println("2)   Appertizer");
		System.out.println("3)   Drinks");
		System.out.println("4)   Dessert");
		System.out.println("5)   Spongebob Recommendation");
		//System.out.println("6)   Everything");
		System.out.println("7)   Exit");
		System.out.println("========================================");
		System.out.println();

	}
	public void printMenu(int c) {

		switch(c) {

			case 1:
				System.out.println("--------------Main Course---------------");
				for (int i = 0; i < main.size(); i++)
				{
					System.out.println("Main Course "+i+1+")");
					System.out.println();
					//******inside the ArrayList i think there is a getName inbuilt func so i change the name to getItemName*******
					//System.out.println("Name       : "+main.get(i).getItemName());
					System.out.println("Name       : "+main.get(i).getName());
					System.out.println("Price      : "+main.get(i).getPrice());
					System.out.println("Description: "+main.get(i).getDescription());
					System.out.println();
				}
				System.out.println("----------------------------------------");
				break;


			case 2:
				System.out.println("--------------Appertizer---------------");
				for (int i = 0; i < appertizer.size(); i++)
				{
					System.out.println("Appertizer "+i+1+")");

					System.out.println();
					System.out.println("Name       : "+appertizer.get(i).getName());
					System.out.println("Price      : "+appertizer.get(i).getPrice());
					System.out.println("Description: "+appertizer.get(i).getDescription());
					System.out.println();
				}
				System.out.println("---------------------------------------");
				break;


			case 3:
				System.out.println("--------------Drinks---------------");
				for (int i = 0; i < drink.size(); i++)
				{
					System.out.println("Drink "+i+1+")");

					System.out.println();
					System.out.println("Name       : "+drink.get(i).getName());
					System.out.println("Price      : "+drink.get(i).getPrice());
					System.out.println("Description: "+drink.get(i).getDescription());
					System.out.println();
				}
				System.out.println("-----------------------------------");
				break;


			case 4:
				System.out.println("--------------Dessert---------------");
				for (int i = 0; i < dessert.size(); i++)
				{
					System.out.println("Dessert "+i+1+")");

					System.out.println();
					System.out.println("Name       : "+dessert.get(i).getName());
					System.out.println("Price      : "+dessert.get(i).getPrice());
					System.out.println("Description: "+dessert.get(i).getDescription());
					System.out.println();
				}
				System.out.println("------------------------------------");
				break;


			case 5:
				System.out.println("***********PROMOTION SETS***********");
				for (int i = 0; i < promo.size(); i++)
				{
					System.out.println("Promotion "+i+1+")");
					//******inside the ArrayList i think there is a getName inbuilt func so i change the name to getItemName*******
					//for promotion can u do two func one is to print the name of all item in that certain promo package the other for the price??
					System.out.println();
					System.out.println("Package      : \n"+promo.get(i).getName());
					System.out.println("Price        : "+promo.get(i).getPrice());
					System.out.println("Description  : "+promo.get(i).getDescription());
					System.out.println("Promo Set Items : ");
					promo.get(i).printPromotionalSet();
					System.out.println();
				}
				System.out.println("************************************");
				break;

			default:
				System.out.println("Wrong Choice!!!");

		}


	}
	//-----------------------------------------------------------------------------------------------------------------
	public void addMenuItem() {

		int choice;
		String name;
		String description;
		double price;
		MenuItem addMenuitem;

		do {

			System.out.println("Please select the type of item to add:");
			System.out.println();
			System.out.println("1) Main Course");
			System.out.println("2) Appertizer");
			System.out.println("3) Drinks");
			System.out.println("4) Dessert");
			System.out.println("5) Promotion Package");
			System.out.println("6) Exit");

			choice=sc.nextInt();

			switch(choice) {

				case 1:
					System.out.println("Enter the name of new Main Course:");
					name = sc.nextLine();

					System.out.println("Enter the price of new Main Course:");
					price = sc.nextDouble();

					System.out.println("Enter the description of new Main Course:");
					description = sc.nextLine();

					addMenuitem = new AlaCarteItem(name,description,price,ItemType.MAIN_COURSE);
					main.add(addMenuitem);
					break;

				case 2:
					System.out.println("Enter the name of new Appertizer:");
					name = sc.nextLine();

					System.out.println("Enter the price of new Appertizer:");
					price = sc.nextDouble();

					System.out.println("Enter the description of new Appertizer:");
					description = sc.nextLine();

					addMenuitem = new AlaCarteItem(name,description,price,ItemType.APPERTIZER);
					appertizer.add(addMenuitem);
					break;

				case 3:
					System.out.println("Enter the name of new Drinks:");
					name = sc.nextLine();

					System.out.println("Enter the price of new Drinks:");
					price = sc.nextDouble();

					System.out.println("Enter the description of new Drinks:");
					description = sc.nextLine();

					addMenuitem = new AlaCarteItem(name,description,price,ItemType.DRINKS);
					drink.add(addMenuitem);
					break;

				case 4:
					System.out.println("Enter the name of new Dessert:");
					name = sc.nextLine();

					System.out.println("Enter the price of new Dessert:");
					price = sc.nextDouble();

					System.out.println("Enter the description of new Dessert:");
					description = sc.nextLine();

					addMenuitem = new AlaCarteItem(name,description,price,ItemType.DESSERT);
					dessert.add(addMenuitem);
					break;

				case 5:
					System.out.println("Enter the name of new Promotional Set:");
					name = sc.nextLine();

					System.out.println("Enter the price of new Promotional Set:");
					price = sc.nextDouble();

					System.out.println("Enter the description of new Promotional Set:");
					description = sc.nextLine();

					addMenuitem = new PromotionalSet(name,description,price);
					addMenuitem.update();
					promo.add((PromotionalSet) addMenuitem);
					break;

				case 6:
					break;

				default:
					System.out.println("Wrong Option!!!!!");

			}
		}while(choice!=6);

	}
	//----------------------------------------------------------------------------------------------------------
	public void removeItem() {

		int choice;
		int menuIndexNo;

		do {
			System.out.println("Please select the type of item to remove:");
			System.out.println();
			System.out.println("1) Main Couse");
			System.out.println("2) Appertizer");
			System.out.println("3) Drinks");
			System.out.println("4) Dessert");
			System.out.println("5) Promotion Package");
			System.out.println("6) Exit");
			choice=sc.nextInt();


			printMenu(choice);
			switch(choice) {

				case 1:
					System.out.println("Please enter the index no. of Main Course to remove:");
					menuIndexNo=sc.nextInt();

					if(menuIndexNo>main.size()||menuIndexNo<=0)
						System.out.println("The index no. is incorrect");
					else
						main.remove(menuIndexNo-1);
					break;


				case 2:
					System.out.println("Please enter the index no. of Appertizer to remove:");
					menuIndexNo=sc.nextInt();

					if(menuIndexNo>appertizer.size()||menuIndexNo<=0)
						System.out.println("The index is incorrect");
					else
						appertizer.remove(menuIndexNo-1);
					break;


				case 3:
					System.out.println("Please enter the index no. of Drinks to remove:");
					menuIndexNo=sc.nextInt();

					if(menuIndexNo>drink.size()||menuIndexNo<=0)
						System.out.println("The index is incorrect");
					else
						drink.remove(menuIndexNo-1);
					break;


				case 4:
					System.out.println("Please enter the index no. of Dessert to remove:");
					menuIndexNo=sc.nextInt();

					if(menuIndexNo>dessert.size()||menuIndexNo<=0)
						System.out.println("The index is incorrect");
					else
						dessert.remove(menuIndexNo-1);
					break;


				case 5:
					System.out.println("Please enter the index no. of Promotion to remove:");
					menuIndexNo=sc.nextInt();

					if(menuIndexNo>promo.size()||menuIndexNo<=0)
						System.out.println("The index is incorrect");
					else
						promo.remove(menuIndexNo-1);
					break;


				case 6:
					break;

				default:
					System.out.println("Wrong Choice!!!");

			}
		}while(choice!=6);
	}
	//---------------------------------------------------------------------------------------------------------------------
	public void updateMenuItem() {

		int choice;
		int menuIndexNo;
		String updatedName;
		String updatedDescription;
		double updatedPrice;

		do {
			System.out.println("Please select the type of item to update:");
			System.out.println();
			System.out.println("1) Main Couse");
			System.out.println("2) Appertizer");
			System.out.println("3) Drinks");
			System.out.println("4) Dessert");
			System.out.println("5) Promotion Package");
			System.out.println("6) Exit");
			choice=sc.nextInt();

			printMenu(choice);
			switch(choice) {

				case 1:
					System.out.println("Please enter the index no. of Main Course to update:");
					menuIndexNo=sc.nextInt();

					if(menuIndexNo>main.size()||menuIndexNo<=0)
						System.out.println("The index is incorrect");
					else
						main.get(menuIndexNo-1).update();
					break;


				case 2:
					System.out.println("Please enter the index no. of Appertizer to update:");
					menuIndexNo=sc.nextInt();

					if(menuIndexNo>appertizer.size()||menuIndexNo<=0)
						System.out.println("The index is incorrect");
					else
						appertizer.get(menuIndexNo-1).update();
					break;


				case 3:
					System.out.println("Please enter the index no. of Drinks to update:");
					menuIndexNo=sc.nextInt();

					if(menuIndexNo>drink.size()||menuIndexNo<=0)
						System.out.println("The index is incorrect");
					else
						drink.get(menuIndexNo-1).update();
					break;


				case 4:
					System.out.println("Please enter the index no. of Dessert to update:");
					menuIndexNo=sc.nextInt();

					if(menuIndexNo>dessert.size()||menuIndexNo<=0)
						System.out.println("The index is incorrect");
					else
						dessert.get(menuIndexNo-1).update();
					break;


				case 5:
					System.out.println("Please enter the index no. of Promotion to update:");
					menuIndexNo=sc.nextInt();

					if(menuIndexNo>promo.size()||menuIndexNo<=0)
						System.out.println("The index is incorrect");
					else

					break;


				case 6:
					break;

				default:
					System.out.println("Wrong Choice!!!");

			}
		}while(choice!=6);
	}

	public void update(MenuItem item) {
		int choice=0;
		String name;
		String description;
		double price;

		do {

			System.out.println("Update:");
			System.out.println();
			System.out.println("(1) Name");
			System.out.println("(2) Price");
			System.out.println("(3) Description");
			System.out.println("(4) Exit");
			choice=sc.nextInt();

			switch(choice) {
				case 1:
					System.out.println("Enter a new name:");
					name=sc.nextLine();
					item.updateName(name);
					break;

				case 2:
					System.out.println("Enter a new price:");
					price=sc.nextDouble();
					item.updatePrice(price);
					break;

				case 3:
					System.out.println("Enter a new description");
					description=sc.nextLine();
					item.updateDescription(description);
					break;

				case 4:
					break;

				default:
					System.out.println("Wrong Input!!!");
			}
		}while(choice!=4);
	}

	//---------------------------------------------------------------------------------------------
	//type 1=main 2=appertizer 3=drinks 4=desserts
	public MenuItem getMenuItem(int index,int type) {

		switch(type) {

			case 1:
				if(index>main.size()||index<=0) {
					System.out.println("The index is incorrect");
				}
				else {
					return main.get(index-1);
				}
				break;


			case 2:
				if(index>appertizer.size()||index<=0) {
					System.out.println("The index is incorrect");
				}
				else {
					return appertizer.get(index-1);
				}
				break;


			case 3:
				if(index>drink.size()||index<=0) {
					System.out.println("The index is incorrect");
				}
				else {
					return drink.get(index-1);
				}
				break;


			case 4:
				if(index>dessert.size()||index<=0) {
					System.out.println("The index is incorrect");
				}
				else {
					return dessert.get(index - 1);
				}
				break;

			default:
				System.out.println("Wrong type!!!");
				return null;

		}

	}
	public PromotionalSet getPromoItem(int index) {
		if(index>main.size()||index<=0) {
			System.out.println("The index is incorrect");
			return null;
		}
		else {
			return promo.get(index-1);
		}
	}
}
