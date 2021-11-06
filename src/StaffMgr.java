import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the {@link Staff}
 * <p>
 * This class provides methods to add,remove Staff in the restaurant
 * <p>
 *
 * @since 2021-11-6
 */
public class StaffMgr implements Serializable {
    /**
     * List of staff
     */
    private final ArrayList<Staff> listOfStaff;
    private transient Scanner sc;

    /**
     * Class constructor with default settings
     */
    public StaffMgr() {
        listOfStaff = new ArrayList<>();
        listOfStaff.add(new Staff("Patrick", Sex.MALE, 1, "Manager"));
        listOfStaff.add(new Staff("SpongeBob", Sex.MALE, 2, "Worker"));
    }


    public int getTotalNoOfStaff() {
        return listOfStaff.size();
    }

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
     */
    public void updateStaffName(int employeeID, String updatedName) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setName(updatedName);
    }
    /**
     * Update the job title of employee
     */
    public void updateStaffJobTitle(int employeeID, String updatedJobTitle) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setJobTitle(updatedJobTitle);
    }
    /**
     * Update the id of employee
     */
    public void updateStaffID(int employeeID, int updatedEmployeeID) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setEmployeeID(updatedEmployeeID);
    }
    /**
     * Update the sex of employee
     */
    public void updateStaffSex(int employeeID, Sex updatedSex) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setGender(updatedSex);
    }

    public void printStaff() {
        for (Staff staff : listOfStaff) {
            System.out.println("Name: "+staff.getName());
            System.out.println("Staff ID: "+staff.getEmployeeID());
            System.out.println("Job title: "+staff.getJobTitle());
            System.out.println("Sex: "+staff.getGender()+"\n");

        }
    }

    public boolean validateEmployeeID(int employeeID) {
        for (Staff s : listOfStaff) {
            if (s.getEmployeeID() == employeeID)
                return true;
        }
        return false;
    }

    public Sex chooseSex(int choice) {
        if (choice == 0) {
            return Sex.MALE;
        }
        else {
            return Sex.FEMALE;
        }
    }

}

