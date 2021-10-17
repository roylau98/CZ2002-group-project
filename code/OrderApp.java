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

	private ArrayList<Order> listOfOrder;
	private Menu menuApp;
	private SalesReport sales;

	public OrderApp() {
		listOfOrder = new ArrayList<Order>();
		menuApp = new Menu();
		sales = new SalesReport();
	}

	//-----------------------------------------------------------------------------------------------------------
	public void createOrder() {

		int tchoice,ichoice;
		MenuItem tempitem = new MenuItem();
		PromotionalSet promotemp = new PromotionalSet();
		Order temp=new Order();


		do{
			menuApp.printInstruction();
			System.out.println("Please select option(1-6):");
			tchoice=sc.nextInt();

			switch(tchoice) {

				case 1:
					menuApp.printMenu(tchoice);
					do {
						System.out.println("Please select the item to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						tempitem= menuApp.getMenuItem(ichoice,tchoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);
					break;


				case 2:
					menuApp.printMenu(tchoice);
					do {
						System.out.println("Please select the item to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						tempitem= menuApp.getMenuItem(ichoice,tchoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);
					break;


				case 3:
					menuApp.printMenu(tchoice);
					do {
						System.out.println("Please select the item to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						tempitem= menuApp.getMenuItem(ichoice,tchoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);
					break;


				case 4:
					menuApp.printMenu(tchoice);
					do {
						System.out.println("Please select the item to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						tempitem= menuApp.getMenuItem(ichoice,tchoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);
					break;


				case 5:
					menuApp.printMenu(tchoice);
					do {
						System.out.println("Please select the Promotion to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						promotemp= menuApp.getPromoItem(ichoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);

					break;


				case 6:
					System.out.println("==============Your Current Order=============");
					temp.printAllItemsInOrder();
					temp.printPrice();
					System.out.println("=============================================");
					listOfOrder.add(temp);
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

		for(int i = 0; i< listOfOrder.size(); i++)
		{
			if(listOfOrder.get(i).getOrderID()==orderID)
			{

				temp = listOfOrder.get(i);

				do{
					menuApp.printInstruction();
					System.out.println("Please select option(1-6):");
					tchoice=sc.nextInt();

					switch(tchoice) {

						case 1:
							menuApp.printMenu(tchoice);
							do {
								System.out.println("Please select the item to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								tempitem= menuApp.getMenuItem(ichoice,tchoice);
								temp.addMenuItems(temp);

							}while(ichoice!=-1);
							break;


						case 2:
							menuApp.printMenu(tchoice);
							do {
								System.out.println("Please select the item to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								tempitem= menuApp.getMenuItem(ichoice,tchoice);
								temp.addMenuItems(temp);

							}while(ichoice!=-1);
							break;


						case 3:
							menuApp.printMenu(tchoice);
							do {
								System.out.println("Please select the item to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								tempitem= menuApp.getMenuItem(ichoice,tchoice);
								temp.addMenuItems(temp);

							}while(ichoice!=-1);
							break;


						case 4:
							menuApp.printMenu(tchoice);
							do {
								System.out.println("Please select the item to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								tempitem= menuApp.getMenuItem(ichoice,tchoice);
								temp.addMenuItems(temp);

							}while(ichoice!=-1);
							break;


						case 5:
							menuApp.printMenu(tchoice);
							do {
								System.out.println("Please select the Promotion to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								promotemp= menuApp.getPromoItem(ichoice);
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

		for(int i = 0; i< listOfOrder.size(); i++)
		{
			if(listOfOrder.get(i).getOrderID()==orderID)
			{

				temp = listOfOrder.get(i);
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

		for(int i = 0; i< listOfOrder.size(); i++)
		{
			if(listOfOrder.get(i).getOrderID()==orderID)
			{

				temp = listOfOrder.get(i);
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

		for(int i = 0; i< listOfOrder.size(); i++)
		{
			if(listOfOrder.get(i).getOrderID()==orderID)
			{

				temp = listOfOrder.get(i);
				bill=temp.getInvoice();
				bill.printInvoice();
				break;
			}
		}

	}
}
}