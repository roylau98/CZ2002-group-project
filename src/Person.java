import java.io.Serializable;
import java.util.Objects;

/**
 * A parent class for {@link Customer} and {@link Staff} class
 *
 * @since 2021-11-5
 */
public class Person implements Serializable {
    /**
     * Person name
     */
    private String name;

    /**
     * Person gender
     */
    private Sex gender;

    /**
     * Class Constructor
     *
     * @param name   name of the {@code Person}
     * @param gender gender of the {@code Person}
     */
    public Person(String name, Sex gender) {
        this.name = name;
        this.gender = gender;
    }

    /**
     * Copy Constructor.
     *
     * @param p {@code Person} to be cloned.
     */
    public Person(Person p) {
        this.name = String.valueOf(p.name);
        this.gender = p.gender;
    }

    /**
     * Gets the name of the {@code Person}
     *
     * @return name of the {@code Person}
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the {@code Person}
     *
     * @param name name of the {@code Person}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the gender of the {@code Person}
     *
     * @return gender of the {@code Person}
     */
    public Sex getGender() {
        return this.gender;
    }

    /**
     * Sets the gender of the {@code Person}
     *
     * @param gender gender of the {@code Person}
     */
    public void setGender(Sex gender) {
        this.gender = gender;
    }

    /**
     * Checks and compares {@code Person} objects
     *
     * @param obj {@code Person} to be compared
     * @return true if the {@code Person} object is the same, else false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        if (!this.name.equals(((Person) obj).name))
            return false;
        return this.gender == ((Person) obj).gender;
    }

    /**
     * Generates a hash for an instance of the {@code Person} class.
     * All Person with the exact same attributes will generate the same hashCode.
     *
     * @return hashCode for the {@code Person} instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, gender);
    }

    /**
     * Return a string with {@code Person} details.
     *
     * @return String with {@code Person} details.
     */
    @Override
    public String toString() {
        return name + " " + gender + " ";
    }
}
