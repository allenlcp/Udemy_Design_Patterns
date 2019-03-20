package e002_challenge;

import java.util.Random;

public abstract class BasicCar implements Cloneable {
    String name;
    int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public static int setPrice() {
        int price = 0;
        Random r = new Random();
        int p = r.nextInt(1000000);
        price = p;
        return price;
    }

    @Override
    protected BasicCar clone() throws CloneNotSupportedException {
       return (BasicCar) super.clone();
    }
}

class Nano extends BasicCar{
    public Nano(String m) {
        this.name = m;
    }

    @Override
    protected BasicCar clone() throws CloneNotSupportedException {
        return (Nano) super.clone();
    }
}

class Ford extends BasicCar{
    public Ford(String m) {
        this.name = m;
    }

    @Override
    protected BasicCar clone() throws CloneNotSupportedException {
        return (Ford) super.clone();
    }
}
