package main.java.e000_decorator;

public class Client {
    public static void main(String[] args) {
        ConcreteComponent cc = new ConcreteComponent();

        ConcreteDecorator1 cd1 = new ConcreteDecorator1();
        ConcreteDecorator2 cd2 = new ConcreteDecorator2();

        cd1.setTheComponent(cc);
        cd1.doJob();

        cd2.setTheComponent(cd1);
        cd2.doJob();
    }
}
