package e005_singleton_bill_pugh;

public class Singleton {

    // an instance attribute
    private int data = 0;

    /**
    * The MakeACaptain Constructor
    * Note that it is private!
    * No client can instantiate a singleton object!
    */
    private Singleton(){}

    private static class SingletonHelper{
        // Nested class is reference after getInstance() is called
        // the private reference to the one and only instance
        private static final Singleton uniqueInstance = new Singleton();

    }

    public static Singleton getInstance(){
        return SingletonHelper.uniqueInstance;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
