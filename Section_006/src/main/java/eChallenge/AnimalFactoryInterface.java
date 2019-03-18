package main.java.eChallenge;

public abstract class AnimalFactoryInterface {
    public abstract AnimalInterface getAnimalType(String type) throws Exception;
}


class ConcreteFactory extends AnimalFactoryInterface {
    @Override
    public AnimalInterface getAnimalType(String animalType) throws Exception {

        switch (animalType){
            case "Duck":
                return new Duck();
            case "Tiger":
                return new Tiger();
            default:
                throw new Exception("Animal type: " + animalType + " cannot be instantiated");
        }
    }
}