import java.util.ArrayList;
import java.util.Scanner;

public class OrderApp {

	//Pls add these func to order class(or i can add after u finish the order class)
	//void addMenuItems(MenuItems items)
	//void addPromoItems(Promotion promo)
	//void removeItems(int index)
	//void printAllItemsInOrder()
	//void printPrice()

	//for OrderApp i havent add in any error detection yet i am not sure if we need one for the selection
	//of item add/remove/update because i already add it in the Menu Class


	Scanner sc = new Scanner(System.in);

	private ArrayList<Order> order;
	private Menu menu;
	private SalesReport sales;

	public OrderApp() {
		order = new ArrayList<Order>();
		menu = new Menu();
		sales = new SalesReport();
	}

	//-----------------------------------------------------------------------------------------------------------
	public void createOrder() {

		int tchoice,ichoice;
		MenuItems tempitem = new MenuItems();
		Promotion promotemp = new Promotion();
		Order temp=new Order();


		do{
			menu.printType();
			System.out.println("Please select option(1-6):");
			tchoice=sc.nextInt();

			switch(tchoice) {

				case 1:
					menu.printMenu(tchoice);
					do {
						System.out.println("Please select the item to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						tempitem=menu.getMenuItem(ichoice,tchoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);
					break;


				case 2:
					menu.printMenu(tchoice);
					do {
						System.out.println("Please select the item to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						tempitem=menu.getMenuItem(ichoice,tchoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);
					break;


				case 3:
					menu.printMenu(tchoice);
					do {
						System.out.println("Please select the item to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						tempitem=menu.getMenuItem(ichoice,tchoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);
					break;


				case 4:
					menu.printMenu(tchoice);
					do {
						System.out.println("Please select the item to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						tempitem=menu.getMenuItem(ichoice,tchoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);
					break;


				case 5:
					menu.printMenu(tchoice);
					do {
						System.out.println("Please select the Promotion to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						promotemp=menu.getPromoItem(ichoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);

					break;


				case 6:
					System.out.println("==============Your Current Order=============");
					temp.printAllItemsInOrder();
					temp.printPrice();
					System.out.println("=============================================");
					order.add(temp);
					break;


				default:
					System.out.println("WRONG OPTION!!!");

			}
		}while(tchoice!=6);

	}
	//------------------------------------------------------------------------------------------------------------
	public void updateOrder(int orderID) {

		int tchoice,ichoice;
		MenuItems tempitem = new MenuItems();
		Promotion promotemp = new Promotion();
		Order temp;

		for(int i =0;i<order.size();i++)
		{
			if(order.get(i).getOrderID()==orderID)
			{

				temp = order.get(i);

				do{
					menu.printType();
					System.out.println("Please select option(1-6):");
					tchoice=sc.nextInt();

					switch(tchoice) {

						case 1:
							menu.printMenu(tchoice);
							do {
								System.out.println("Please select the item to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								tempitem=menu.getMenuItem(ichoice,tchoice);
								temp.addMenuItems(temp);

							}while(ichoice!=-1);
							break;


						case 2:
							menu.printMenu(tchoice);
							do {
								System.out.println("Please select the item to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								tempitem=menu.getMenuItem(ichoice,tchoice);
								temp.addMenuItems(temp);

							}while(ichoice!=-1);
							break;


						case 3:
							menu.printMenu(tchoice);
							do {
								System.out.println("Please select the item to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								tempitem=menu.getMenuItem(ichoice,tchoice);
								temp.addMenuItems(temp);

							}while(ichoice!=-1);
							break;


						case 4:
							menu.printMenu(tchoice);
							do {
								System.out.println("Please select the item to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								tempitem=menu.getMenuItem(ichoice,tchoice);
								temp.addMenuItems(temp);

							}while(ichoice!=-1);
							break;


						case 5:
							menu.printMenu(tchoice);
							do {
								System.out.println("Please select the Promotion to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								promotemp=menu.getPromoItem(ichoice);
								temp.addPromoItems(promotemp);

							}while(ichoice!=-1);

							break;


						case 6:
							System.out.println("==============Your Updated Order=============");
							temp.printAllItemsInOrder();
							temp.printPrice();
							System.out.println("=============================================");

							break;


						default:
							System.out.println("WRONG OPTION!!!");

					}
				}while(tchoice!=6);
				break;
			}
		}
	}
	//--------------------------------------------------------------------------------------------------------------------
	public void removeOrderItem(int orderID) {
		int index=0;
		Order temp;

		for(int i =0;i<order.size();i++)
		{
			if(order.get(i).getOrderID()==orderID)
			{

				temp = order.get(i);
				do{

					System.out.println("==============Your Current Order=============");
					temp.printAllItemsInOrder();
					System.out.println("=============================================");
					System.out.println("Please select the item to remove(Enter -1 to exit)");
					temp.removeItems(index);

				}while(index!=-1);


				System.out.println("==============Your Updated Order=============");
				temp.printAllItemsInOrder();
				temp.printPrice();
				System.out.println("=============================================");
				break;
			}

		}
	}
	//-----------------------------------------------------------------------------------------------------------------------
	public void viewOrder(int orderID) {

		Order temp;

		for(int i =0;i<order.size();i++)
		{
			if(order.get(i).getOrderID()==orderID)
			{

				temp = order.get(i);
				System.out.println("==============Your Current Order=============");
				temp.printAllItemsInOrder();
				temp.printPrice();
				System.out.println("=============================================");
				break;
			}
		}
	}
	//-----------------------------------------------------------------------------------------------------------------------
	public void chargeBill(int orderID) {
		Order temp;
		Invoice bill;

		for(int i =0;i<order.size();i++)
		{
			if(order.get(i).getOrderID()==orderID)
			{

				temp = order.get(i);
				bill=temp.getInvoice();
				bill.printInvoice();
				break;
			}
		}

	}
}
}