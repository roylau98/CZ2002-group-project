import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SalesReport {

	private ArrayList<Invoice> listOfSales;

	public double calculateRevenue(ArrayList<Invoice> selectedListOfSales) {
		double sum = 0;
		for(int i = 0; i< selectedListOfSales.size(); i++) {
			sum = sum + selectedListOfSales.get(i).getFinalPrice(); // finalprice or totalprice?
		}
		return sum;
	}

	public void printAllSales() {
		sortListOfSalesByLocalDateTime(listOfSales);
		for(int i = 0; i< listOfSales.size(); i++) {
			listOfSales.get(i).printInvoice();
		}
		System.out.println("Total revenue: " + calculateRevenue(listOfSales));
	}

	public void printSalesByMonth() {
		sortListOfSalesByLocalDateTime(listOfSales);


	}

	public void printSalesInSelectedMonth(Month month) {
		ArrayList<Invoice> selectedListOfSales = getListOfSalesInSelectedTimeFrame(month);
		for(int i = 0; i< selectedListOfSales.size(); i++) {
			selectedListOfSales.get(i).printInvoice();
		}
		System.out.println("Total revenue in " + month + " = " + calculateRevenue(selectedListOfSales));
	}

	public void printByDay() {
		ArrayList<Invoice> selectedListOfSales = getListOfSalesInSelectedTimeFrame();
		for(int i = 0; i< selectedListOfSales.size(); i++) {
			selectedListOfSales.get(i).printInvoice();
		}
		System.out.println("Total revenue in this period = " + calculateRevenue(selectedListOfSales));
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

	public ArrayList<Invoice> getListOfSalesInSelectedTimeFrame(Month month) {
		ArrayList<Invoice> selectedList = new ArrayList<>();
		Invoice currSale;
		for(int i = 0; i< listOfSales.size(); i++) {
			currSale = listOfSales.get(i);
			if (currSale.getTimestamp().getMonth() == month) {
				//currSale.printInvoice();
				selectedList.add(currSale);
			}
		}
		if (selectedList.size() == 0) {
			System.out.println("No sales were made in this period");
			return null;
		}
		return selectedList;
	}

	public ArrayList<Invoice> sortListOfSalesByLocalDateTime(ArrayList<Invoice> selectedListOfSales) {
		Collections.sort(selectedListOfSales, new Comparator<Invoice>() {
			public int compare(Invoice o1, Invoice o2) {
				return o1.getTimestamp().compareTo(o2.getTimestamp());
			}
		});
		return selectedListOfSales;
	}

}