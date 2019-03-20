package e002_challenge;

import java.util.Hashtable;

public class BasicCarCache {
    private static Hashtable<String, BasicCar> carMap = new Hashtable<String, BasicCar>();

    public static BasicCar getBasicCar(String name) throws CloneNotSupportedException{
        BasicCar cachedBasicCar = carMap.get(name);
        return (BasicCar) cachedBasicCar.clone();
    }

    public static void loadCache(){
        BasicCar nano = new Nano("Green Nano");
        nano.price = 100000;
        carMap.put("Green Nano", nano);

        BasicCar ford = new Ford("Ford Yellow");
        ford.price = 500000;
        carMap.put("Ford Yellow", ford);
    }

}
