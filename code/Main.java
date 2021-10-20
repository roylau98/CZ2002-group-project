import java.util.HashMap;

public class Main {
    private static HashMap<Person, Integer> hm = new HashMap<>();

    public static void main(String[] args) {
        Person p1 = new Person("John", Sex.MALE);
        Person p2 = new Person("John", Sex.FEMALE);
        System.out.println(p1.equals(p2));


        hm.put(p1, 1);
        hm.put(p2, 1);
        //hm.put(LocalDateTime.of(2021, 10, 19, 22, 0), 2);
        System.out.println(hm.toString());
        System.out.println(hm.size());
    }
}
