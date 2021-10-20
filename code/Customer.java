public class Customer extends Person {

	private int contactNo;
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

}