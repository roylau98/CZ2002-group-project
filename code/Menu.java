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

	private ArrayList<MenuItems> main;
	private ArrayList<MenuItems> appertizer;
	private ArrayList<MenuItems> drink;
	private ArrayList<MenuItems> dessert;
	private ArrayList<Promotion> promo;


	public Menu(){

		ArrayList<MenuItems> main = new ArrayList<MenuItems>();
		ArrayList<MenuItems> appertizer = new ArrayList<MenuItems>();
		ArrayList<MenuItems> drink = new ArrayList<MenuItems>();
		ArrayList<MenuItems> dessert = new ArrayList<MenuItems>();
		ArrayList<Promotion> promo = new ArrayList<Promotion>();

	}
	//-------------------------------------------------------------------------------------------------------------
	public void printType() {


		System.out.println("=============THE KRUSTY KRAB=============");
		System.out.println("1)   Main Course");
		System.out.println("2)   Appertizer");
		System.out.println("3)   Drinks");
		System.out.println("4)   Dessert");
		System.out.println("5)   Spongebob Recommendation");
		System.out.println("6)   Exit");
		System.out.println("========================================");
		System.out.println();
		//printMenu(1);
		//printMenu(2);
		//printMenu(3);
		//printMenu(4);
		//printMenu(5);

	}
	public void printMenu(int c) {

		switch(c) {

			case 1:
				System.out.println("--------------Main Course---------------");
				for (int i = 0; i < main.size(); i++)
				{
					System.out.println("Main Course "+i+1+")");
					//******inside the ArrayList i think there is a getName inbuilt func so i change the name to getItemName*******
					System.out.println();
					System.out.println("Name       : "+main.get(i).getItemName());
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
					System.out.println("Name       : "+appertizer.get(i).getItemName());
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
					System.out.println("Name       : "+drink.get(i).getItemName());
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
					System.out.println("Name       : "+dessert.get(i).getItemName());
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
					System.out.println("Promotion "i+1+")");
					//******inside the ArrayList i think there is a getName inbuilt func so i change the name to getItemName*******
					//for promotion can u do two func one is to print the name of all item in that certain promo package the other for the price??
					System.out.println();
					System.out.println("Package      : \n"+promo.get(i).printItem());
					System.out.println("Price        : "+promo.get(i).getPrice());
					System.out.println("Description  : "+promo.get(i).getDescription());
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
		MenuItem temp;
		Promotion temp1;

		do {


			System.out.println("Please select the type of item to add:");
			System.out.println();
			System.out.println("1) Main Couse");
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

					temp= new MenuItem(name,price,description);
					main.add(temp);
					break;

				case 2:
					System.out.println("Enter the name of new Appertizer:");
					name = sc.nextLine();

					System.out.println("Enter the price of new Appertizer:");
					price = sc.nextDouble();

					System.out.println("Enter the description of new Appertizer:");
					description = sc.nextLine();

					temp = new MenuItem(name,price,description);
					appertizer.add(temp);
					break;

				case 3:
					System.out.println("Enter the name of new Drinks:");
					name = sc.nextLine();

					System.out.println("Enter the price of new Drinks:");
					price = sc.nextDouble();

					System.out.println("Enter the description of new Drinks:");
					description = sc.nextLine();

					temp = new MenuItem(name,price,description);
					drink.add(temp);
					break;

				case 4:
					System.out.println("Enter the name of new Dessert:");
					name = sc.nextLine();

					System.out.println("Enter the price of new Dessert:");
					price = sc.nextDouble();

					System.out.println("Enter the description of new Dessert:");
					description = sc.nextLine();

					temp = new MenuItem(name,price,description);
					dessert.add(temp);
					break;

					/*case 5:
						System.out.println("Enter the name of new Main Course:");
						name = sc.nextLine();

						System.out.println("Enter the price of new Main Course:");
						price = sc.nextDouble();

						System.out.println("Enter the description of new Main Course:");
						description = sc.nextLine();

						temp1 = new Promotion(name,price,description);
						promo.add(temp1);
						break;*/
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
		int no;

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
					no=sc.nextInt();

					if(no>main.size()||no<=0)
						System.out.println("The index is incorrect");
					else
						main.remove(no-1);
					break;


				case 2:
					System.out.println("Please enter the index no. of Appertizer to remove:");
					no=sc.nextInt();

					if(no>appertizer.size()||no<=0)
						System.out.println("The index is incorrect");
					else
						appertizer.remove(no-1);
					break;


				case 3:
					System.out.println("Please enter the index no. of Drinks to remove:");
					no=sc.nextInt();

					if(no>drink.size()||no<=0)
						System.out.println("The index is incorrect");
					else
						drink.remove(no-1);
					break;


				case 4:
					System.out.println("Please enter the index no. of Dessert to remove:");
					no=sc.nextInt();

					if(no>dessert.size()||no<=0)
						System.out.println("The index is incorrect");
					else
						dessert.remove(no-1);
					break;


				case 5:
					System.out.println("Please enter the index no. of Promotion to remove:");
					no=sc.nextInt();

					if(no>promo.size()||no<=0)
						System.out.println("The index is incorrect");
					else
						promo.remove(no-1);
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
		int no;
		String name;
		double price;

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
					no=sc.nextInt();

					if(no>main.size()||no<=0)
						System.out.println("The index is incorrect");
					else
						update(main.get(no-1));
					break;


				case 2:
					System.out.println("Please enter the index no. of Appertizer to update:");
					no=sc.nextInt();

					if(no>appertizer.size()||no<=0)
						System.out.println("The index is incorrect");
					else
						update(appertizer.get(no-1));
					break;


				case 3:
					System.out.println("Please enter the index no. of Drinks to update:");
					no=sc.nextInt();

					if(no>drink.size()||no<=0)
						System.out.println("The index is incorrect");
					else
						update(drink.get(no-1));
					break;


				case 4:
					System.out.println("Please enter the index no. of Dessert to update:");
					no=sc.nextInt();

					if(no>dessert.size()||no<=0)
						System.out.println("The index is incorrect");
					else
						update(dessert.get(no-1));
					break;


				case 5:
					System.out.println("Please enter the index no. of Promotion to update:");
					no=sc.nextInt();

					if(no>promo.size()||no<=0)
						System.out.println("The index is incorrect");
					else
						updatePromo(promo.get(no-1));
					break;


				case 6:
					break;

				default:
					System.out.println("Wrong Choice!!!");

			}
		}while(choice!=6);
	}

	public void update(MenuItems item) {
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
	/*public void updatePromo(Promotion promo) {
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
	}*/
	//---------------------------------------------------------------------------------------------
	//type 1=main 2=appertizer 3=drinks 4=desserts
	public MenuItems getMenuItem(int index,int type) {

		switch(type) {

			case 1:
				if(index>main.size()||index<=0)
					System.out.println("The index is incorrect");
				else
					return main.get(index-1);
				break;


			case 2:
				if(index>appertizer.size()||index<=0)
					System.out.println("The index is incorrect");
				else
					return appertizer.get(index-1);
				break;


			case 3:
				if(index>drink.size()||index<=0)
					System.out.println("The index is incorrect");
				else
					return drink.get(index-1);
				break;


			case 4:
				if(index>dessert.size()||index<=0)
					System.out.println("The index is incorrect");
				else
					return dessert.get(index-1);
				break;


			default:
				System.out.println("Wrong type!!!");

		}

	}
	public Promotion getPromoItem(int index) {

		if(index>main.size()||index<=0)
			System.out.println("The index is incorrect");
		else
			return promo.get(index-1);
	


	}
}
