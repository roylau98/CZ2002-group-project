import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SalesReport {

	private ArrayList<Invoice> listOfSales;

	public SalesReport() {
		listOfSales = new ArrayList<>();
	}

	public void options() {
		Scanner sc = new Scanner(System.in);
		int choice = 999;
		int input;

		System.out.println("Here are your choices: \n" +
				"1)" +
				"2)" +
				"3)" +
				"4) Exit");
		while (choice != -1) {
			System.out.println("Enter your choice");
			choice = sc.nextInt();

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
		listOfSales.add(tobeAdded);
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