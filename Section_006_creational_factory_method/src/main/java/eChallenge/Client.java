package main.java.eChallenge;

public class Client {
    public static void main(String[] args) throws Exception{

        AnimalFactoryInterface animalFactory = new ConcreteFactory();

        AnimalInterface animal1 = animalFactory.getAnimalType("Duck");
        animal1.eat();
        animal1.walk();

        AnimalInterface animal2 = animalFactory.getAnimalType("Tiger");
        animal2.eat();
        animal2.walk();
    }
}
