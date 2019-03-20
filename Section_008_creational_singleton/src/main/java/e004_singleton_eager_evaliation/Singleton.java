package e004_singleton_eager_evaliation;

public class Singleton{
    private static Singleton uniqueInstance = new Singleton();

    private Singleton(){}

    public static Singleton getInsntance(){
        // we already got an instance so just return it
        return uniqueInstance;
    }
}