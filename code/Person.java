public abstract class Person {

	private String name;
	private Sex gender;

	public Person(String name, Sex gender){
		this.name = name;
		this.gender = gender;
	}
	public String getName() {
		return this.name;
	}

	public Sex getGender() {
		return this.gender;
	}

}
