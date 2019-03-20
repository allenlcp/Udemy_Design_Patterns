package e002_adapter_composition2;

public class Client {
    public static void main(String[] args) {
        System.out.println("**Adapter Pattern**");

        Triangle t = new Triangle(20, 10);
        CalculatorInterface calculatorInterface = new CalculatorAdapter(t);

        System.out.println("Aread of Triangle: " + calculatorInterface.getArea(null));
    }
}
