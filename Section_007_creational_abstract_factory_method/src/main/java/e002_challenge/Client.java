package e002_challenge;

public class Client {
    public static void main(String[] args) {
        MovieFactoryInterface hollyWoodMovieFactory = FactoryProducer.getFactory("HollywoodMovie");
        HollywoodMovie hAction = hollyWoodMovieFactory.getHollywoodMovie("action");
        HollywoodMovie hComedy = hollyWoodMovieFactory.getHollywoodMovie("comedy");

        System.out.println("\nHollywood movies are: ");
        System.out.println(hAction.getMovieName());
        System.out.println(hComedy.getMovieName());


        MovieFactoryInterface bollyWoodMovieFactory = FactoryProducer.getFactory("BollywoodMovie");
        BollywoodMovie bAction = bollyWoodMovieFactory.getBollywoodMovie("action");
        BollywoodMovie bComedy = bollyWoodMovieFactory.getBollywoodMovie("comedy");

        System.out.println("\nBollywood movies are: ");
        System.out.println(bAction.getMovieName());
        System.out.println(bComedy.getMovieName());
    }
}
