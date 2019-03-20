package main.java.eChallenge;

public interface AnimalInterface {
    void walk();
    void eat();
}

class Tiger implements AnimalInterface{
    @Override
    public void walk() {
        System.out.println("Tiger::walk() method");
    }

    @Override
    public void eat() {
        System.out.println("Tiger::eat() method");
    }
}

class Duck implements AnimalInterface{
    @Override
    public void walk() {
        System.out.println("Duck::walk() method");
    }

    @Override
    public void eat() {
        System.out.println("Duck::eat() method");
    }
}
