import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the {@link Staff}
 * <p>
 * This class provides methods to add,remove Staff in the restaurant
 * <p>
 *
 * @since 2021-11-5
 */
public class StaffApp implements Serializable {
    /**
     * List of staff
     */
    private final ArrayList<Staff> listOfStaff;
    private transient Scanner sc;

    /**
     * Class constructor with default settings
     */
    public StaffApp() {
        listOfStaff = new ArrayList<>();
        listOfStaff.add(new Staff("Patrick", Sex.MALE, 1, "Manager"));
        listOfStaff.add(new Staff("SpongeBob", Sex.MALE, 2, "Worker"));
    }

    /**
     * Return a selected staff
     *
     * @return the selected staff
     */
    public Staff selectStaff() {
        sc = new Scanner(System.in);
        System.out.println("Please select the following staff");

        for (Staff staff : listOfStaff) {
            System.out.println(staff.getName());
            System.out.println(staff.getEmployeeID());
            System.out.println(staff.getJobTitle());
            System.out.println(staff.getGender());
        }

        System.out.println("Please type the employee ID");
        int choice = sc.nextInt();
        sc.nextLine();

        for (Staff staff : listOfStaff) {
            if (staff.getEmployeeID() == choice) {
                return staff;
            }
        }
        return null;
    }

    /**
     * Add new staff into restaurant
     */
    public void addStaff() {
        Staff newStaff = new Staff();
        newStaff.update();
        listOfStaff.add(newStaff);
    }

    /**
     * Remove existing staff from restaurant
     *
     * @param employeeID ID of the staff to be removed
     */
    public void removeStaff(int employeeID) {
        sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < listOfStaff.size(); i++) {
            if (listOfStaff.get(i).getEmployeeID() == choice) {
                listOfStaff.remove(i);
                return;
            }
        }
    }
}
