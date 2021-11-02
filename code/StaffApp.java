import java.util.ArrayList;
import java.util.Scanner;

public class StaffApp {
    private ArrayList<Staff> listOfStaff;
    private transient Scanner sc;

    public StaffApp() {
        listOfStaff = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    public Staff selectStaff() {

        System.out.println("Please select the following staff");

        for (int i = 0; i < listOfStaff.size(); i++) {
            System.out.println(listOfStaff.get(i).getName());
            System.out.println(listOfStaff.get(i).getEmployeeID());
            System.out.println(listOfStaff.get(i).getJobTitle());
            System.out.println(listOfStaff.get(i).getGender());
        }

        System.out.println("Please type the employee ID");
        int choice = sc.nextInt();

        for (int i = 0; i < listOfStaff.size(); i++) {
            if (listOfStaff.get(i).getEmployeeID() == choice) {
                return listOfStaff.get(i);
            }
        }
        return null;
    }

    public void addStaff() {
        Staff newStaff = new Staff();
        newStaff.update();
        listOfStaff.add(newStaff);
    }

    public void removeStaff(int employeeID) {
        sc = new Scanner(System.in);
        int choice = sc.nextInt();
        for (int i = 0; i < listOfStaff.size(); i++) {
            if (listOfStaff.get(i).getEmployeeID() == choice) {
                listOfStaff.remove(i);
                return;
            }
        }
    }


}
