package e002_adapter_composition2;

public interface CalculatorInterface {
    // target interface
    double getArea(Rectangle r);
}

class Calculator implements  CalculatorInterface {
    Rectangle rectangle;

    @Override
    public double getArea(Rectangle r) {
        rectangle = r;
        return rectangle.length * rectangle.width;
    }
}