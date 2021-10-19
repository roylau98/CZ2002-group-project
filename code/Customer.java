public class Customer extends Person {

	private int contactNo;
	private Boolean membershipStatus;

	public Boolean isMember() {
		// TODO - implement Customer.isMember
		return membershipStatus;
	}
	public Customer(String name, Sex gender, int contactNo, Boolean membershipStatus) {
		// TODO - implement Customer.Customer
		super(name, gender);
		this.contactNo = contactNo;
		this.membershipStatus = membershipStatus;
	}
	public Customer(int parameter) {
		// TODO - implement Customer.Customer // what is this?
		throw new UnsupportedOperationException();
	}
	public int getContactNo() {
		// TODO - implement Customer.getContactNo
		return contactNo;
	}
}
