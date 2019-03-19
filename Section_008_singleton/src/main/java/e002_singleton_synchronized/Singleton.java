package e002_singleton_synchronized;

public class Singleton {
    // the private reference to the one and only instance
    private static Singleton uniqueInstance = null;

    // an instance attribute
    private int data = 0;

    /**
    * The MakeACaptain Constructor
    * Note that it is private!
    * No client can instantiate a singleton object!
    */
    private Singleton(){}

    // by adding the synchronized keyword to getInstance
    // we force every thread to wait its turn before it can enter the method
    public static synchronized Singleton getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Singleton();
        }

        return uniqueInstance;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
