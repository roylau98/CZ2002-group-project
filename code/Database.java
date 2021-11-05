import java.io.*;

public class Database {

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
