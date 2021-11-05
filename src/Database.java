import java.io.*;
/**
 * Stores and retrieves serialized {@link RRPSS} object. 
 * This class uses {@link FileInputStream} class and {@link ObjectInputStream} class in order to read an object from a ".txt" file.
 * For the purpose of writing a serialized object into a ".txt" file, FileOutputStream and ObjectInputStream class are used.
 * 
 * <p>
 * This Database class allows the main application to load previous saved data and to store any changes made during application
 * execution.
 * </p>
 * 
 * <p>
 * <b>Note:</b> All classes which are going to be serialized must implement the {@link Serializable} interface!
 * </p>
 * 
 * @author
 * @since 2021-11-5
 */
public class Database {
    /**
	 * Save a serialized object from a ".txt" file from the given path. 
	 * 
	 * <p>
	 * <b>Note:</b> All classes which are serialized must implement the {@link Serializable} interface!
	 * </p>
	 * 
	 * <p>
	 * Exceptions which may occur during data saving include:
	 * <li>IOException}</li>
	 * 
	 * 
	 * The exceptions have been handled inside the method.
	 * <br>
	 * If there is no exception, the {@link RRPSS} object will be save into the ".txt" file 
	 * </p>
	 * 
	 * @param 	obj 	        serialized objecct to be saved
     * @param   filename        A relative path to the project folder where the ".txt" file resides
	 *
	 */
    public void save(Object obj, String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();

        }
        catch (IOException e) {
            System.out.println("Failed export!");
        }
    }
    /**
	 *  Load a serialized object from a ".txt" file from the given path. 
	 * 
	 * <p>
	 * <b>Note:</b> All classes which are serialized must implement the {@link Serializable} interface!
	 * </p>
	 * 
	 * <p>
	 * Exceptions which may occur during data reading include:
	 * <ul>
	 * <li>ClassNotFoundException</li>
	 * <li>IOException}</li>
	 * </ul>
	 * <br>
	 * All exceptions have been handled inside the method.
	 * <br>
	 * If there is no exception, the {@link RRPSS} object inside the ".txt" file will be returned.
	 * Otherwise, {@code null}.
	 * </p>
	 * 
	 * @param 	filename 	A relative path to the project folder where the ".txt" file resides
	 * @return 			The {@link RRPSS} object inside the ".txt" file if it is found, {@code null} otherwise
     *
	 */
    public Object load(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream  = new ObjectInputStream(fileInputStream);
            return objectInputStream.readObject();
        }
        catch (IOException e) {
            System.out.println("No such file!");
            return null;
        }
        catch (ClassNotFoundException e1) {
            System.out.println("No such class");
            return null;
        }
    }
}
