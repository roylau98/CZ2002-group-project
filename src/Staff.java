import java.util.Scanner;

/**
 * Stores information about a Staff inherit from{@link Person} class
 * <p>
 * inherit various method from abstract class {@link Person}
 *
 * @since 2021-11-5
 */
public class Staff extends Person {
    /**
     * ID of employee
     */
    private int employeeID;
    /**
     * Job titles of employee
     */
    private String jobTitle;

    /**
     * Class constructor with default settings
     */
    public Staff() {
        super("", Sex.MALE);
        this.employeeID = 0;
        this.jobTitle = "";
    }

    /**
     * Class constructor
     *
     * @param name       name of the staff
     * @param gender     gender of the staff
     * @param employeeID ID of the staff
     * @param jobTitle   job title of the staff
     */
    public Staff(String name, Sex gender, int employeeID, String jobTitle) {
        super(name, gender);
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
    }

    /**
     * Gets ID of the employee
     *
     * @return employeeID    ID of the employee
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Update ID of the employee
     *
     * @param employeeID ID of the employee
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Gets job title of the employee
     *
     * @return jobTitle    job title of the employee
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Update job title of the employee
     *
     * @param jobTitle job title of the employee
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Update the details of employee
     */
    public void update() {
        Scanner sc = new Scanner(System.in);
        String inputString;
        int inputInput;
        System.out.println("What is the staff's name: ");
        inputString = sc.next();
        sc.nextLine();
        System.out.println("What is the staff's employee ID: ");
        inputInput = sc.nextInt();
        sc.nextLine();
        System.out.println("What is the staff's name: ");
        inputString = sc.next();
        sc.nextLine();
    }


}
