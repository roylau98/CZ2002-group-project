import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class SalesReport implements Serializable{
	private ArrayList<Invoice> listOfSales;
	private transient Scanner sc = new Scanner(System.in);

	public SalesReport() {
		listOfSales = new ArrayList<>();
	}

	public void options() {
		System.out.println("Please select one of the options below:\n" +
				"1. Make a new reservation\n" +
				"2. Cancel an existing reservation\n" +
				"3. Amend an existing reservation\n" +
				"4. View the list of reservations\n" +
				"5. View the list of tables\n" +
				"6. Check availability at a specified date and time\n" +
				"7. Quit this application and return to the previous page");
		int choice = 0;
		while (choice != -1) {
			System.out.println("Enter your choice");
			sc = new Scanner(System.in);
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					System.out.println("Exited");
					return;
				default:
					System.out.println("Invalid choice try again");
			}
		}

	}

	private void print(LocalDate start, LocalDate end) {
		HashMap<MenuItem, Integer> salesCount = new HashMap<>();
		for (Invoice invoice : listOfSales) {
			LocalDate invoiceDate = invoice.getTimestamp().toLocalDate();
			if (invoiceDate.isBefore(start) || invoiceDate.isAfter(end))
				continue;
			for (MenuItem menuItem : invoice.getListOfSoldItems()) {
				salesCount.putIfAbsent(menuItem, 1);
				salesCount.replace(menuItem, salesCount.get(menuItem) + 1);
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