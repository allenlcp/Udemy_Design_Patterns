package e002_challenge;

public class FactoryProducer {
    public static MovieFactoryInterface getFactory(String choice) {
        if (choice.equalsIgnoreCase("HollywoodMovie")) {
            return new HollywoodMovieFactory();
        } else if (choice.equalsIgnoreCase("BollywoodMovie")) {
            return new BollywoodMovieFactory();
        }
        return null;
    }
}
