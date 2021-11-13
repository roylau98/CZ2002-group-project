/**
 * Stores information about a {@code Staff} inherit from{@link Person} class
 * <p>
 * inherit various method from abstract class {@link Person}
 *
 * @since 2021-11-5
 */
public class Staff extends Person {
    /**
     * ID of {@code Staff}
     */
    private int employeeID;
    /**
     * Job titles of {@code Staff}
     */
    private String jobTitle;

    /**
     * Class Constructor with default settings
     */
    public Staff() {
        super("", Sex.MALE);
        this.employeeID = 0;
        this.jobTitle = "";
    }

    /**
     * Class Constructor
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
     * Gets ID of the {@code Staff}
     *
     * @return ID of the {@code Staff}
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Update ID of the {@code Staff}
     *
     * @param employeeID ID of the {@code Staff}
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Gets job title of the {@code Staff}
     *
     * @return job title of the {@code Staff}
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Update job title of the {@code Staff}
     *
     * @param jobTitle job title of the {@code Staff}
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

}
