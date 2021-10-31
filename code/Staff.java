public class Staff extends Person {
	private int employeeID;
	private String jobTitle;

	public Staff(String name, Sex gender, int employeeID, String jobTitle) {
		super(name, gender);
		this.employeeID = employeeID;
		this.jobTitle = jobTitle;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
}