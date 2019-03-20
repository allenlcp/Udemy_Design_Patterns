package e002_challenge;

public interface Item {
    String getName();
    double getPrice();
    Packing getPacking();
}

abstract class Burger implements Item{
    @Override
    public Packing getPacking() {
        return new Wrapper();
    }
}

abstract class ColdDrink implements Item{
     @Override
    public Packing getPacking() {
        return new Bottle();
    }
}

class VegBurger extends Burger{
    String name;
    double price;

    public VegBurger(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}

class ChickenBurger extends Burger{
    String name;
    double price;

    public ChickenBurger(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}

class Coke extends ColdDrink{
    String name;
    double price;

    public Coke(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}

class Pepsi extends ColdDrink{
    String name;
    double price;

    public Pepsi(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
