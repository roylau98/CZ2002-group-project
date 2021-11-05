import java.io.Serializable;
import java.util.*;
/**
 * Manage and print restaurant sales report daily or monthly.
 * The sales report generated can either be a daily report or monthly report, based on the given user input.
 * 
 * 
 * @author 
 * @since 2021-11-5
 */
public class SalesReport implements Serializable{
	/**
     	* List of invoices
     	*/
	private ArrayList<Invoice> listOfSales;
	private transient Scanner sc = new Scanner(System.in);
	/**
     	* Class constructer with default settings
     	*/
	public SalesReport() {
		listOfSales = new ArrayList<>();
	}
	/**
    	 *Open Sales Report App options
     	*/
	public void options() {
		int choice = 999;
		int input;
		int day,month,year;
		
		while (choice != -1) {
			System.out.println("=====Sales Report Option====");
			System.out.println("Here are your choices: \n" +
					"1)Print All Sales\n" +
					"2)Print Sales by Days\n" +
					"3)Print Sales by Month\n" +
					"4)Print Sales of Selected Day\n"+
					"5)Print Sales of Selected Month\n"+
					"6)Exit\n");
			try 
			{
				System.out.print("Enter your choice: ");
				sc = new Scanner(System.in);
				choice = sc.nextInt();
            		} 
			catch (InputMismatchException e) 
			{
                		sc.nextLine();
            		}
			switch (choice) {
				case 1:
					printAllSales();
					break;
				case 2:
					printSalesByDay();
					break;
				case 3:
					printSalesByMonth();
					break;
				case 4:
					while(true)
					{
						try 
						{
							System.out.println("Enter day:");
							day = sc.nextInt();
							if (day<=0)
								throw new Exception("Error: date must not lower than 1!" );
							System.out.println("Enter month:");
							month = sc.nextInt();
							if (month<=0)
								throw new Exception("Error: date must not lower than 1!" );
							System.out.println("Enter year:");
							year = sc.nextInt();
							if (year<=0)
								throw new Exception("Error: date must not lower than 1!" );
							break;
			            		} 
						catch (InputMismatchException e) 
						{
							System.out.println("Please enter a number!!!");
			               			sc.nextLine();
			           		}
						catch(Exception e)
						{
							System.out.println(e.getMessage());
						}
					}
					printSalesInSelectedDay(day,month,year);
					break;
				case 5:
					while(true)
					{
						try 
						{
							System.out.println("Enter month:");
							month = sc.nextInt();
							if (month<=0)
								throw new Exception("Error: date must not lower than 1!" );
							System.out.println("Enter year:");
							year = sc.nextInt();
							if (year<=0)
								throw new Exception("Error: date must not lower than 1!" );
							break;
			            		} 
						catch (InputMismatchException e) 
						{
							System.out.println("Please enter a number!!!");
			                		sc.nextLine();
			            		}
						catch(Exception e)
						{
							System.out.println(e.getMessage());
						}
					}
					printSalesInSelectedMonth(month,year);
					break;
				case 6:
					System.out.println("Exited");
					System.out.println("==End of SalesReport Option==");
					return;
				default:
					System.out.println("Invalid choice try again");
			}
		}


	}
	/**
     	* Calculate the revenue of all sales
     	*/
	public double calculateRevenue(ArrayList<Invoice> selectedListOfSales) {
		double sum = 0;
		for(int i = 0; i< selectedListOfSales.size(); i++) {
			sum = sum + selectedListOfSales.get(i).getFinalPrice(); // finalprice or totalprice?
		}
		return sum;
	}
	/**
     	* Print the revenue of all sales
     	*/
	public void printAllSales() {
		sortListOfSalesByAscendingLocalDateTime(listOfSales);
		for(int i = 0; i< listOfSales.size(); i++) {
			listOfSales.get(i).printInvoice();
		}
		System.out.println("Total revenue: " + calculateRevenue(listOfSales));
	}
	/**
     	* Print the revenue of all sales by days
     	*/
	public void printSalesByDay() {
		sortListOfSalesByAscendingLocalDateTime(listOfSales);
		for(int i = 0; i< listOfSales.size(); i++) {
			listOfSales.get(i).printInvoice();
		}
		System.out.println("Total revenue: " + calculateRevenue(listOfSales));
	}
	/**
     	* Print the revenue of all sales by months
     	*/
	public void printSalesByMonth() {
		sortListOfSalesByAscendingLocalDateTime(listOfSales);
		int minYear = listOfSales.get(0).getTimestamp().getYear();
		int maxYear = listOfSales.get(listOfSales.size()-1).getTimestamp().getYear();

		for (int i = minYear ; i <= maxYear; i++) {
			for (int j = 1; j<=12; j++) {
				printSalesInSelectedMonth(j,i);
			}
		}

		System.out.println("Total revenue: " + calculateRevenue(listOfSales));
	}
	/**
     	* Print the revenue of all sales in a selected month
	*
	* @param	month	selected month to print revenue report
	* @param	year	selected year to print revenue report
     	*/
	public void printSalesInSelectedMonth(int month, int year) {
		ArrayList<Invoice> selectedListOfSales = getListOfSalesInSelectedTimeFrame(month, year);
		if (selectedListOfSales == null) {
			System.out.println("No sales were made in " + month + "/" + year);
			return;
		}
		for(int i = 0; i< selectedListOfSales.size(); i++) {
			selectedListOfSales.get(i).printInvoice();
		}
		System.out.println("Total revenue in " + month + "/"+year + " = " + calculateRevenue(selectedListOfSales));
	}
	/**
     	* Print the revenue of all sales in a selected day
	*
	* @param	day	selected day to print revenue report
	* @param	month	selected month to print revenue report
	* @param	year	selected year to print revenue report
     	*/
	public void printSalesInSelectedDay(int day, int month, int year) {
		ArrayList<Invoice> selectedListOfSales = getListOfSalesInSelectedTimeFrame(day,month,year);
		if (selectedListOfSales == null) {
			System.out.println("No sales were made in this period");
			return;
		}
		for(int i = 0; i< selectedListOfSales.size(); i++) {
			selectedListOfSales.get(i).printInvoice();
		}
		System.out.println("Total revenue in " + day+"/"+month+"/"+year + " = " + calculateRevenue(selectedListOfSales));
	}
	/**
     	* Create list of invoices from all completed order
	*
	* @param	listOfOrders	list of all order created
     	*/
	public void createListOfInvoices(ArrayList<Order> listOfOrders ) {
		Order currOrder;
		for(int i = 0; i< listOfOrders.size(); i++) {
			currOrder = listOfOrders.get(i);
			if (currOrder.isCompleted() == true) {
				listOfSales.add(currOrder.getInvoice());
			}
			else {}
		}
	}
	/**
     	* Add invoice into the list
	*
	* @param	tobeAdded	Invoice to be added
     	*/
	public void addInvoice(Invoice tobeAdded) {
		if (tobeAdded == null) {
			return;
		}
		else {
			listOfSales.add(tobeAdded);
		}
	}
	/**
     	* Return the invoice in a selected month
	*
	* @param	month	selected month to return invoice
	* @param	year	selected year to return invoice
     	*/
	public ArrayList<Invoice> getListOfSalesInSelectedTimeFrame(int month, int year) {
		ArrayList<Invoice> selectedList = new ArrayList<>();
		Invoice currSale;
		for(int i = 0; i< listOfSales.size(); i++) {
			currSale = listOfSales.get(i);
			if (currSale.getTimestamp().getMonthValue() == month && currSale.getTimestamp().getYear() == year) {
				selectedList.add(currSale);
			}
		}
		if (selectedList.size() == 0) {
			return null;
		}
		return selectedList;
	}
	/**
     	* Return the invoice in a selected day
	*
	* @param	day	selected day to return invoice
	* @param	month	selected month to return invoice
	* @param	year	selected year to return invoice
     	*/
	public ArrayList<Invoice> getListOfSalesInSelectedTimeFrame(int day, int month, int year) {
		ArrayList<Invoice> selectedList = new ArrayList<>();
		Invoice currSale;
		for(int i = 0; i< listOfSales.size(); i++) {
			currSale = listOfSales.get(i);
			if (currSale.getTimestamp().getMonthValue() == month && currSale.getTimestamp().getYear() == year && currSale.getTimestamp().getDayOfMonth() == day) {
				selectedList.add(currSale);
			}
		}
		if (selectedList.size() == 0) {
			System.out.println("No sales were made in this period");
			return null;
		}
		return selectedList;
	}
	/**
     	* Sort the list of the invoice according to the date and time
	*
	* @param	selectedListOfSales	list of invoice to be sorted
     	*/
	public ArrayList<Invoice> sortListOfSalesByAscendingLocalDateTime(ArrayList<Invoice> selectedListOfSales) {
		Collections.sort(selectedListOfSales, new Comparator<Invoice>() {
			public int compare(Invoice o1, Invoice o2) {
				return o1.getTimestamp().compareTo(o2.getTimestamp());
			}
		});
		return selectedListOfSales;
	}

}
