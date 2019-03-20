package e002_challenge;

public class Client {
    public static void main(String[] args) throws CloneNotSupportedException{
        BasicCarCache.loadCache();

        BasicCar bc1 = (BasicCar) BasicCarCache.getBasicCar("Green Nano");
        bc1.price = bc1.price + BasicCar.setPrice();
        System.out.println("Car is: " + bc1.getName() + " and it's price is Rs." + bc1.getPrice());

        bc1 = (BasicCar) BasicCarCache.getBasicCar("Ford Yellow");
        bc1.price = bc1.price + BasicCar.setPrice();
        System.out.println("Car is: " + bc1.getName() + " and it's price is Rs." + bc1.getPrice());


    }
}
