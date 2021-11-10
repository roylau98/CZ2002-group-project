import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * Manage and print restaurant sales report daily or monthly.
 * The sales report generated can either be a daily report or monthly report, based on the given user input.
 *
 * @since 2021-11-5
 */
public class SalesReport implements Serializable {
    /**
     * List of invoices
     */
    private final ArrayList<Invoice> listOfSales;
    private transient Scanner sc;

    /**
     * Class constructor with default settings
     */
    public SalesReport() {
        listOfSales = new ArrayList<>();
    }

    /**
     * Open Sales Report App options
     */
    public void salesReportOptions() {
        sc = new Scanner(System.in);
        int choice = 999;
        int day, month, year;

        while (choice != -1) {
            System.out.print("\nSales Report App\n" +
                    "Please select one of the options below:\n" +
                    "1. Print all sales\n" +
                    "2. Print all sales by day\n" +
                    "3. Print all sales by month\n" +
                    "4. Print sales in selected period\n" +
                    "5. Exit this application and return to the previous page\n" +
                    "Enter your choice: ");
            try {
                sc = new Scanner(System.in);
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
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
                    System.out.println("Enter the date for the start of the period:");
                    int startDay = askUserForDate();
                    int startMonth = askUserForMonth();
                    int startYear = askUserForYear();
                    LocalDate start = LocalDate.of(startYear, startMonth, startDay);
                    System.out.println("Enter the date for the end of the period:");
                    int endDay = askUserForDate();
                    int endMonth = askUserForMonth();
                    int endYear = askUserForYear();
                    LocalDate end = LocalDate.of(endYear, endMonth, endDay);
                    if (end.isBefore(start)) {
                        System.out.println("End date is before start date. Invalid input");
                    } else {
                        printSalesByPeriod(start, end);
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice try again");
                    break;
            }
        }


    }

    public void printSalesByPeriod(LocalDate start, LocalDate end) {
        HashMap<MenuItem, Integer> salesCount = new HashMap<>();
        HashMap<MenuItem, Double> revenue = new HashMap<>();
        for (Invoice invoice : listOfSales) {
            LocalDate invoiceDate = invoice.getTimestamp().toLocalDate();
            if (invoiceDate.isBefore(start) || invoiceDate.isAfter(end))
                continue;
            for (MenuItem menuItem : invoice.getListOfSoldItems()) {
                if (salesCount.containsKey(menuItem)) {
                    salesCount.replace(menuItem, salesCount.get(menuItem) + 1);
                } else {
                    salesCount.putIfAbsent(menuItem, 1);
                }
            }
        }
        for (MenuItem menuItem : salesCount.keySet()) {
            revenue.put(menuItem, salesCount.get(menuItem) * menuItem.getPrice());
        }
        System.out.println("The total sales from " + start + " to " + end + "is: ");
        revenue.forEach((menuItem, aDouble) -> System.out.println(menuItem + aDouble.toString()));
        double totalRevenue = 0;
        for (double d : revenue.values())
            totalRevenue += d;
        System.out.println("Total revenue = " + totalRevenue);
    }

    /**
     * Print the revenue of all sales
     */
    private void printAllSales() {
        sortListOfSalesByAscendingLocalDateTime(listOfSales);
        for (Invoice invoice : listOfSales) {
            invoice.printInvoice();
        }
        System.out.println("Total revenue: " + calculateRevenue(listOfSales));
    }

    /**
     * Print the revenue of all sales by days
     */
    private void printSalesByDay() {
        sortListOfSalesByAscendingLocalDateTime(listOfSales);
        for (Invoice invoice : listOfSales) {
            invoice.printInvoice();
        }
        System.out.println("Total revenue: " + calculateRevenue(listOfSales));
    }

    /**
     * Print the revenue of all sales by months
     */
    private void printSalesByMonth() {
        sortListOfSalesByAscendingLocalDateTime(listOfSales);
        int minYear = listOfSales.get(0).getTimestamp().getYear();
        int maxYear = listOfSales.get(listOfSales.size() - 1).getTimestamp().getYear();

        for (int i = minYear; i <= maxYear; i++) {
            for (int j = 1; j <= 12; j++) {
                printSalesInSelectedMonth(j, i);
            }
        }

        System.out.println("Total revenue: " + calculateRevenue(listOfSales));
    }

    /**
     * Print the revenue of all sales in a selected month
     *
     * @param month selected month to print revenue report
     * @param year  selected year to print revenue report
     */
    private void printSalesInSelectedMonth(int month, int year) {
        ArrayList<Invoice> selectedListOfSales = getListOfSalesInSelectedTimeFrame(month, year);
        if (selectedListOfSales == null) {
            System.out.println("No sales were made in " + month + "/" + year);
            return;
        }
        for (Invoice invoice : selectedListOfSales) {
            invoice.printInvoice();
        }
        System.out.println("Total revenue in " + month + "/" + year + " = " + calculateRevenue(selectedListOfSales));
    }

    /**
     * Print the revenue of all sales in a selected day
     *
     * @param day   selected day to print revenue report
     * @param month selected month to print revenue report
     * @param year  selected year to print revenue report
     */
    private void printSalesInSelectedDay(int day, int month, int year) {
        ArrayList<Invoice> selectedListOfSales = getListOfSalesInSelectedTimeFrame(day, month, year);
        if (selectedListOfSales == null) {
            System.out.println("No sales were made in this period");
            return;
        }
        for (Invoice invoice : selectedListOfSales) {
            invoice.printInvoice();
        }
        System.out.println("Total revenue in " + day + "/" + month + "/" + year + " = " + calculateRevenue(selectedListOfSales));
    }


    /**
     * Add invoice into the list
     *
     * @param tobeAdded Invoice to be added
     */
    public void addInvoice(Invoice tobeAdded) {
        if (tobeAdded == null) {
            return;
        }
        listOfSales.add(tobeAdded);
    }

    /**
     * Sort the list of the invoice according to the date and time
     *
     * @param selectedListOfSales list of invoice to be sorted
     */
    private void sortListOfSalesByAscendingLocalDateTime(ArrayList<Invoice> selectedListOfSales) {
        selectedListOfSales.sort(Comparator.comparing(Invoice::getTimestamp));
    }

    private int askUserForDate() {
        System.out.println("Enter day:");
        sc = new Scanner(System.in);
        int day;
        try {
            day = sc.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input type. Try again!");
            return  askUserForDate();
        }

        if (day<=0) {
            System.out.println("Error: date can only be between values 1 and 31. Try again!");
            return askUserForDate();
        }
        if (day>31) {
            System.out.println("Error: date can only be between values 1 and 31. Try again!");
            return askUserForDate();
        }
        return day;
    }

    private int askUserForMonth() {
        System.out.println("Enter month:");
        sc = new Scanner(System.in);
        int month;
        try {
            month = sc.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input type. Try again!");
            return  askUserForMonth();
        }

        if (month<=0) {
            System.out.println("Error: month can only be between values 1 and 12. Try again!");
            return askUserForMonth();
        }
        if (month>12) {
            System.out.println("Error: month can only be between values 1 and 12. Try again!");
            return askUserForMonth();
        }
        return month;
    }

    private int askUserForYear() {
        System.out.println("Enter year:");
        sc = new Scanner(System.in);
        int year;
        try {
            year = sc.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input type. Try again!");
            return  askUserForYear();
        }

        if (year<=0) {
            System.out.println("Error: year cannot be less than 1. Try again!");
            return askUserForYear();
        }
        return year;
    }
}