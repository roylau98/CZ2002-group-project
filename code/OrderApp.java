import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Manages all the {@link Order} objects of the whole restaurants, 
 * basically the "manager" of {@link RRPSS} to {@link Order} objects
 * <p> 
 * This class provides various methods to create,update(add/remove MenuItem) of the order,
 * and view individual order details and bills.
 * <p>
 * @author Chua Zi Jian
 * 
 */

public class OrderApp {

	//Pls add these func to order class(or i can add after u finish the order class)
	//void addMenuItems(MenuItems items)
	//void addPromoItems(Promotion promo)
	//void removeItems(int index)
	//void printAllItemsInOrder()
	//void printPrice()


	Scanner sc = new Scanner(System.in);
	/**
         * List of Order implemented in {@link ArrayList} data structure.
         * Each entry consists of a reference to existing {@link Order}object.
         */
	private ArrayList<Order> order;
	
	/**
         * 
         */
	private Menu menu;
	private SalesReport sales;
	
	/**
         * Constructs an {@code OrderApp} object and
         * initialize the attributes {@code Order}/{@code }/{@code } .
         */
	public OrderApp() {
		listOfOrder = new ArrayList<Order>();
		menuApp = new Menu();
		sales = new SalesReport();
	}

	//-----------------------------------------------------------------------------------------------------------
	/**
	 * A Do-While loop to create an order and add items of AlaCarteItem and add PromotionalSet to it 
	 * 
	 */
	public void createOrder() {

		int tchoice,ichoice;
		AlaCarteItem tempitem = new AlaCarteItem();
		PromotionalSet promotemp = new PromotionalSet();
		Order temp=new Order();


		do{
			
			System.out.println("1) Ala Carte");
			System.out.println("2) Promotion Package");
			System.out.println("3) Exit");
			System.out.println("Please select option(1-3):");
			tchoice=sc.nextInt();

			switch(tchoice) {

				case 1:
					menu.printAlaCarteByItemType();
					do {
						System.out.println("Please select the item to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						tempitem= menuApp.getMenuItem(ichoice,tchoice);
						temp.addMenuItems(temp);

					}while(ichoice!=-1);
					break;


				case 2:
					
					for(int i = 0;i<menu.promo.size();i++)
					{
						menu.promo.get(i).printPromotionalSet();
					}
					do {
						System.out.println("Please select the Promotion to add in to order(Enter -1 to exit):");
						ichoice=sc.nextInt();

						promotemp=menu.getPromoItem(ichoice);
						//NOT DONE YET
						temp.addMenuItems(temp);

					}while(ichoice!=-1);

					break;


				case 3:
					System.out.println("==============Your Current Order=============");
					temp.printAllItemsInOrder();
					temp.printPrice();
					System.out.println("=============================================");
					listOfOrder.add(temp);
					break;


				default:
					System.out.println("WRONG OPTION!!!");

			}
		}while(tchoice!=3);

	}
	//------------------------------------------------------------------------------------------------------------
	/**
	 * A Do-While loop to update existing order (adding more items of AlaCarteItem and add PromotionalSet to it) by orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
	public void updateOrder(int orderID) {

		int tchoice,ichoice;
		AlaCarteItem tempitem = new AlaCarteItem();
		PromotionalSet promotemp = new PromotionalSet();
		Order temp;

		for(int i = 0; i< listOfOrder.size(); i++)
		{
			if(listOfOrder.get(i).getOrderID()==orderID)
			{

				temp = listOfOrder.get(i);

				do{
					System.out.println("1) Ala Carte");
					System.out.println("2) Promotion Package");
					System.out.println("3) Exit");
					System.out.println("Please select option(1-3):");
					tchoice=sc.nextInt();

					switch(tchoice) {

						case 1:
							menu.printAlaCarteByItemType();
							do {
								System.out.println("Please select the item to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								tempitem= menuApp.getMenuItem(ichoice,tchoice);
								temp.addMenuItems(temp);

							}while(ichoice!=-1);
							break;


						case 2:
							menu.printPromotion();
							do {
								System.out.println("Please select the Promotion to add in to order(Enter -1 to exit):");
								ichoice=sc.nextInt();

								promotemp= menuApp.getPromoItem(ichoice);
								temp.addPromoItems(promotemp);

							}while(ichoice!=-1);

							break;


						case 3:
							System.out.println("==============Your Updated Order=============");
							temp.printAllItemsInOrder();
							temp.printPrice();
							System.out.println("=============================================");

							break;


						default:
							System.out.println("WRONG OPTION!!!");

					}
				}while(tchoice!=3);
				break;
			}
		}
	}
	//--------------------------------------------------------------------------------------------------------------------
	/**
	 * A Do-While loop to update existing order (removing more items of AlaCarteItem and add PromotionalSet to it) by orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
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
	/**
	 * A function that printout the existing items in order by using orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
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
	/**
	 * A function that printout the bills in order by using orderID
	 * 
	 * @param 	orderID	The ID that is used to indicate existing {@link Order} object   
	 * 
	 */
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
