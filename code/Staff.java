import java.util.Scanner;

public class Staff extends Person {
	private int employeeID;
	private String jobTitle;
	private Scanner sc;

	public Staff() {
		super("", Sex.MALE);
		this.employeeID = 0;
		this.jobTitle = "";
	}
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

	public void update() {
		sc = new Scanner(System.in);
		String inputString;
		int inputInput;
		System.out.println("What is the staff's name: ");
		inputString = sc.next();
		System.out.println("What is the staff's employee ID: ");
		inputInput = sc.nextInt();
		System.out.println("What is the staff's name: ");
		inputString = sc.next();
	}




}