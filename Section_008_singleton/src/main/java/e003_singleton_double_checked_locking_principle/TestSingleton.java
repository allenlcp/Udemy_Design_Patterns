package e003_singleton_double_checked_locking_principle;

public class TestSingleton {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();

        // set the data value
        s.setData(55);

        System.out.println("First reference: " + s);
        System.out.println("MakeACaptain data value is: " + s.getData());

        // Get another reference to the singleton
        // Is it the same object?
        s = null;
        s = Singleton.getInstance();
        System.out.println("First reference: " + s);
        System.out.println("MakeACaptain data value is: " + s.getData());

    }
}
