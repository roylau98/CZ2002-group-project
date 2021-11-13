import java.io.Serializable;
import java.util.ArrayList;

/**
 * Manages the {@link Staff}
 * <p>
 * This class provides methods to add,remove {@link Staff} in the restaurant
 * <p>
 *
 * @since 2021-11-12
 */
public class StaffMgr implements Serializable {
    /**
     * List of {@link Staff}
     */
    private final ArrayList<Staff> listOfStaff;

    /**
     * Class constructor
     */
    public StaffMgr() {
        listOfStaff = new ArrayList<>();
        listOfStaff.add(new Staff("Patrick", Sex.MALE, 1, "Manager"));
        listOfStaff.add(new Staff("SpongeBob", Sex.MALE, 2, "Worker"));
    }

    /**
     * Return the number of {@link Staff}
     *
     * @return number of {@link Staff}
     */
    public int getTotalNoOfStaff() {
        return listOfStaff.size();
    }

    /**
     * Return {@link Staff} object by staff id
     *
     * @param id id of the {@link Staff}
     * @return {@link Staff} object to be returned
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
     * Add new {@link Staff} into restaurant
     *
     * @param    name    name of the {@link Staff}
     * @param    gender    the enumeration which indicate the gender of the {@link Staff}
     * @param    employeeID    the id of the {@link Staff}
     * @param    jobTitle    the job title of the {@link Staff}
     */
    public void addStaff(String name, Sex gender, int employeeID, String jobTitle) {
        Staff newStaff = new Staff(name, gender, employeeID, jobTitle);
        listOfStaff.add(newStaff);
    }

    /**
     * Remove existing {@link Staff} from restaurant
     *
     * @param employeeID ID of the {@link Staff} to be removed
     */
    public void removeStaff(int employeeID) {
        Staff toBeRemoved = getStaffByID(employeeID);
        listOfStaff.remove(toBeRemoved);
    }

    /**
     * Update the name of {@link Staff}
     *
     * @param    employeeID    the id of the {@link Staff} to be updated
     * @param    updatedName    the updated name of the {@link Staff}
     */
    public void updateStaffName(int employeeID, String updatedName) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setName(updatedName);
    }

    /**
     * Update the job title of {@link Staff}employee
     *
     * @param    employeeID        the id of the {@link Staff} to be updated
     * @param    updatedJobTitle    the updated job title of the {@link Staff}
     */
    public void updateStaffJobTitle(int employeeID, String updatedJobTitle) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setJobTitle(updatedJobTitle);
    }

    /**
     * Update the id of {@link Staff}
     *
     * @param    employeeID            the id of the {@link Staff} to be updated
     * @param    updatedEmployeeID    the updated id of the {@link Staff}
     */
    public void updateStaffID(int employeeID, int updatedEmployeeID) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setEmployeeID(updatedEmployeeID);
    }

    /**
     * Update the gender of {@link Staff}
     *
     * @param    employeeID    the id of the {@link Staff} to be updated
     * @param    updatedSex    the updated gender of the {@link Staff}
     */
    public void updateStaffSex(int employeeID, Sex updatedSex) {
        Staff updateThisStaff = getStaffByID(employeeID);
        updateThisStaff.setGender(updatedSex);
    }

    /**
     * Print all the {@link Staff} and their details
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
     * @param employeeID id of the {@link Staff}
     * @return true if id is valid,false otherwise
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
