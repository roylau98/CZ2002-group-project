import java.util.Objects;

/**
 * Represents a customer entity.
 * It is used in the Reservation class.
 *
 * @author
 * @since 2021-10-19
 */
public class Customer extends Person {
    /**
     * Customer contact number
     */
    private String contactNo;
    /**
     * Customer membershipStatus
     */
    private Boolean membershipStatus;

    /**
     * Class constructor
     *
     * @param name             name of Customer
     * @param gender           gender of Customer
     * @param contactNo        contact number of Customer
     * @param membershipStatus Membership Status of Customer
     */
    public Customer(String name, Sex gender, String contactNo, Boolean membershipStatus) {
        super(name, gender);
        this.contactNo = contactNo;
        this.membershipStatus = membershipStatus;
    }

    /**
     * Gets Customer Contact number
     *
     * @return customer's contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * Changes customer Contact number.
     *
     * @param contactNo New contact number of the customer.
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * Gets customer membership status.
     *
     * @return customer's membership status.
     */
    public Boolean getMembershipStatus() {
        return membershipStatus;
    }

    /**
     * Changes customer membership status.
     *
     * @param membershipStatus New membership status of the customer.
     */
    public void setMembershipStatus(Boolean membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    /**
     * Checks and compares Customer objects
     *
     * @param obj Customer object
     * @return true if the Customer object is the same, else false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer)) {
            return false;
        }
        if (!this.contactNo.equals(((Customer) obj).getContactNo()))
            return false;
        if (!this.getName().equals(((Customer) obj).getName()))
            return false;
        return true;
        //return super.equals(obj);
    }

    /**
     * Generates a hash for an instance of the Customer class.
     * All customers with the exact same attributes will generate the same hashCode.
     * This is used in the mapping for HashMap.
     *
     * @return hashCode for the customer instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), contactNo);
    }
}