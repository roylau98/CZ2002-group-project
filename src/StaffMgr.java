import java.io.Serializable;
import java.util.ArrayList;

/**
 * Manages the {@link Staff}
 * <p>
 * This class provides methods to add,remove Staff in the restaurant
 * <p>
 *
 * @since 2021-11-12
 */
public class StaffMgr implements Serializable {
    /**
     * List of staff
     */
    private final ArrayList<Staff> listOfStaff;

    /**
     * Class constructor with default settings
     */
    public StaffMgr() {
        listOfStaff = new ArrayList<>();
        listOfStaff.add(new Staff("Patrick", Sex.MALE, 1, "Manager"));
        listOfStaff.add(new Staff("SpongeBob", Sex.MALE, 2, "Worker"));
    }

    /**
     * Return the number of staff
     *
     * @return number of staff
     */
    public int getTotalNoOfStaff() {
        return listOfStaff.size();
    }

    /**
     * Return staff object by staff id
     *
     * @param id id of the staff
     * @return staff  staff object to be returned
     */
    public Staff getStaffByID(int id) {
        for (Staff staff : listOfStaff) {
            if (staff.getEmployeeID() == id) {
                return staff;
            }
        }
        return null;
    }

    /**
     * Add new staff into restaurant
     *
     * @param    name    name of the staff
     * @param    gender    the enumeration which indicate the gender of the staff
     * @param    employeeID    the id of the staff
     * @param    jobTitle    the job title of the staff
     */
    public void addStaff(String name, Sex gender, int employeeID, String jobTitle) {
        Staff newStaff = new Staff(name, gender, employeeID, jobTitle);
        listOfStaff.add(newStaff);
    }

    /**
     * Remove existing staff from restaurant
     *
     * @param employeeID ID of the staff to be removed
     */
    public void removeStaff(int employeeID) {
        Staff toBeRemoved = getStaffByID(employeeID);
        listOfStaff.remove(toBeRemoved);
    }

    /**
     * Update the name of employee
     *
     * @param    employeeID    the id of the staff to be updated
     * @param    updatedName    the updated name of the staff
     */
    public void updateStaffName(int employeeID, String updatedName) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setName(updatedName);
    }

    /**
     * Update the job title of employee
     *
     * @param    employeeID        the id of the staff to be updated
     * @param    updatedJobTitle    the updated job title of the staff
     */
    public void updateStaffJobTitle(int employeeID, String updatedJobTitle) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setJobTitle(updatedJobTitle);
    }

    /**
     * Update the id of employee
     *
     * @param    employeeID            the id of the staff to be updated
     * @param    updatedEmployeeID    the updated id of the staff
     */
    public void updateStaffID(int employeeID, int updatedEmployeeID) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setEmployeeID(updatedEmployeeID);
    }

    /**
     * Update the sex of employee
     *
     * @param    employeeID    the id of the staff to be updated
     * @param    updatedSex    the updated gender of the staff
     */
    public void updateStaffSex(int employeeID, Sex updatedSex) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setGender(updatedSex);
    }

    /**
     * Print all the staff and their details
     */
    public void printStaff() {
        for (Staff staff : listOfStaff) {
            System.out.println("Name: " + staff.getName());
            System.out.println("Staff ID: " + staff.getEmployeeID());
            System.out.println("Job title: " + staff.getJobTitle());
            System.out.println("Sex: " + staff.getGender() + "\n");

        }
    }

    /**
     * Return true if the id is existing,false otherwise
     *
     * @param employeeID id of the employee
     * @return the validate of the id
     */
    public boolean validateEmployeeID(int employeeID) {
        for (Staff s : listOfStaff) {
            if (s.getEmployeeID() == employeeID)
                return true;
        }
        return false;
    }

    /**
     * Return the gender by selection
     *
     * @param choice choice of the gender
     * @return enumeration of the gender
     */
    public Sex chooseSex(int choice) {
        if (choice == 0) {
            return Sex.MALE;
        } else {
            return Sex.FEMALE;
        }
    }

}
