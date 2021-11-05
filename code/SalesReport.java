import java.io.Serializable;
import java.util.*;

public class SalesReport implements Serializable{

	private ArrayList<Invoice> listOfSales;
	private transient Scanner sc = new Scanner(System.in);

	public SalesReport() {
		listOfSales = new ArrayList<>();
	}

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

	public double calculateRevenue(ArrayList<Invoice> selectedListOfSales) {
		double sum = 0;
		for(int i = 0; i< selectedListOfSales.size(); i++) {
			sum = sum + selectedListOfSales.get(i).getFinalPrice(); // finalprice or totalprice?
		}
		return sum;
	}

	public void printAllSales() {
		sortListOfSalesByAscendingLocalDateTime(listOfSales);
		for(int i = 0; i< listOfSales.size(); i++) {
			listOfSales.get(i).printInvoice();
		}
		System.out.println("Total revenue: " + calculateRevenue(listOfSales));
	}

	public void printSalesByDay() {
		sortListOfSalesByAscendingLocalDateTime(listOfSales);
		for(int i = 0; i< listOfSales.size(); i++) {
			listOfSales.get(i).printInvoice();
		}
		System.out.println("Total revenue: " + calculateRevenue(listOfSales));
	}

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

	public void addInvoice(Invoice tobeAdded) {
		if (tobeAdded == null) {
			return;
		}
		else {
			listOfSales.add(tobeAdded);
		}
	}

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

	public ArrayList<Invoice> sortListOfSalesByAscendingLocalDateTime(ArrayList<Invoice> selectedListOfSales) {
		Collections.sort(selectedListOfSales, new Comparator<Invoice>() {
			public int compare(Invoice o1, Invoice o2) {
				return o1.getTimestamp().compareTo(o2.getTimestamp());
			}
		});
		return selectedListOfSales;
	}

}
