import java.util.Objects;

/**
 * A parent class for Customer and Staff class
 *
 * @author
 * @since 2021-10-19
 */
public class Person {
    /**
     * Person name
     */
    private String name;

    /**
     * Person gender
     */
    private Sex gender;

    /**
     * Class constructor
     *
     * @param name   name of the person
     * @param gender gender of the person
     */
    public Person(String name, Sex gender) {
        this.name = name;
        this.gender = gender;
    }

    /**
     * Gets the name of the person
     *
     * @return name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the person
     *
     * @param name name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the gender of the person
     *
     * @return gender of the person
     */
    public Sex getGender() {
        return this.gender;
    }

    /**
     * Sets the gender of the person
     *
     * @param gender gender of the person
     */
    public void setGender(Sex gender) {
        this.gender = gender;
    }

    /**
     * Checks and compares Person objects
     * @param obj
     * @return true if the Person object is the same, else false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        if (!this.name.equals(((Person) obj).name))
            return false;
        if (this.gender != ((Person) obj).gender)
            return false;
        return true;
        //return super.equals(obj);
    }

    /**
     * Generates a hash for an instance of the Person class.
     * All Person with the exact same attributes will generate the same hashCode.
     * @return hashCode for the person instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, gender);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}