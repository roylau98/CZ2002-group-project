import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interface of the Staff App which has the option of create/remove etc. {@link Staff}
 * <p>
 *
 * @since 2021-11-12
 */
public class StaffApp implements Serializable, AppInterface {
    /**
     * {@link Staff} manager of this application
     */
    private final StaffMgr staffMgr;
    /**
     * Scanner for user input
     */
    private transient Scanner sc;

    /**
     * Class constructor
     *
     * @param    staffMgrEx    staff manager of the app
     */
    public StaffApp(StaffMgr staffMgrEx) {
        staffMgr = staffMgrEx;
    }

    /**
     * Open the option of the staff app
     */
    public void openOptions() {
        sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.print("\nStaff Management App\n" +
                    "Please select one of the options below:\n" +
                    "1. View all Staff\n" +
                    "2. Add a new Staff\n" +
                    "3. Remove a existing Staff\n" +
                    "4. Update an existing Staff\n" +
                    "5. Exit this application and return to the previous page\n" +
                    "Enter your choice: ");
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type received.");
            }
            switch (choice) {
                case 1:
                    printAll();
                    break;
                case 2:
                    addStaff();
                    break;
                case 3:
                    removeStaff();
                    break;
                case 4:
                    updateStaff();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid input received.");
                    break;
            }
        } while (choice != 5);
    }

    /**
     * Print all the {@link Staff} and their details in the restaurant
     */
    public void printAll() {
        System.out.println("---List Of Staff Employed---\n");
        staffMgr.printStaff();
    }

    /**
     * Add new {@link Staff} into the restaurant
     */
    public void addStaff() {
        String name = askUserForStaffStringInput("What is the staff's name: ");
        String jobTitle = askUserForStaffStringInput("What is the staff's job title: ");
        int id = askUserToSetEmployeeID();
        int sex = askUserToSetEmployeeSex();
        staffMgr.addStaff(name, staffMgr.chooseSex(sex), id, jobTitle);
    }

    /**
     * Remove {@link Staff} from the restaurant
     */
    public void removeStaff() {
        int id = askUserForEmployeeID();
        staffMgr.removeStaff(id);
    }

    /**
     * Update the details of a certain {@link Staff}
     */
    public void updateStaff() {
        int id = askUserForEmployeeID();
        if (yesOrNo("Update Staff's name: ")) {
            staffMgr.updateStaffName(id, askUserForStaffStringInput("Enter updated name: "));
        }
        if (yesOrNo("Update Staff's job title: ")) {
            staffMgr.updateStaffJobTitle(id, askUserForStaffStringInput("Enter updated job title: "));
        }
        if (yesOrNo("Update Staff's Sex: ")) {
            staffMgr.updateStaffSex(id, staffMgr.chooseSex(askUserToSetEmployeeSex()));
        }
        if (yesOrNo("Update Staff's ID: ")) {
            staffMgr.updateStaffID(id, askUserToSetEmployeeID());
        }
    }

    /**
     * Return a selected {@link Staff}
     *
     * @return the selected {@link Staff}
     */
    public Staff selectStaff() {
        if (staffMgr.getTotalNoOfStaff() == 0) {
            System.out.println("There is no staff currently");
            return null;
        }
        System.out.println("Please select the following staff");
        staffMgr.printStaff();
        int employeeID = askUserForEmployeeID();
        return staffMgr.getStaffByID(employeeID);
    }

    /**
     * Return the number of {@link Staff} in the restaurant
     *
     * @return number of {@link Staff}
     */
    public int getNoOfStaff() {
        return staffMgr.getTotalNoOfStaff();
    }

    /**
     * Scanner to ask for user input(setting {@link Staff} id) with error checking
     *
     * @return employeeID  the id of the {@link Staff}
     */
    private int askUserToSetEmployeeID() {
        sc = new Scanner(System.in);
        int employeeID;
        System.out.print("What is the staff's employee ID: ");
        try {
            employeeID = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input type received");
            return askUserToSetEmployeeID();
        }
        if (staffMgr.getStaffByID(employeeID) != null) {
            System.out.println("ID is already taken. Try again!");
            return askUserToSetEmployeeID();
        }
        if (employeeID < 0) {
            System.out.println("ID cannot be negative. Try again!");
            return askUserToSetEmployeeID();
        }
        return employeeID;
    }

    /**
     * Scanner to ask for user input(finding {@link Staff} by id) with error checking
     *
     * @return employeeID  the id of the {@link Staff}
     */
    private int askUserForEmployeeID() {
        sc = new Scanner(System.in);
        int employeeID;
        System.out.print("Enter employee ID: ");
        try {
            employeeID = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input type received");
            return askUserForEmployeeID();
        }
        if (!staffMgr.validateEmployeeID(employeeID)) {
            System.out.println("No such employee. Try again!");
            return askUserForEmployeeID();
        }
        return employeeID;
    }

    /**
     * Scanner to ask for user input(string) with error checking
     *
     * @param    whatToAsk    the details which indicate what to ask from user
     * @return                details of the {@link Staff}
     */
    private String askUserForStaffStringInput(String whatToAsk) {
        sc = new Scanner(System.in);
        String inputString;
        System.out.println(whatToAsk);
        try {
            inputString = sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input type. Try again!");
            return askUserForStaffStringInput(whatToAsk);
        }
        return inputString;
    }

    /**
     * Scanner to ask for user input(Gender) with error checking
     *
     * @return the enumeration of gender of the  {@link Staff}
     */
    private int askUserToSetEmployeeSex() {
        sc = new Scanner(System.in);
        int choice;
        System.out.print("What is the staff's employee sex. 0 - Male, 1 - Female: ");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input type received. Try again!");
            return askUserToSetEmployeeSex();
        }
        if (choice != 0) {
            if (choice == 1) {
                return choice;
            }
            System.out.println("Invalid input type received. Try again!");
            return askUserToSetEmployeeSex();
        }
        return choice;
    }

    /**
     * Scanner to ask for user input(Boolean) with error checking
     *
     * @return true if updated false otherwise
     * @param    UpdateThis    the details which indicate what to be updated
     */
    private Boolean yesOrNo(String UpdateThis) {
        sc = new Scanner(System.in);
        int choice = 999;

        System.out.println(UpdateThis + " 1-Yes, 0-No");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Try again!");
        }
        if (choice == 1) {
            return true;
        } else if (choice == 0) {
            return false;
        } else {
            return yesOrNo(UpdateThis);
        }
    }


}

